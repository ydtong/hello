//package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberDeleteService;
//
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.IVirtualNumberRepository;
//import com.allqj.virtual_number_administrate.business.repository.virtualNumberInfo.entity.VirtualNumberEntity;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberDeleteService;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddRequest;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddResult;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberDeleteRequest;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberDeleteResult;
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
// * @description 短隐号删除
// * @date: 2019/4/4 15:08
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ShortVirtualNumberDeleteServiceTest {
//    @Autowired
//    private IVirtualNumberDeleteService<ShortVirtualNumberDeleteRequest,ShortVirtualNumberDeleteResult> virtualNUmberDeleteServiceImpl;
//    @Autowired
//    private IVirtualNumberAddService<ShortVirtualNumberAddRequest,ShortVirtualNumberAddResult> virtualNumberAddServiceImpl;
//
//    /**
//     * 短号批量删除
//     */
//    @Test
//    @Rollback
//    @Transactional
//    public void deleteVirtualNumber(){
//        ShortVirtualNumberAddRequest shortVirtualNumberAddRequest = new ShortVirtualNumberAddRequest();
//        shortVirtualNumberAddRequest.setAccountCode(1);
//        shortVirtualNumberAddRequest.setSonVirtualNumber("372232784");
//        shortVirtualNumberAddRequest.setVirtualNumber("237211217");
//        virtualNumberAddServiceImpl.add(shortVirtualNumberAddRequest);
//        ShortVirtualNumberAddRequest shortVirtualNumberAddRequest1 = new ShortVirtualNumberAddRequest();
//        shortVirtualNumberAddRequest1.setAccountCode(1);
//        shortVirtualNumberAddRequest1.setSonVirtualNumber("378212784");
//        shortVirtualNumberAddRequest1.setVirtualNumber("237356587");
//        virtualNumberAddServiceImpl.add(shortVirtualNumberAddRequest1);
//
//        ShortVirtualNumberDeleteRequest shortVirtualNumberDeleteRequest = new ShortVirtualNumberDeleteRequest();
//        List<String> virtualNumberList = new ArrayList<>();
//        virtualNumberList.add("237211217");
//        virtualNumberList.add("237356587");
//        shortVirtualNumberDeleteRequest.setVirtualNumberList(virtualNumberList);
//
//        ShortVirtualNumberDeleteResult shortVirtualNumberDeleteResult =
//                virtualNUmberDeleteServiceImpl.deleteVirtualNumber(shortVirtualNumberDeleteRequest);
//        Assert.assertThat(shortVirtualNumberDeleteResult.getVirtualNumberList().size(),is(2));
//    }
//}
