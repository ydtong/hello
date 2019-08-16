//package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberDeleteService;
//
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.IVirtualNumberRepository;
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.entity.VirtualNumberEntity;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberDeleteService;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddRequest;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddResult;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberDeleteRequest;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberDeleteResult;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
///**
// * @author: cj
// * @description 长隐号删除
// * @date: 2019/4/4 11:46
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LongVirtualNumberDeleteServiceTest {
//    @Autowired
//    private IVirtualNumberDeleteService<LongVirtualNumberDeleteRequest,LongVirtualNumberDeleteResult> virtualNumberDeleteServiceImpl;
//    @Autowired
//    private IVirtualNumberAddService<LongVirtualNumberAddRequest,LongVirtualNumberAddResult> virtualNumberAddServiceImpl;
//    @MockBean
//    private IVirtualNumberRepository virtualNumberRepository;
//    /**
//     * 批量删除长隐号
//     */
//    @Test
//    @Rollback
//    @Transactional
//    public void deleteVirtualNumber(){
//        List<String> virtualNumberList = new ArrayList<>();
//        virtualNumberList.add("3434433");
//        LongVirtualNumberDeleteRequest longVirtualNumberDeleteRequest = new LongVirtualNumberDeleteRequest();
//        longVirtualNumberDeleteRequest.setVirtualNumberList(virtualNumberList);
//
//        String virtualNumber = "3434433";
//        Integer virtualNumberType = 1;
//
//        VirtualNumberEntity virtualNumberEntity = new VirtualNumberEntity();
//        virtualNumberEntity.setId(1);
//        virtualNumberEntity.setVirtualNumber("3434433");
//        virtualNumberEntity.setBindingState(0);
//        virtualNumberEntity.setIsdelete(false);
//        virtualNumberEntity.setIsHistory(false);
//        virtualNumberEntity.setModifytime(new Date());
//        virtualNumberEntity.setCreatetime(new Date());
//
//        VirtualNumberEntity virtualNumberEntity1 = new VirtualNumberEntity();
//        virtualNumberEntity1.setId(1);
//        virtualNumberEntity1.setVirtualNumber("3434433");
//        virtualNumberEntity1.setBindingState(0);
//        virtualNumberEntity1.setIsdelete(true);
//        virtualNumberEntity1.setIsHistory(false);
//        virtualNumberEntity1.setModifytime(new Date());
//        virtualNumberEntity1.setCreatetime(new Date());
//
//        when(virtualNumberRepository.findByVirtualNumberAndUtypeAndIsdeleteIsFalse(virtualNumber,virtualNumberType)).thenReturn(virtualNumberEntity);
//        when(virtualNumberRepository.save(any())).thenReturn(virtualNumberEntity1);
//
//        LongVirtualNumberDeleteResult longVirtualNumberDeleteResult = virtualNumberDeleteServiceImpl.deleteVirtualNumber(longVirtualNumberDeleteRequest);
//        Assert.assertTrue(longVirtualNumberDeleteResult != null);
//    }
//}
