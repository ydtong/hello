package com.allqj.virtual_number_administrate.peopleService.LongVirtualNumberHistoryService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberHistoryService;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberHistoryRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberHistoryResult;
import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;
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
 * @date: 2019/4/4 13:44
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class LongVirtualNumberHistoryServiceTest {
    @Autowired
    private IVirtualNumberHistoryService<LongVirtualNumberHistoryRequest, LongVirtualNumberHistoryResult> virtualNUmberHistoryServiceImpl;*/
    /**
     * 查询绑定历史
     */
   /* @Test
    public void historyVirtualNumber(){
        LongVirtualNumberHistoryRequest longVirtualNumberHistoryRequest = new LongVirtualNumberHistoryRequest();
        longVirtualNumberHistoryRequest.setVirtualNumber("123");
        longVirtualNumberHistoryRequest.setVirtualNumberType(1);
        List<LongVirtualNumberHistoryResult> longVirtualNumberHistoryResults = virtualNUmberHistoryServiceImpl.historyVirtualNumber(longVirtualNumberHistoryRequest);
        Assert.assertThat(longVirtualNumberHistoryResults.get(0).getUserName(),is("李四"));
    }
*/
    /**
     * 获得绑定历史表头
     */
  /*  @Test
    public void pageHeaders(){
        List<PageHeadersResult> pageHeadersResults = virtualNUmberHistoryServiceImpl.pageHeaders();
        Assert.assertThat(pageHeadersResults.size(),is(5));
    }
}*/
