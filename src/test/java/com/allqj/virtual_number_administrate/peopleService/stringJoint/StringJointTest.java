//package com.allqj.virtual_number_administrate.peopleService.stringJoint;
//
//import com.allqj.virtual_number_administrate.business.repository.pzhframeBaseOrganization.IDepartmentSuperiorRelationRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.hamcrest.core.IsEqual.equalTo;
//
///**
// * @author: cj
// * @description 单元测试dao接口
// * @date: 2019/4/2 14:08
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StringJointTest {
//    @Autowired
//    private IDepartmentSuperiorRelationRepository departmentSuperiorRelationRepository;
//
//    @Test
//    public void numberTest() {
//        List<String> mainPeopleName = departmentSuperiorRelationRepository.queryMainPeopleNameByDepartmentid(43);
//        String middle = null;
//        String mainPeople = mainPeopleName.get(0);
//        String index = " " + "-" + " ";
//        for (int i = 1; i < mainPeopleName.size(); i++) {
//           middle = mainPeople + index;
//           mainPeople = middle + mainPeopleName.get(i);
//        }
//
//        Assert.assertThat(mainPeople,equalTo("郭阳 - 张鹏"));
//    }
//}
