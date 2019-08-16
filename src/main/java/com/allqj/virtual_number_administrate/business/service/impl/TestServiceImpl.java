package com.allqj.virtual_number_administrate.business.service.impl;


import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IDeptInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberBindingInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.ITestService;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AddLog
@UseDistributedLocks
@Service
public class TestServiceImpl implements ITestService, InitializingBean {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;

    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepository;

    @Autowired
    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepository;

    @Autowired
    private IDeptInfoEsRepository deptInfoEsRepository;

    public void afterPropertiesSet() throws Exception {
        System.out.println("");
    }

    @Override
    @Transactional
    @LogDescribe("test")
    @Lock
    public void test() {
//        VirtualNumberMysqlEntity virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
//        virtualNumberMysqlEntity.setVirtualNumber("99999999");
//        virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
        //throw new ResultException(0,"test");
        System.out.println("");
    }
}
