//package com.allqj.virtual_number_administrate.peopleService;
//
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IDeptInfoEsRepository;
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberBindingInfoEsRepository;
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
//import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
//import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
//import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
//import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
//import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
//import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
//import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DataUpdate
//{
//
//    @Autowired
//    private IDeptInfoMysqlRepository deptInfoMysqlRepository;
//    @Autowired
//    private IDeptInfoEsRepository deptInfoEsRepository;
//
//    @Autowired
//    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;
//    @Autowired
//    private IVirtualNumberEsRepository virtualNumberEsRepository;
//
//    @Autowired
//    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;
//    @Autowired
//    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepository;
//
//    /**
//     * 将mysql数据同步到es
//     */
//    @Test
//    @Rollback(false)
//    public void test1()
//    {
//        int i = 0;
//        List<DeptInfoMysqlEntity> deptInfoMysqlEntityList = deptInfoMysqlRepository.findAll();
//        deptInfoEsRepository.deleteAll();
//        for (DeptInfoMysqlEntity deptInfoMysqlEntity : deptInfoMysqlEntityList)
//        {
//            i ++;
//            DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
//            BeanUtils.copyProperties(deptInfoMysqlEntity,deptInfoEsEntity);
//            deptInfoEsRepository.save(deptInfoEsEntity);
//            System.out.println("部门信息表进度：" + i + "/" + deptInfoMysqlEntityList.size());
//        }
//
//        i = 0;
//        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = virtualNumberMysqlRepository.findAll();
//        virtualNumberEsRepository.deleteAll();
//        for (VirtualNumberMysqlEntity virtualNumberMysqlEntity : virtualNumberMysqlEntityList)
//        {
//            i ++;
//            VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
//            BeanUtils.copyProperties(virtualNumberMysqlEntity,virtualNumberEsEntity);
//            virtualNumberEsRepository.save(virtualNumberEsEntity);
//            System.out.println("虚拟号信息表：" + i + "/" + virtualNumberMysqlEntityList.size());
//        }
//
//        i = 0;
//        List<VirtualNumberBindingInfoMysqlEntity> virtualNumberBindingInfoMysqlEntityList = virtualNumberBindingInfoMysqlRepository.findAll();
//        virtualNumberBindingInfoEsRepository.deleteAll();
//        for (VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity : virtualNumberBindingInfoMysqlEntityList)
//        {
//            i ++;
//            VirtualNumberBindingInfoEsEntity virtualNumberBindingInfoEsEntity = new VirtualNumberBindingInfoEsEntity();
//            BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity,virtualNumberBindingInfoEsEntity);
//            virtualNumberBindingInfoEsRepository.save(virtualNumberBindingInfoEsEntity);
//            System.out.println("绑定信息表：" + i + "/" + virtualNumberBindingInfoMysqlEntityList.size());
//        }
//        System.out.println("");
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
