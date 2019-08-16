package com.allqj.virtual_number_administrate.peopleService.DeptService;

import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdRequest;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IDeptService;
import com.allqj.virtual_number_administrate.business.vo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author: cj
 * @description 部门管理单元测试
 * @date: 2019/4/4 10:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptServiceTest {
    @Autowired
    private IDeptService deptServiceImpl;
    @MockBean
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;
    @MockBean
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;
    @MockBean
    private PeopleService peopleServiceClient;
    /**
     * 部门列表
     */
    @Test
    public void page(){
        //设置列表请求参数
        DeptPageRequest deptPageRequest = new DeptPageRequest();
        PageVO pageVO = new PageVO();
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = new PageRequest(pageVO.getPage() - 1, pageVO.getSize(), sort);
        //设置人才库部门服务请求参数
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        //设置查询本地Mysql虚拟号参数
        Integer deptId = 43;
        String deptType = "0001";
        Integer longVirtualType = 1;
        Integer shortVirtualType = 0;
        //查询部门列表返回结果
        Page<DeptInfoMysqlEntity> result = null;
        result = new PageDept();
        //设置人才库部门服务返回结果
        OrganizationQueryByDeptIdResult queryByDeptIdResult = new OrganizationQueryByDeptIdResult();
        List<String> superior = new ArrayList<>();
        superior.add("AAAA");
        superior.add("BBBB");
        queryByDeptIdResult.setNumberHold(60);
        queryByDeptIdResult.setDeptName("网络部");
        queryByDeptIdResult.setDeptSuperior(superior);
        ResultVO<OrganizationQueryByDeptIdResult> deptIdResultResultVO = new ResultVO<>();
        deptIdResultResultVO.setMessage("OK");
        deptIdResultResultVO.setStatusCode(1000);
        deptIdResultResultVO.setResult(queryByDeptIdResult);
        //设置查询本地Mysql虚拟号返回结果
        Integer longBindingNumber = 1;
        Integer longNotBindingNumber = 0;
        Integer shortBindingNumber = 0;
        Integer shortNotBindingNumber =0;
        //mock方法
        Mockito.when(deptInfoMysqlRepository.page(deptPageRequest,pageable)).thenReturn(result);
        Mockito.when(peopleServiceClient.departmentQueryByDeptId(any())).thenReturn(deptIdResultResultVO);
        Mockito.when(virtualNumberMysqlRepository.countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptId,deptType,longVirtualType,true)).thenReturn(longBindingNumber);
        Mockito.when(virtualNumberMysqlRepository.countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptId,deptType,longVirtualType,false)).thenReturn(longNotBindingNumber);
        Mockito.when(virtualNumberMysqlRepository.countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptId,deptType,shortVirtualType,true)).thenReturn(shortBindingNumber);
        Mockito.when(virtualNumberMysqlRepository.countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptId,deptType,shortVirtualType,false)).thenReturn(shortNotBindingNumber);
        //查询列表
        PageResult<DeptPageResult> pageResultPageResult = deptServiceImpl.page(deptPageRequest,pageVO);
        Assert.assertTrue(pageResultPageResult.getContent() != null);
    }

    /**
     * 获得部门表头
     */
    @Test
    public void pageHeaders(){
        List<PageHeadersResult> pageHeadersResults = deptServiceImpl.pageHeaders();
        Assert.assertTrue(pageHeadersResults != null);
    }
}
