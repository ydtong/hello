package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberHistoryService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberHistoryService;
import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberHistoryRequest;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberHistoryResult;
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
 * @description TODO
 * @date: 2019/4/4 15:33
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortVirtualNumberHistoryServiceTest {
    @Autowired
    private IVirtualNumberHistoryService<ShortVirtualNumberHistoryRequest, ShortVirtualNumberHistoryResult> virtualNumberHistoryServiceImpl;*/

    /**
     * 查询绑定历史
     */
 /*   @Test
    public void historyVirtualNumber(){
        ShortVirtualNumberHistoryRequest shortVirtualNumberHistoryRequest = new ShortVirtualNumberHistoryRequest();
        shortVirtualNumberHistoryRequest.setVirtualNumber("45345");
        shortVirtualNumberHistoryRequest.setVirtualNumberType(0);

        List<ShortVirtualNumberHistoryResult> shortVirtualNumberHistoryResultList =
                virtualNumberHistoryServiceImpl.historyVirtualNumber(shortVirtualNumberHistoryRequest);
        Assert.assertThat(shortVirtualNumberHistoryResultList.get(0).getUserName(),is("李四"));
    }*/
    /**
     * 获得绑定历史表头
     */
  /*  @Test
    public void pageHeaders(){
        List<PageHeadersResult> pageHeadersResults = virtualNumberHistoryServiceImpl.pageHeaders();
        Assert.assertThat(pageHeadersResults.size(),is(5));
    }
}*/
