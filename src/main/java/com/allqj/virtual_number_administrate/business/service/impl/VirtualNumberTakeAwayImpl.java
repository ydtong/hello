package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IDeptInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberBindingInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberTakeAwayService;
import com.allqj.virtual_number_administrate.business.vo.ModifyBindingInfoRequest;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : lsy
 * @description: 部门调动带走隐号
 * @date : 2019-04-24 9:53
 */
@Service
public class VirtualNumberTakeAwayImpl implements IVirtualNumberTakeAwayService {
    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository bindingInfoMysqlRepository; //人员和隐号dao
    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository; //部门和隐号dao
    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository; //部门隐号信息dao
    @Autowired
    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepositoryEs;
    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepositoryEs;
    @Autowired
    private IDeptInfoEsRepository deptInfoEsRepositoryEs;

    @Transactional
    @Override
    public ResultVO<Boolean> takeAwayVirtual(ModifyBindingInfoRequest modifyBindingInfoRequest) {
        List<VirtualNumberBindingInfoMysqlEntity> bindingInfoMysqlEntityList = bindingInfoMysqlRepository.findAllByPhoneAndIsdeleteFalse(modifyBindingInfoRequest.getPhone());
        List<VirtualNumberBindingInfoEsEntity> bindingInfoMysqlEntities = new ArrayList<>();
        Date modifyTime = new Date();
        //获取隐号人员绑定信息
        getBindingInfoListResult(modifyBindingInfoRequest, bindingInfoMysqlEntityList, bindingInfoMysqlEntities, modifyTime);
        //获取部门隐号绑定信息
        List<VirtualNumberEsEntity> virtualNumberMysqlEntities = new ArrayList<>();
        getDeptVirtualBindingResult(modifyBindingInfoRequest, bindingInfoMysqlEntities, virtualNumberMysqlEntities, modifyTime);
        //获取部门隐号基础信息
        List<DeptInfoEsEntity> deptInfoMysqlEntities = new ArrayList<>();
        getDeptInfoResult(bindingInfoMysqlEntities, deptInfoMysqlEntities, modifyTime);
        //同步es
        SynchronousEs(bindingInfoMysqlEntities, virtualNumberMysqlEntities, deptInfoMysqlEntities);
        return ResultVO.newInstance(true);
    }

    /**
     * 同步es
     *
     * @param bindingInfoMysqlEntities
     * @param virtualNumberMysqlEntities
     * @param deptInfoMysqlEntities
     */
    private void SynchronousEs(List<VirtualNumberBindingInfoEsEntity> bindingInfoMysqlEntities, List<VirtualNumberEsEntity> virtualNumberMysqlEntities, List<DeptInfoEsEntity> deptInfoMysqlEntities) {
        virtualNumberBindingInfoEsRepositoryEs.saveAll(bindingInfoMysqlEntities);
        virtualNumberEsRepositoryEs.saveAll(virtualNumberMysqlEntities);
        deptInfoEsRepositoryEs.saveAll(deptInfoMysqlEntities);
    }

    /**
     * 获取部门隐号基础信息,新调部门如果没有则新建部门信息
     *
     * @param bindingInfoMysqlEntityList
     * @param deptInfoMysqlEntities
     * @param modifyTime
     */
    private void getDeptInfoResult(List<VirtualNumberBindingInfoEsEntity> bindingInfoMysqlEntityList, List<DeptInfoEsEntity> deptInfoMysqlEntities, Date modifyTime) {
        bindingInfoMysqlEntityList.forEach(bindingInfoMysqlEntitie -> {
            DeptInfoMysqlEntity deptInfoMysqlEntity = deptInfoMysqlRepository.findByDeptIdAndDeptTypeAndIsdelete(bindingInfoMysqlEntitie.getDeptId(), bindingInfoMysqlEntitie.getDeptType(), false);
            if (null == deptInfoMysqlEntity) {
                DeptInfoMysqlEntity deptInfoMysql = new DeptInfoMysqlEntity(bindingInfoMysqlEntitie.getDeptId(), bindingInfoMysqlEntitie.getDeptType(), bindingInfoMysqlEntitie.getDeptName());
                DeptInfoMysqlEntity newDeptInfoMysqlEntity = deptInfoMysqlRepository.save(deptInfoMysql);
                DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
                BeanUtils.copyProperties(newDeptInfoMysqlEntity, deptInfoEsEntity);
                deptInfoMysqlEntities.add(deptInfoEsEntity);

            } else {
                //原部门是否有空闲虚拟号
                List<VirtualNumberMysqlEntity> shortNumberMysqlEntityList = virtualNumberMysqlRepository.findAllByDeptIdAndIsAssignTrueAndIsBindingFalseAndIsdeleteFalseAndUtype(bindingInfoMysqlEntitie.getDeptId(), VirtualNumberTypeEnum.SHORT.getCode());
                if (null == shortNumberMysqlEntityList || shortNumberMysqlEntityList.size() == 0) {
                    deptInfoMysqlEntity.setIsFreeShortNumber(false);
                } else {
                    deptInfoMysqlEntity.setIsFreeShortNumber(true);
                }
                List<VirtualNumberMysqlEntity> longNumberMysqlEntityList = virtualNumberMysqlRepository.findAllByDeptIdAndIsAssignTrueAndIsBindingFalseAndIsdeleteFalseAndUtype(bindingInfoMysqlEntitie.getDeptId(), VirtualNumberTypeEnum.LONG.getCode());
                if (null == longNumberMysqlEntityList || longNumberMysqlEntityList.size() == 0) {
                    deptInfoMysqlEntity.setIsFreeLongNumber(false);
                } else {
                    deptInfoMysqlEntity.setIsFreeLongNumber(true);
                }
                deptInfoMysqlEntity.setModifytime(modifyTime);
                DeptInfoMysqlEntity newDeptInfoMysqlEntity = deptInfoMysqlRepository.save(deptInfoMysqlEntity);
                DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
                BeanUtils.copyProperties(newDeptInfoMysqlEntity, deptInfoEsEntity);
                deptInfoMysqlEntities.add(deptInfoEsEntity);
            }
            return;
        });
    }

