//package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberAddService;
//
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.IVirtualNumberRepository;
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.entity.VirtualNumberEntity;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddRequest;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddResult;
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
// * @description 长隐号添加单元测试
// * @date: 2019/4/4 11:33
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LongVirtualNumberAddServiceTest {
//    @Autowired
//    private IVirtualNumberAddService<LongVirtualNumberAddRequest,LongVirtualNumberAddResult> virtualNumberAddServiceImpl;
//    @MockBean
//    private IVirtualNumberRepository virtualNumberRepository;
//
//    /**
//     * 长隐号添加
//     */
//    @Test
//    @Rollback
//    @Transactional
//    public void add(){
//        LongVirtualNumberAddRequest longVirtualNumberAddRequest = new LongVirtualNumberAddRequest();
//        longVirtualNumberAddRequest.setVirtualNumber("34389433");
//
//        VirtualNumberEntity virtualNumberEntity = new VirtualNumberEntity();
//        virtualNumberEntity.setId(1);
//        virtualNumberEntity.setVirtualNumber("34389433");
//        virtualNumberEntity.setBindingState(0);
//        virtualNumberEntity.setIsdelete(false);
//        virtualNumberEntity.setIsHistory(false);
//        virtualNumberEntity.setModifytime(new Date());
//        virtualNumberEntity.setCreatetime(new Date());
//
//        String virtualNumber = null;
//        Integer virtualNumberType = null;
//
//        Mockito.when(virtualNumberRepository.save(any())).thenReturn(virtualNumberEntity);
//        Mockito.when(virtualNumberRepository.findByVirtualNumberAndUtypeAndIsdeleteIsFalse(virtualNumber,virtualNumberType)).thenReturn(virtualNumberEntity);
//
//        LongVirtualNumberAddResult longVirtualNumberAddResult = virtualNumberAddServiceImpl.add(longVirtualNumberAddRequest);
//
//        Assert.assertTrue(longVirtualNumberAddResult != null);
//    }
//
//    /**
//     * 长隐号多个添加
//     */
//    @Test
//    @Rollback
//    @Transactional
//    public void addAll(){
//        List<LongVirtualNumberAddRequest> longVirtualNumberAddRequests = new ArrayList<>();
//
//        LongVirtualNumberAddRequest longVirtualNumberAddRequest = new LongVirtualNumberAddRequest();
//        longVirtualNumberAddRequest.setVirtualNumber("3433345");
//        LongVirtualNumberAddRequest longVirtualNumberAddRequest1 = new LongVirtualNumberAddRequest();
//        longVirtualNumberAddRequest1.setVirtualNumber("543345");
//
//        longVirtualNumberAddRequests.add(longVirtualNumberAddRequest);
//        longVirtualNumberAddRequests.add(longVirtualNumberAddRequest1);
//
//        //mock掉repository
//        IVirtualNumberRepository virtualNumberRepository = Mockito.mock(IVirtualNumberRepository.class);
//        VirtualNumberEntity virtualNumberEntity = Mockito.mock(VirtualNumberEntity.class);
//        when(virtualNumberRepository.save(virtualNumberEntity)).thenReturn(virtualNumberEntity);
//
//        List<LongVirtualNumberAddResult> longVirtualNumberAddResultList = virtualNumberAddServiceImpl.excelAdd(longVirtualNumberAddRequests);
//        Assert.assertThat(longVirtualNumberAddResultList.size(),is(2));
//    }
//
//}
