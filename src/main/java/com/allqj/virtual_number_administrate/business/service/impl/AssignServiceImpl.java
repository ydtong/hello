package com.allqj.virtual_number_administrate.business.service.impl;


import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberAssignBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberCancelAssignBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IAssignService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 分配部门实现
 */
@Service
@AddLog
@UseDistributedLocks
public class AssignServiceImpl implements IAssignService {

    @Autowired
    @Qualifier("virtualNumberModifyToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberModifyToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberAssignToMysqlBaseServiceImpl")
    private IVirtualNumberAssignBaseService<String, VirtualNumberMysqlEntity> virtualNumberAssignToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberCancelAssignToMysqlBaseServiceImpl")
    private IVirtualNumberCancelAssignBaseService<VirtualNumberMysqlEntity, VirtualNumberMysqlEntity> virtualNumberCancelAssignToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberQueryAllFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberQueryAllRequest, List<String>> virtualNumberQueryAllFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("deptModifyToEsBaseServiceImpl")
    private IModifyBaseService<DeptInfoMysqlEntity, DeptInfoEsEntity> deptModifyToEsBaseServiceImpl;

    @Autowired
    @Qualifier("deptVirtualNumberUpdateToMysqlBaseServiceImpl")
    private IModifyBaseService<DeptInfoMysqlEntity, DeptInfoMysqlEntity> deptVirtualNumberUpdateToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("deptQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<DeptBase, DeptInfoMysqlEntity> deptQueryFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> virtualNumberQueryFromMysqlBaseServiceImpl;

    /**
     * 虚拟号列表选定分配
     *
     * @param assignDeptRequest
     * @return
     */
    @Transactional
    @LogDescribe("虚拟号列表选定分配")
    @Lock
    public AssignDeptResult assignDept(AssignDeptRequest assignDeptRequest) {
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        for (String virtualNumber : assignDeptRequest.getVirtualNumberList()) {
            //更新隐号信息
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberAssignToMysqlBaseServiceImpl.modify(virtualNumber, assignDeptRequest.getVirtualNumberType(), assignDeptRequest);
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);
        }

        //更新部门信息
        DeptInfoMysqlEntity deptInfoMysqlEntity = deptQueryFromMysqlBaseServiceImpl.query(assignDeptRequest, false, true);
        deptInfoMysqlEntity = deptVirtualNumberUpdateToMysqlBaseServiceImpl.modify(deptInfoMysqlEntity, assignDeptRequest.getVirtualNumberType());

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, assignDeptRequest.getVirtualNumberType()));
        deptModifyToEsBaseServiceImpl.modify(deptInfoMysqlEntity, assignDeptRequest.getVirtualNumberType());
        return new AssignDeptResult();
    }

    /**
     * 虚拟号列表选定取消分配
     *
     * @param cancelAssignDeptRequest
     * @return
     */
    @Transactional
    @LogDescribe("虚拟号列表选定取消分配")
    @Lock
    public CancelAssignDeptResult cancelAssignDept(CancelAssignDeptRequest cancelAssignDeptRequest) {
        List<DeptInfoMysqlEntity> deptInfoMysqlEntityList = new ArrayList<>();
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        for (String virtualNumber : cancelAssignDeptRequest.getVirtualNumberList()) {
            //更新隐号信息
            VirtualNumberBase virtualNumberBase = new VirtualNumberBase(virtualNumber, cancelAssignDeptRequest.getVirtualNumberType());
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberQueryFromMysqlBaseServiceImpl.query(virtualNumberBase, false, true);
            DeptBase deptBase = new DeptBase(virtualNumberMysqlEntity.getDeptId(), virtualNumberMysqlEntity.getDeptType());
            virtualNumberCancelAssignToMysqlBaseServiceImpl.modifyAnd(virtualNumberMysqlEntity, cancelAssignDeptRequest.getVirtualNumberType());
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);

            //更新部门信息
            DeptInfoMysqlEntity deptInfoMysqlEntity = deptQueryFromMysqlBaseServiceImpl.query(deptBase, false, true);
            deptInfoMysqlEntity = deptVirtualNumberUpdateToMysqlBaseServiceImpl.modify(deptInfoMysqlEntity, cancelAssignDeptRequest.getVirtualNumberType());
            deptInfoMysqlEntityList.add(deptInfoMysqlEntity);
        }

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, cancelAssignDeptRequest.getVirtualNumberType()));
        deptInfoMysqlEntityList.forEach(deptInfoMysqlEntity ->
                deptModifyToEsBaseServiceImpl.modify(deptInfoMysqlEntity, cancelAssignDeptRequest.getVirtualNumberType()));
        return new CancelAssignDeptResult();
    }

    /**
     * 部门列表按数量随机分配
     *
     * @param numberAssignRequest
     * @return
     */
    @Transactional
    @LogDescribe("部门列表按数量随机分配")
    @Lock
    public NumberAssignResult numberAssign(NumberAssignRequest numberAssignRequest) {

        //查询空闲隐号
        VirtualNumberQueryAllRequest virtualNumberQueryAllRequest = new VirtualNumberQueryAllRequest();
        virtualNumberQueryAllRequest.setIsAssign(false);
        virtualNumberQueryAllRequest.setVirtualNumberType(numberAssignRequest.getVirtualNumberType());
        virtualNumberQueryAllRequest.setNumber(numberAssignRequest.getNumber());
        List<String> virtualNumberList = virtualNumberQueryAllFromMysqlBaseServiceImpl.query(virtualNumberQueryAllRequest, false, true);
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        //更新隐号信息
        for (String virtualNumber : virtualNumberList) {
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberAssignToMysqlBaseServiceImpl.modify(virtualNumber, numberAssignRequest.getVirtualNumberType(), numberAssignRequest);
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);
        }

        //更新部门信息
        DeptInfoMysqlEntity deptInfoMysqlEntity = deptQueryFromMysqlBaseServiceImpl.query(numberAssignRequest, false, true);
        deptInfoMysqlEntity = deptVirtualNumberUpdateToMysqlBaseServiceImpl.modify(deptInfoMysqlEntity, numberAssignRequest.getVirtualNumberType());

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, numberAssignRequest.getVirtualNumberType()));
        deptModifyToEsBaseServiceImpl.modify(deptInfoMysqlEntity, numberAssignRequest.getVirtualNumberType());
        return new NumberAssignResult();
    }

    /**
     * 部门列表按数量随机取消分配
     *
     * @param cancelNumberAssignRequest
     * @return
     */
    @Transactional
    @LogDescribe("部门列表按数量随机取消分配")
    @Lock
    public CancelNumberAssignResult cancelNumberAssign(CancelNumberAssignRequest cancelNumberAssignRequest) {
        //查询占用隐号
        VirtualNumberQueryAllRequest virtualNumberRepository = new VirtualNumberQueryAllRequest();
        virtualNumberRepository.setIsBinding(false);
        virtualNumberRepository.setIsAssign(true);
        virtualNumberRepository.setVirtualNumberType(cancelNumberAssignRequest.getVirtualNumberType());
        virtualNumberRepository.setNumber(cancelNumberAssignRequest.getNumber());
        virtualNumberRepository.setDeptId(cancelNumberAssignRequest.getDeptId());
        virtualNumberRepository.setDeptType(cancelNumberAssignRequest.getDeptType());
        List<String> virtualNumberList = virtualNumberQueryAllFromMysqlBaseServiceImpl.query(virtualNumberRepository, false, true);

        //更新隐号信息
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        for (String virtualNumber : virtualNumberList) {
            VirtualNumberBase virtualNumberBase = new VirtualNumberBase(virtualNumber, cancelNumberAssignRequest.getVirtualNumberType());
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberQueryFromMysqlBaseServiceImpl.query(virtualNumberBase, false, true);
            virtualNumberMysqlEntity = virtualNumberCancelAssignToMysqlBaseServiceImpl.modifyAnd(virtualNumberMysqlEntity, cancelNumberAssignRequest.getVirtualNumberType());
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);
        }

        //更新部门信息
        DeptInfoMysqlEntity deptInfoMysqlEntity = deptQueryFromMysqlBaseServiceImpl.query(cancelNumberAssignRequest, false, true);
        deptInfoMysqlEntity = deptVirtualNumberUpdateToMysqlBaseServiceImpl.modify(deptInfoMysqlEntity, cancelNumberAssignRequest.getVirtualNumberType());

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, cancelNumberAssignRequest.getVirtualNumberType()));
        deptModifyToEsBaseServiceImpl.modify(deptInfoMysqlEntity, cancelNumberAssignRequest.getVirtualNumberType());
        return new CancelNumberAssignResult();
    }
}
