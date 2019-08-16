package com.allqj.virtual_number_administrate.peopleService.ControllerTest;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddResult;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberExcelAddRequest;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberAddRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * @author: cj
 * @description 长隐号Controller集成测试
 * @date: 2019/4/8 18:18
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class LongVirtualNumberControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private  IVirtualNumberAddService<LongVirtualNumberAddRequest, LongVirtualNumberExcelAddRequest,LongVirtualNumberAddResult> longVirtualNumberAddServiceImpl;
    @MockBean
    private IAddBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberAddToEsBaseServiceImpl;
    @MockBean
    private IAddBaseService<VirtualNumberAddRequest, VirtualNumberMysqlEntity> addBaseServiceImpl;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addLongVirtualNumber() throws Exception {
        LongVirtualNumberAddRequest longVirtualNumberAddRequest = new LongVirtualNumberAddRequest();
        longVirtualNumberAddRequest.setVirtualNumber("12223323828");
        //设置参数
        LongVirtualNumberAddResult longVirtualNumberAddResult = new LongVirtualNumberAddResult();
        longVirtualNumberAddResult.setVirtualNumber("12223323828");
        longVirtualNumberAddResult.setIsHistory(false);
        longVirtualNumberAddResult.setDeptName(null);
        longVirtualNumberAddResult.setModifytime(new Date());
        longVirtualNumberAddResult.setCreatetime(new Date());
        longVirtualNumberAddResult.setIsAssign(false);
        longVirtualNumberAddResult.setIsBinding(false);

        VirtualNumberMysqlEntity virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
        VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
        BeanUtils.copyProperties(virtualNumberMysqlEntity,virtualNumberMysqlEntity);
        Mockito.when(longVirtualNumberAddServiceImpl.add(longVirtualNumberAddRequest)).thenReturn(longVirtualNumberAddResult);
        when(addBaseServiceImpl.add(longVirtualNumberAddRequest,VirtualNumberTypeEnum.LONG.getCode())).thenReturn(virtualNumberMysqlEntity);
        when(virtualNumberAddToEsBaseServiceImpl.add(virtualNumberMysqlEntity, VirtualNumberTypeEnum.LONG.getCode())).thenReturn(virtualNumberEsEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/longVirtualNumber/virtualNumber")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}*/
