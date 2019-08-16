//package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberAddService;
//
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.IVirtualNumberRepository;
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.entity.VirtualNumberEntity;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddRequest;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddResult;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//
///**
// * @author: cj
// * @description 短隐号添加
// * @date: 2019/4/4 14:43
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ShortVirtualNumberAddServiceTest {
//    @Autowired
//    private IVirtualNumberAddService<ShortVirtualNumberAddRequest,ShortVirtualNumberAddResult> virtualNumberAddServiceImpl;
//
//    /**
//     * 短隐号添加
//     * 1.创建添加数据
//     * 2.mock掉Repository
//     * 3.测试service方法
//     * 4.断言
//     */
//    @Test
//    @Rollback
//    @Transactional
//    public void add(){
//        ShortVirtualNumberAddRequest shortVirtualNumberAddRequest = new ShortVirtualNumberAddRequest();
//        shortVirtualNumberAddRequest.setAccountCode(1);
//        shortVirtualNumberAddRequest.setSonVirtualNumber("373232784");
//        shortVirtualNumberAddRequest.setVirtualNumber("232327387");
//
//        IVirtualNumberRepository virtualNumberRepository = Mockito.mock(IVirtualNumberRepository.class);
//        VirtualNumberEntity virtualNumberEntity = Mockito.mock(VirtualNumberEntity.class);
//        Mockito.when(virtualNumberRepository.save(virtualNumberEntity)).thenReturn(virtualNumberEntity);
//
//        ShortVirtualNumberAddResult shortVirtualNumberAddResult = virtualNumberAddServiceImpl.add(shortVirtualNumberAddRequest);
//        Assert.assertThat(shortVirtualNumberAddResult.getVirtualNumber(),is("232327387"));
//    }
//
//    /**
//     * 短隐号批量添加
//     1.创建添加数据
//     * 2.mock掉Repository
//     * 3.测试service方法
//     * 4.断言
//     */
//   /* @Test
//    @Rollback
//    @Transactional
//    public void addAll(){
//        List<ShortVirtualNumberAddRequest> virtualNumberAddRequestLists = new ArrayList<>();
//        ShortVirtualNumberAddRequest shortVirtualNumberAddRequest = new ShortVirtualNumberAddRequest();
//        shortVirtualNumberAddRequest.setAccountCode(1);
//        shortVirtualNumberAddRequest.setVirtualNumber("234232423");
//        shortVirtualNumberAddRequest.setSonVirtualNumber("2132332");
//
//        ShortVirtualNumberAddRequest shortVirtualNumberAddRequest1 = new ShortVirtualNumberAddRequest();
//        shortVirtualNumberAddRequest1.setAccountCode(1);
//        shortVirtualNumberAddRequest1.setVirtualNumber("32342323");
//        shortVirtualNumberAddRequest1.setSonVirtualNumber("32213232");
//
//        virtualNumberAddRequestLists.add(shortVirtualNumberAddRequest);
//        virtualNumberAddRequestLists.add(shortVirtualNumberAddRequest1);
//        //mock掉repository
//        IVirtualNumberRepository virtualNumberRepository = Mockito.mock(IVirtualNumberRepository.class);
//        VirtualNumberEntity virtualNumberEntity = Mockito.mock(VirtualNumberEntity.class);
//        Mockito.when(virtualNumberRepository.save(virtualNumberEntity)).thenReturn(virtualNumberEntity);
//
//        List<ShortVirtualNumberAddResult> shortVirtualNumberAddResultList =
//                virtualNumberAddServiceImpl.excelAdd(virtualNumberAddRequestLists);
//        Assert.assertThat(shortVirtualNumberAddResultList.size(),is(2));
//    }*/
//}
