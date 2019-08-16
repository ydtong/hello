package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberFreeSubPageService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberFreeSubPageRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberFreeSubPageResult;
import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author: cj
 * @description 查询长隐号空闲列表
 * @date: 2019/4/4 13:32
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class LongVirtualNumberFreeSubPageServiceTest {
    @Autowired
    private IVirtualNumberSubPageService<LongVirtualNumberFreeSubPageRequest,LongVirtualNumberFreeSubPageResult> virtualNumberSubPageServiceImpl;

    *//**
     * 虚拟号子列表
     *//*
    @Test
    public void subPage(){
        LongVirtualNumberFreeSubPageRequest request = new LongVirtualNumberFreeSubPageRequest();
        PageVO pageVO = new PageVO();
        PageResult<LongVirtualNumberFreeSubPageResult> longVirtualNumberFreeSubPageResultPageResult =
                virtualNumberSubPageServiceImpl.subPage(request,pageVO);
        Assert.assertTrue(longVirtualNumberFreeSubPageResultPageResult != null);
    }

    *//**
     * 表头信息
     *//*
    @Test
    public void pageHeaders(){
        List<PageHeadersResult> pageHeadersResults = virtualNumberSubPageServiceImpl.pageHeaders();
        Assert.assertThat(pageHeadersResults.size(),is(2));
    }
}*/
