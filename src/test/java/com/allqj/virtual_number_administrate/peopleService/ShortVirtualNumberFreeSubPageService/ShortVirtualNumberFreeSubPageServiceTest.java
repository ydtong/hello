package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberFreeSubPageService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberFreeSubPageRequest;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberFreeSubPageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: cj
 * @description 短号空闲列表
 * @date: 2019/4/4 15:27
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortVirtualNumberFreeSubPageServiceTest {
    @Autowired
    private IVirtualNumberSubPageService<ShortVirtualNumberFreeSubPageRequest,ShortVirtualNumberFreeSubPageResult> virtualNumberSubPageServiceImpl;
    @Test
    public void subPage(){
        ShortVirtualNumberFreeSubPageRequest request = new ShortVirtualNumberFreeSubPageRequest();
        PageVO pageVO = new PageVO();
        PageResult<ShortVirtualNumberFreeSubPageResult> shortVirtualNumberFreeSubPageResultPageResult =
                virtualNumberSubPageServiceImpl.subPage(request,pageVO);
        System.out.println("+++++++++++++++++++++");
        System.out.println(shortVirtualNumberFreeSubPageResultPageResult.getContent().size());
    }
}*/