    /**
     * 获取部门隐号绑定信息
     *
     * @param modifyBindingInfoRequest
     * @param bindingInfoMysqlEntities
     * @param virtualNumberMysqlEntities
     * @param modifyTime
     */
    private void getDeptVirtualBindingResult(ModifyBindingInfoRequest modifyBindingInfoRequest, List<VirtualNumberBindingInfoEsEntity> bindingInfoMysqlEntities, List<VirtualNumberEsEntity> virtualNumberMysqlEntities, Date modifyTime) {
        bindingInfoMysqlEntities.forEach(bindingInfoMysqlEntitie -> {
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(bindingInfoMysqlEntitie.getVirtualNumber(),
                    bindingInfoMysqlEntitie.getVirtualNumberType(), false);
            virtualNumberMysqlEntity.setDeptId(modifyBindingInfoRequest.getDeptId());
            virtualNumberMysqlEntity.setDeptType(modifyBindingInfoRequest.getDeptType());
            virtualNumberMysqlEntity.setModifytime(modifyTime);
            virtualNumberMysqlEntity.setIsHistory(true);
            virtualNumberMysqlEntity.setAccountCode(virtualNumberMysqlEntity.getAccountCode());
            virtualNumberMysqlEntity.setMainPhone(virtualNumberMysqlEntity.getMainPhone());
            VirtualNumberMysqlEntity newVirtualNumberMysqlEntity = virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
            VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
            BeanUtils.copyProperties(newVirtualNumberMysqlEntity, virtualNumberEsEntity);
            virtualNumberMysqlEntities.add(virtualNumberEsEntity);
        });
    }

    /**
     * //获取隐号人员绑定信息,将以前绑定信息删除再新增绑定信息
     *
     * @param modifyBindingInfoRequest
     * @param bindingInfoMysqlEntityList
     * @param bindingInfoMysqlEntities
     * @param modifyTime
     */
    private void getBindingInfoListResult(ModifyBindingInfoRequest modifyBindingInfoRequest, List<VirtualNumberBindingInfoMysqlEntity> bindingInfoMysqlEntityList, List<VirtualNumberBindingInfoEsEntity> bindingInfoMysqlEntities, Date modifyTime) {
        bindingInfoMysqlEntityList.forEach(bindingInfoMysqlEntity -> {
            bindingInfoMysqlEntity.setIsdelete(true);
            bindingInfoMysqlEntity.setModifytime(modifyTime);
            VirtualNumberBindingInfoMysqlEntity modifyBindingInfo = bindingInfoMysqlRepository.save(bindingInfoMysqlEntity);
            VirtualNumberBindingInfoEsEntity bindingInfoEsEntity = new VirtualNumberBindingInfoEsEntity();
            BeanUtils.copyProperties(modifyBindingInfo, bindingInfoEsEntity);
            bindingInfoMysqlEntities.add(bindingInfoEsEntity);
            VirtualNumberBindingInfoMysqlEntity newBindingInfo = new VirtualNumberBindingInfoMysqlEntity(modifyBindingInfo.getUserId(), modifyBindingInfo.getUserName()
                    , modifyBindingInfo.getPhone(), modifyBindingInfo.getVirtualNumber(), modifyBindingInfo.getVirtualNumberType());
            newBindingInfo.setCreatetime(modifyTime);
            newBindingInfo.setDeptId(modifyBindingInfoRequest.getDeptId());
            newBindingInfo.setDeptName(modifyBindingInfoRequest.getDeptName());
            newBindingInfo.setDeptType(modifyBindingInfoRequest.getDeptType());
            newBindingInfo.setIsdelete(false);
            VirtualNumberBindingInfoMysqlEntity newBindingInfoEntity = bindingInfoMysqlRepository.save(newBindingInfo);
            VirtualNumberBindingInfoEsEntity bindingInfoEs = new VirtualNumberBindingInfoEsEntity();
            BeanUtils.copyProperties(newBindingInfoEntity, bindingInfoEs);
            bindingInfoMysqlEntities.add(bindingInfoEs);

        });
    }
}
