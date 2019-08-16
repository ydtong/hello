//package com.allqj.virtual_number_administrate;
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
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//*
// *@author : lsy
// *@description: 同步es数据
// *@date : 2019-04-25 10:25
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class SynchronousEs {
//    @Autowired
//    private IVirtualNumberBindingInfoMysqlRepository bindingInfoMysqlRepository;
//    @Autowired
//    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;
//    @Autowired
//    private IDeptInfoMysqlRepository deptInfoMysqlRepository;
//    @Autowired
//    private IDeptInfoEsRepository deptInfoEsRepositoryEs;
//    @Autowired
//    private IVirtualNumberBindingInfoEsRepository bindingInfoEsRepositoryEs;
//    @Autowired
//    private IVirtualNumberEsRepository virtualNumberEsRepositoryEs;
//
//    @Test
//    public void synchronousPeopleAndVirtual(){
//        List<VirtualNumberBindingInfoEsEntity> bindingInfoEsEntityList = new ArrayList<>();
//        List<VirtualNumberBindingInfoMysqlEntity> bindingInfoMysqlEntityList = bindingInfoMysqlRepository.findAll();
//        bindingInfoMysqlEntityList.parallelStream().forEach(
//            (bindingInfoMysqlEntity->{
//                VirtualNumberBindingInfoEsEntity bindingInfoEsEntity = new VirtualNumberBindingInfoEsEntity();
//                BeanUtils.copyProperties(bindingInfoMysqlEntity,bindingInfoEsEntity);
//                bindingInfoEsEntityList.add(bindingInfoEsEntity);
//            })
//        );
//
//        List<VirtualNumberEsEntity> virtualNumberEsEntityList = new ArrayList<>();
//        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntities = virtualNumberMysqlRepository.findAll();
//        virtualNumberMysqlEntities.parallelStream().forEach(virtualNumberMysqlEntity -> {
//            VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
//            BeanUtils.copyProperties(virtualNumberMysqlEntity,virtualNumberEsEntity);
//            virtualNumberEsEntityList.add(virtualNumberEsEntity);
//        });
//
//        List<DeptInfoEsEntity> deptInfoEsEntityList = new ArrayList<>();
//       List<DeptInfoMysqlEntity> deptInfoMysqlEntities = deptInfoMysqlRepository.findAll();
//       deptInfoMysqlEntities.forEach(deptInfoMysqlEntity -> {
//           DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
//           BeanUtils.copyProperties(deptInfoMysqlEntity,deptInfoEsEntity);
//           deptInfoEsEntityList.add(deptInfoEsEntity);
//       });
//       //同步部门隐号信息
//        deptInfoEsRepositoryEs.saveAll(deptInfoEsEntityList);
//        //同步绑定信息
//        bindingInfoEsRepositoryEs.saveAll(bindingInfoEsEntityList);
//        //同步部门分配隐号信息
//        virtualNumberEsRepositoryEs.saveAll(virtualNumberEsEntityList);
//    }
//}
