package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberBindingInfoService;
import com.allqj.virtual_number_administrate.business.vo.ModifyBindingInfoRequest;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberBindingInfoQueryResult;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberBindingQueryResult;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBindingQueryBase;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@UseDistributedLocks
public class VirtualNumberBindingInfoServiceImpl implements IVirtualNumberBindingInfoService {

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;
    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;

    @Autowired
    @Qualifier("virtualNumberModifyToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumbermodifyBaseService;
    @Autowired
    @Qualifier("virtualNumberBindingInfoModifyToESBaseServiceImpl")
    private IModifyBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> bindingInfomodifyBaseService;
    @Autowired
    private IModifyBaseService<DeptInfoMysqlEntity, DeptInfoEsEntity> deptInfoEsEntityIModifyBaseService;

    /**
     * 查询绑定的虚拟号详细信息
     *
     * @param phone
     * @return
     */
    @Override
    public List<VirtualNumberBindingInfoQueryResult> virtualNumberInfo(String phone) {
        List<VirtualNumberBindingInfoQueryResult> resultList = new ArrayList<>();
        VirtualNumberTypeEnum.getDictionaryResult().forEach(dictionaryResult ->
        {
            List<VirtualNumberBindingQueryBase> virtualNumberBindingQueryBaseList =
                    virtualNumberBindingInfoMysqlRepository.bindingInfo(phone, dictionaryResult.getCode());
            if (virtualNumberBindingQueryBaseList != null && virtualNumberBindingQueryBaseList.size() > 0)
                resultList.add(new VirtualNumberBindingInfoQueryResult(
                        virtualNumberBindingQueryBaseList,
                        VirtualNumberTypeEnum.getVirtualNumberTypeEnum(dictionaryResult.getCode())));
        });
        return resultList;
    }

    /**
     * 查询绑定的虚拟号
     *
     * @param phone
     * @return
     */
    @Override
    public List<VirtualNumberBindingQueryResult> virtualNumber(String phone) {
        List<VirtualNumberBindingQueryResult> resultList = new ArrayList<>();
        VirtualNumberTypeEnum.getDictionaryResult().forEach(dictionaryResult ->
        {
            List<VirtualNumberBindingQueryBase> virtualNumberBindingQueryBaseList =
                    virtualNumberBindingInfoMysqlRepository.bindingInfo(phone, dictionaryResult.getCode());
            if (virtualNumberBindingQueryBaseList != null && virtualNumberBindingQueryBaseList.size() > 0) {
                VirtualNumberTypeEnum virtualNumberTypeEnum = VirtualNumberTypeEnum.getVirtualNumberTypeEnum(dictionaryResult.getCode());
                virtualNumberBindingQueryBaseList.forEach(virtualNumberBindingQueryBase ->
                        resultList.add(new VirtualNumberBindingQueryResult(virtualNumberBindingQueryBase, virtualNumberTypeEnum)));
            }
        });
        return resultList;
    }

    /**
     * 部门调动修改绑定信息
     *
     * @param modifyBindingInfoRequest
     * @return
     */
    @Override
    @Lock
    @Deprecated
    public Boolean modifyBindingInfo(ModifyBindingInfoRequest modifyBindingInfoRequest) {
        //修改Mysql绑定信息,同时添加一条绑定历史
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoMysqlRepository.findByPhoneAndIsdeleteFalse(modifyBindingInfoRequest.getPhone());
        if (null == virtualNumberBindingInfoMysqlEntity)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        virtualNumberBindingInfoMysqlEntity.setIsdelete(true);
        virtualNumberBindingInfoMysqlRepository.save(virtualNumberBindingInfoMysqlEntity);
        //添加新的绑定信息
        BeanUtils.copyProperties(modifyBindingInfoRequest, virtualNumberBindingInfoMysqlEntity);
        VirtualNumberBindingInfoMysqlEntity saveVirtualNumberBindingInfoMysqlEntity = getNewBindingInfoEntity(virtualNumberBindingInfoMysqlEntity);
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, saveVirtualNumberBindingInfoMysqlEntity);
        virtualNumberBindingInfoMysqlRepository.save(saveVirtualNumberBindingInfoMysqlEntity);

        //修改Mysql隐号信息
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumberBindingInfoMysqlEntity.getVirtualNumber(), virtualNumberBindingInfoMysqlEntity.getVirtualNumberType(), false);
        virtualNumberMysqlEntity.setDeptId(modifyBindingInfoRequest.getDeptId());
        virtualNumberMysqlEntity.setDeptType(modifyBindingInfoRequest.getDeptType());
        virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
        //修改Mysql绑定信息,同时添加一条绑定历史
        bindingInfomodifyBaseService.modify(virtualNumberBindingInfoMysqlEntity, virtualNumberMysqlEntity.getUtype());
        //修改ES隐号信息
        virtualNumbermodifyBaseService.modify(virtualNumberMysqlEntity, virtualNumberMysqlEntity.getUtype());

        //添加新Mysql部门信息
        DeptInfoMysqlEntity deptInfoMysqlEntity = deptInfoMysqlRepository.findByDeptIdAndDeptTypeAndIsdelete(modifyBindingInfoRequest.getDeptId(), modifyBindingInfoRequest.getDeptType(), false);
        if (null == deptInfoMysqlEntity)
            deptInfoMysqlEntity = new DeptInfoMysqlEntity();
        deptInfoMysqlEntity.setDeptId(modifyBindingInfoRequest.getDeptId());
        deptInfoMysqlEntity.setDeptType(modifyBindingInfoRequest.getDeptType());
        deptInfoMysqlEntity.setDeptName(modifyBindingInfoRequest.getDeptName());
        deptInfoMysqlEntity = deptInfoMysqlRepository.save(deptInfoMysqlEntity);
        //添加新ES部门信息
        deptInfoEsEntityIModifyBaseService.modify(deptInfoMysqlEntity, virtualNumberMysqlEntity.getUtype());
        return true;
    }

    /**
     * 获得新的绑定信息
     *
     * @param virtualNumberBindingInfoMysqlEntity
     * @return
     */
    @Deprecated
    private VirtualNumberBindingInfoMysqlEntity getNewBindingInfoEntity(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity) {
        virtualNumberBindingInfoMysqlEntity.setId(null);
        virtualNumberBindingInfoMysqlEntity.setCreatetime(null);
        virtualNumberBindingInfoMysqlEntity.setModifytime(null);
        virtualNumberBindingInfoMysqlEntity.setIsdelete(false);
        VirtualNumberBindingInfoMysqlEntity saveVirtualNumberBindingInfoMysqlEntity = new VirtualNumberBindingInfoMysqlEntity();
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, saveVirtualNumberBindingInfoMysqlEntity);
        return saveVirtualNumberBindingInfoMysqlEntity;
    }

}
