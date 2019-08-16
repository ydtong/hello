package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberOccupySubPageService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
import com.allqj.virtual_number_administrate.business.vo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;

/**
 * @author: cj
 * @description 短号占用列表
 * @date: 2019/4/4 16:01
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortVirtualNumberOccupySubPageServiceTest {
    @Autowired
    private IVirtualNumberSubPageService<ShortVirtualNumberBindingSubPageRequest, ShortVirtualNumberBindingSubPageResult> virtualNumberSubPagserviceImpl;

    *//**
     * 获得占用列表
     *//*
    @Test
    public void subPage(){
        ShortVirtualNumberBindingSubPageRequest request = new ShortVirtualNumberBindingSubPageRequest();
        PageVO pageVO = new PageVO();
        PageResult<ShortVirtualNumberBindingSubPageResult> shortVirtualNumberOccupySubPageResultPageResult =
                virtualNumberSubPagserviceImpl.subPage(request,pageVO);
        Assert.assertTrue(shortVirtualNumberOccupySubPageResultPageResult != null);
    }
    *//**
     * 表头信息
     *//*
    @Test
    public void pageHeaders(){
        List<PageHeadersResult> pageHeadersResults = virtualNumberSubPagserviceImpl.pageHeaders();
        Assert.assertThat(pageHeadersResults.size(),is(4));
    }
}*/
