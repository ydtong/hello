package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberModifyIdCardRequest;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: cj
 * @description 短隐号修改身份证号
 * @date: 2019/4/30 14:00
 **/
@Service
public class ShortVirtualNumberModifyCardNumberServiceImpl implements IModifyBaseService<VirtualNumberModifyIdCardRequest, ResultVO<Boolean>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;
    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepository;
    @Override
    @Transactional
    public ResultVO<Boolean> modify(VirtualNumberModifyIdCardRequest modifyIdCardRequest, Integer virtualNumberType) {
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(modifyIdCardRequest.getVirtualNumber(), modifyIdCardRequest.getVirtualNumberType(), false);
        if (null == virtualNumberMysqlEntity) {
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        }
        virtualNumberMysqlEntity.setIdCard(modifyIdCardRequest.getIdCard());
        virtualNumberMysqlEntity.setModifytime(new Date());
        VirtualNumberMysqlEntity result = virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
        VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
        if (null == result) {
            return ResultVO.newInstance(false, StatusCodeEnum.MODIFY_FAIL.getCode(), StatusCodeEnum.MODIFY_FAIL.getMessage());
        }else {
            BeanUtils.copyProperties(result,virtualNumberEsEntity);
            virtualNumberEsRepository.save(virtualNumberEsEntity);
        }
        return ResultVO.newInstance(true);
    }
}
