//package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberPageService;
//
//import com.allqj.qjf.common.vo.PageVO;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberPageService;
//import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;
//import com.allqj.virtual_number_administrate.business.vo.PageResult;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberPageRequest;
//import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberPageResult;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//
///**
// * @author: cj
// * @description 短隐号列表
// * @date: 2019/4/4 16:13
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ShortVirtualNumberPageServiceImplTest {
//    @Autowired
//    private IVirtualNumberPageService<ShortVirtualNumberPageRequest,ShortVirtualNumberPageResult> virtualNumberPageServiceImpl;
////    @Test
////    public void page(){
////        ShortVirtualNumberPageRequest pageRequest = new ShortVirtualNumberPageRequest();
////        PageVO pageVO = new PageVO();
////        PageResult<ShortVirtualNumberPageResult> pageResultPageResult =
////                virtualNumberPageServiceImpl.page(pageRequest,pageVO);
////        Assert.assertTrue(pageResultPageResult != null);
////    }
//
//    /**
//     * 短隐号表头
//     */
//  /*  @Test
//    public void pageHeaders(){
//        List<PageHeadersResult> pageHeadersResults = virtualNumberPageServiceImpl.pageHeaders();
//        Assert.assertThat(pageHeadersResults.size(),is(7));
//    }*/
//}
