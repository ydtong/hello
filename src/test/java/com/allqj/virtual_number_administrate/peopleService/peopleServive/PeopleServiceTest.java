package com.allqj.virtual_number_administrate.peopleService.peopleServive;


import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.*;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: cj
 * @description 调用人才库接口单元测试
 * @date: 2019/4/3 11:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceTest {

    @Test
    public void test(){
        //拼接参数
        ResultVO<DepartmentPageVO> pageResultResultVO = new ResultVO<>();
        DepartmentPageVO departmentPageVO1 = new DepartmentPageVO();
        List<OrganizationDepartmentPageResult> organizationDepartmentPageResultList = new ArrayList<>();
        List<MainPeopleName> superior = new ArrayList<>();
        MainPeopleName mainPeopleName1 = new MainPeopleName(1246,"郭阳");
        MainPeopleName mainPeopleName2 = new MainPeopleName(1248,"张鹏");
        superior.add(mainPeopleName1);
        superior.add(mainPeopleName2);
        Date data = new Date();
        OrganizationDepartmentPageResult organizationDepartmentPageResult = new OrganizationDepartmentPageResult(43,"网络部","职能",false,1,"千家公司",superior,data,true,true);
        organizationDepartmentPageResultList.add(organizationDepartmentPageResult);
        departmentPageVO1.setTotalCount(8L);
        departmentPageVO1.setPageNum(1);
        departmentPageVO1.setList(organizationDepartmentPageResultList);
        pageResultResultVO.setStatusCode(1000);
        pageResultResultVO.setMessage("OK");
        pageResultResultVO.setResult(departmentPageVO1);
        //拼接结果
        DepartmentPageVO departmentPageVO= pageResultResultVO.getResult();
        List<OrganizationDepartmentPageResult> organizationDepartmentPageResults= departmentPageVO.getList();
        Long employeesNumber = departmentPageVO.getTotalCount();
        List<MainPeopleName> personInCharges = organizationDepartmentPageResults.get(0).getSuperior();
        String mainPeopleName = null;
        if(personInCharges.size() == 1)
            mainPeopleName = personInCharges.get(0).getName();
        if(personInCharges.size() >1){
            //斐波那契数列拼接字符串
            String middle = null;
            String mainPeople = personInCharges.get(0).getName();
            String index = " " + "-" + " ";
            for (int i = 1; i < personInCharges.size(); i++) {
                middle = mainPeople + index;
                mainPeople = middle + personInCharges.get(i).getName();
            }
            mainPeopleName = mainPeople;
        }
        Assert.assertTrue(employeesNumber > 0);
        Assert.assertTrue(mainPeopleName != null);

    }

 /*   @Test
    public void testDept(){
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        queryByDeptIdRequest.setDeptId(43);
        queryByDeptIdRequest.setDeptType("0001");
        ResultVO<OrganizationQueryByDeptIdResult> result = peopleServiceClient.departmentQueryByDeptId(queryByDeptIdRequest);
        System.out.println("++++++++++++++++++++");
        System.out.println(result.getResult().getDeptName());
        System.out.println(result.getResult().getNumberHold());
        System.out.println("+++++++++++++++++++++");
    }*/
}
