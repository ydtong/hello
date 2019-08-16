//package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberOccupySubPageService;
//
//import com.allqj.qjf.common.vo.PageVO;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberFreeSubPageRequest;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberBindingSubPageResult;
//import com.allqj.virtual_number_administrate.business.vo.PageResult;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.hamcrest.core.Is.is;
//
///**
// * @author: cj
// * @description 查询长隐号占用列表
// * @date: 2019/4/4 14:11
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LongVirtualNumberOccupySubPageServiceTest {
//    @Autowired
//    private IVirtualNumberSubPageService<LongVirtualNumberFreeSubPageRequest, LongVirtualNumberBindingSubPageResult> virtualNumberOccupySubPageServiceImpl;
//
//    /**
//     * 占用列表
//     */
////    @Test
////    public void subPage(){
////        LongVirtualNumberFreeSubPageRequest request = new LongVirtualNumberFreeSubPageRequest();
////        PageVO pageVO = new PageVO();
////        PageResult<LongVirtualNumberBindingSubPageResult> longVirtualNumberOccupySubPageResultPageResult =
////                virtualNumberOccupySubPageServiceImpl.subPage(request,pageVO);
////        Assert.assertTrue(longVirtualNumberOccupySubPageResultPageResult != null);
////    }
//    /**
//     * 表头信息
//     */
//  /*  @Test
//    public void pageHeaders(){
//        List<PageHeadersResult> pageHeadersResults = virtualNumberOccupySubPageServiceImpl.pageHeaders();
//        Assert.assertThat(pageHeadersResults.size(),is(3));
//    }*/
//}
