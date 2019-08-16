package com.allqj.virtual_number_administrate.peopleService.AssignService;

import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IDeptInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IAssignService;
import com.allqj.virtual_number_administrate.business.vo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: cj
 * @description 分配单元测试
 * @date: 2019/4/4 8:57
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AssignServiceTest {
//    @Autowired
//    private IAssignService assignServiceImpl;
//    @MockBean
//    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;
//    @MockBean
//    private IDeptInfoMysqlRepository deptInfoMysqlRepository;
//    @MockBean
//    private PeopleService peopleServiceClient;
//    @MockBean
//    private IVirtualNumberEsRepository virtualNumberEsRepository;
//    @MockBean
//    private IDeptInfoEsRepository deptInfoEsRepository;
//    /**
//     * 分配部门
//     **/
//    @Test
//    public void assignDept(){
//        //设置人才库部门服务返回结果
//        ResultVO<OrganizationQueryByDeptIdResult> deptIdResultResultVO = getPeopleClientResult();
//        //更新隐号状态
//        VirtualNumberMysqlEntity virtualNumberMysqlEntity = getVirtualNumberMysqlEntity(null,null,false);
//        VirtualNumberMysqlEntity saveVirtualNumberMysqlEntity = getVirtualNumberMysqlEntity(43,"0001",true);
//        Mockito.when(virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete("232323243",0,false)).thenReturn(virtualNumberMysqlEntity);
//        Mockito.when(virtualNumberMysqlRepository.save(virtualNumberMysqlEntity)).thenReturn(saveVirtualNumberMysqlEntity);
//        //更新部门信息
//        DeptInfoMysqlEntity deptInfoMysqlEntity = getDeptInfoMysqlEntity(false);
//
//        Mockito.when(deptInfoMysqlRepository.findByDeptIdAndDeptTypeAndIsdelete(43,"0001",false)).thenReturn(deptInfoMysqlEntity);
//        Mockito.when(peopleServiceClient.departmentQueryByDeptId(any())).thenReturn(deptIdResultResultVO);
//        //更新ES隐号状态
//        VirtualNumberEsEntity virtualNumberEsEntity = getVirtualNumberEsEntity(null,null,false);
//        VirtualNumberEsEntity saveVirtualNumberEsEntity = getVirtualNumberEsEntity(43,"0001",true);
//        Optional<VirtualNumberEsEntity> virtualNumberEsEntityOptional = Optional.of(virtualNumberEsEntity);
//        Mockito.when(virtualNumberEsRepository.findById(any())).thenReturn(virtualNumberEsEntityOptional);
//        Mockito.when(virtualNumberEsRepository.save(any())).thenReturn(saveVirtualNumberEsEntity);
//        //更新ES部门状态
//        DeptInfoEsEntity deptInfoEsEntity = getDeptInfoESEntity(false);
//        DeptInfoEsEntity saveDeptInfoESEntity = getDeptInfoESEntity(true);
//        Optional<DeptInfoEsEntity> deptInfoEsEntityOptional =Optional.of(deptInfoEsEntity);
//        Mockito.when(deptInfoEsRepository.findById(any())).thenReturn(deptInfoEsEntityOptional);
//        Mockito.when(deptInfoEsRepository.save(any())).thenReturn(saveDeptInfoESEntity);
//        //设置分配部门请求
//        AssignDeptRequest assignDeptRequest = new AssignDeptRequest();
//        List<String> virtualNumberList = new ArrayList<>();
//        virtualNumberList.add("232323243");
//        assignDeptRequest.setVirtualNumberList(virtualNumberList);
//        assignDeptRequest.setVirtualNumberType(0);
//        assignDeptRequest.setDeptId(559);
//        assignDeptRequest.setDeptType("0003");
//        AssignDeptResult assignDeptResult = assignServiceImpl.assignDept(assignDeptRequest);
//        Assert.assertTrue(assignDeptRequest != null);
//    }
//
//    /**
//     * 取消部门分配
//     */
//    @Test
//    public void cancelAssignDept(){
//        CancelAssignDeptRequest cancelAssignDeptRequest = new CancelAssignDeptRequest();
//        List<String> virtualNumberList = new ArrayList<>();
//        virtualNumberList.add("232323243");
//        cancelAssignDeptRequest.setVirtualNumberList(virtualNumberList);
//        cancelAssignDeptRequest.setVirtualNumberType(0);
//        CancelAssignDeptResult cancelAssignDeptResult = assignServiceImpl.cancelAssignDept(cancelAssignDeptRequest);
//        Assert.assertThat(cancelAssignDeptResult.getVirtualNumberList(),hasItem("232323243"));
//    }
//
//   /**
//     * 按数量分配
//     */
//    @Test
//    public void numberAssign(){
//        NumberAssignRequest numberAssignRequest = new NumberAssignRequest();
//        numberAssignRequest.setNumber(1);
//        numberAssignRequest.setVirtualNumberType(0);
//        numberAssignRequest.setDeptId(489);
//        numberAssignRequest.setDeptType("0005");
//        NumberAssignResult numberAssignResult = assignServiceImpl.numberAssign(numberAssignRequest);
//        Assert.assertThat(numberAssignResult.getVirtualNumberList().size(),is(3));
//    }
//
//   /**
//     * 按数量取消分配
//     */
//    @Test
//    public void cancelNumberAssign(){
//        CancelNumberAssignRequest cancelNumberAssignRequest = new CancelNumberAssignRequest();
//        cancelNumberAssignRequest.setNumber(232323243);
//        cancelNumberAssignRequest.setVirtualNumberType(0);
//        cancelNumberAssignRequest.setDeptId(559);
//        cancelNumberAssignRequest.setDeptType("0003");
//        CancelNumberAssignResult cancelNumberAssignResult = assignServiceImpl.cancelNumberAssign(cancelNumberAssignRequest);
//        Assert.assertThat(cancelNumberAssignResult.getVirtualNumberList().size(),is(1));
//    }
//
//    /**
//     * 人才库部门服务返回结果
//     * @return
//     */
//    private ResultVO<OrganizationQueryByDeptIdResult> getPeopleClientResult(){
//        //设置人才库部门服务返回结果
//        OrganizationQueryByDeptIdResult queryByDeptIdResult = new OrganizationQueryByDeptIdResult();
//        List<String> superior = new ArrayList<>();
//        superior.add("AAAA");
//        superior.add("BBBB");
//        queryByDeptIdResult.setNumberHold(60);
//        queryByDeptIdResult.setDeptName("网络部");
//        queryByDeptIdResult.setDeptSuperior(superior);
//        ResultVO<OrganizationQueryByDeptIdResult> deptIdResultResultVO = new ResultVO<>();
//        deptIdResultResultVO.setMessage("ok");
//        deptIdResultResultVO.setStatusCode(1000);
//        deptIdResultResultVO.setResult(queryByDeptIdResult);
//        return deptIdResultResultVO;
//    }
//
//   /**
//     * mySql虚拟号实例化
//     * @param deptId
//     * @param deptType
//     * @param isAssign
//     * @return
//     */
//    private VirtualNumberMysqlEntity getVirtualNumberMysqlEntity(Integer deptId,String deptType,Boolean isAssign){
//        VirtualNumberMysqlEntity virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
//        virtualNumberMysqlEntity.setId(1);
//        virtualNumberMysqlEntity.setVirtualNumber("232323243");
//        virtualNumberMysqlEntity.setUtype(0);
//        virtualNumberMysqlEntity.setDeptId(deptId);
//        virtualNumberMysqlEntity.setDeptType(deptType);
//        virtualNumberMysqlEntity.setIsBinding(false);
//        virtualNumberMysqlEntity.setIsAssign(isAssign);
//        virtualNumberMysqlEntity.setIsdelete(false);
//        return virtualNumberMysqlEntity;
//    }
//
//   /**
//     * mySql部门实例化
//     * @param shortNumber
//     * @return
//     */
//    private DeptInfoMysqlEntity getDeptInfoMysqlEntity(Boolean shortNumber){
//        DeptInfoMysqlEntity deptInfoMysqlEntity = new DeptInfoMysqlEntity();
//        deptInfoMysqlEntity.setId(1);
//        deptInfoMysqlEntity.setDeptId(43);
//        deptInfoMysqlEntity.setDeptType("0001");
//        deptInfoMysqlEntity.setIsdelete(false);
//        deptInfoMysqlEntity.setCreatetime(new Date());
//        deptInfoMysqlEntity.setModifytime(new Date());
//        deptInfoMysqlEntity.setIsFreeShortNumber(shortNumber);
//        deptInfoMysqlEntity.setIsFreeLongNumber(false);
//        return deptInfoMysqlEntity;
//    }
//    /**
//     * ES虚拟号实例化
//     * @param deptId
//     * @param deptType
//     * @param isAssign
//     * @return
//     */
//    private VirtualNumberEsEntity getVirtualNumberEsEntity(Integer deptId,String deptType,Boolean isAssign){
//        VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
//        virtualNumberEsEntity.setId(1);
//        virtualNumberEsEntity.setAccountCode(1);
//        virtualNumberEsEntity.setDeptId(deptId);
//        virtualNumberEsEntity.setDeptType(deptType);
//        virtualNumberEsEntity.setCreatetime(new Date());
//        virtualNumberEsEntity.setModifytime(new Date());
//        virtualNumberEsEntity.setIsAssign(isAssign);
//        virtualNumberEsEntity.setIsBinding(false);
//        virtualNumberEsEntity.setIsHistory(false);
//        virtualNumberEsEntity.setIsdelete(false);
//        return virtualNumberEsEntity;
//    }
//   /**
//    * ES部门实例化
//    * @param shortNumber
//    * @return
//    */
//    private DeptInfoEsEntity getDeptInfoESEntity(Boolean shortNumber){
//        DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
//        deptInfoEsEntity.setId(1);
//        deptInfoEsEntity.setDeptId(43);
//        deptInfoEsEntity.setDeptType("0001");
//        deptInfoEsEntity.setIsdelete(false);
//        deptInfoEsEntity.setCreatetime(new Date());
//        deptInfoEsEntity.setModifytime(new Date());
//        deptInfoEsEntity.setIsFreeShortNumber(shortNumber);
//        deptInfoEsEntity.setIsFreeLongNumber(false);
//        return deptInfoEsEntity;
//    }
//}
