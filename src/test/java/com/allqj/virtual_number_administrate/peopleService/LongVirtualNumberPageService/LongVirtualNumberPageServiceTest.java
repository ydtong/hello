//package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberPageService;
//
//import com.allqj.qjf.common.vo.PageVO;
//import com.allqj.virtual_number_administrate.business.service.IVirtualNumberPageService;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberPageRequest;
//import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberPageResult;
//import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;
//import com.allqj.virtual_number_administrate.business.vo.PageResult;
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
// * @description 长隐号列表
// * @date: 2019/4/4 14:29
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LongVirtualNumberPageServiceTest {
//    @Autowired
//    private IVirtualNumberPageService<LongVirtualNumberPageRequest,LongVirtualNumberPageResult> virtualNumberPageServiceImpl;
//
//    /**
//     * 长隐号列表
//     */
//    @Test
//    public void page(){
//        LongVirtualNumberPageRequest pageRequest = new LongVirtualNumberPageRequest();
//        PageVO pageVO =new PageVO();
//        PageResult<LongVirtualNumberPageResult> longVirtualNumberPageResultPageResult =
//                virtualNumberPageServiceImpl.page(pageRequest,pageVO);
//        Assert.assertTrue(longVirtualNumberPageResultPageResult != null);
//    }
//
//    /**
//     * 长隐号表头
//     */
// /*   @Test
//    public void pageHeaders(){
//        List<PageHeadersResult> pageHeadersResults = virtualNumberPageServiceImpl.pageHeaders();
//        Assert.assertThat(pageHeadersResults.size(),is(5));
//    }*/
//}
