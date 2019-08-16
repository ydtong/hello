package com.allqj.virtual_number_administrate.business.repository.mysql;

import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberPageRequest;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberPageRequest;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberQueryAllRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVirtualNumberMysqlRepository extends JpaRepository<VirtualNumberMysqlEntity, Integer> {
    /**
     * 隐号列表
     *
     * @param virtualNumberPageRequest
     * @param pageable
     * @return
     */
    @Query(value = "select t " +
            "from VirtualNumberMysqlEntity as t " +
            "where t.utype= :virtualNumberType and t.isdelete = false " +
            "and (:#{#virtualNumberPageRequest.accountCode} is null or t.accountCode = :#{#virtualNumberPageRequest.accountCode})" +
            "and (:#{#virtualNumberPageRequest.isAssign} is null or t.isAssign = :#{#virtualNumberPageRequest.isAssign}) " +
            "and (:#{#virtualNumberPageRequest.isBinding} is null or t.isBinding = :#{#virtualNumberPageRequest.isBinding})" +
            "and (:#{#virtualNumberPageRequest.deptIds} is null or t.deptId in :#{#virtualNumberPageRequest.deptIds} ) " +
            "and (:#{#virtualNumberPageRequest.startTime} is null or t.modifytime >= :#{#virtualNumberPageRequest.startTime}) " +
            "and (:#{#virtualNumberPageRequest.endTime} is null or t.modifytime <= :#{#virtualNumberPageRequest.endTime}) " +

            //查询子虚拟号是否为空
            "and " +
            "(:#{#virtualNumberPageRequest.sonVirtualNumberWhere} is null " +
            "    or " +
            "    (" +
            "       ( :#{#virtualNumberPageRequest.sonVirtualNumberWhere} = false and t.sonVirtualNumber is null) " +
            "       or ( :#{#virtualNumberPageRequest.sonVirtualNumberWhere} = true and t.sonVirtualNumber is not null)" +
            "     ) " +
            ")" +

            //加入模糊搜索条件
            "and (:#{#virtualNumberPageRequest.vagueParameter} is null " +
            "or t.virtualNumber like concat('%',:#{#virtualNumberPageRequest.vagueParameter},'%')" +
            "or(:#{#accountCodes} is not null and  t.accountCode in :accountCodes)" +
            " or  t.sonVirtualNumber like concat('%',:#{#virtualNumberPageRequest.vagueParameter},'%') )")
    Page<VirtualNumberMysqlEntity> shortPage(@Param("virtualNumberPageRequest") ShortVirtualNumberPageRequest virtualNumberPageRequest,
                                             @Param("virtualNumberType") Integer virtualNumberType,
                                             @Param("accountCodes") Integer[] accountCodes,
                                             Pageable pageable);

    @Query(value = "select t " +
            "from VirtualNumberMysqlEntity as t " +
            "where t.utype= :virtualNumberType and t.isdelete = false  " +
            "and (:#{#virtualNumberPageRequest.isAssign} is null or t.isAssign = :#{#virtualNumberPageRequest.isAssign}) " +
            "and (:#{#virtualNumberPageRequest.isBinding} is null or t.isBinding = :#{#virtualNumberPageRequest.isBinding})" +
            "and (:#{#virtualNumberPageRequest.deptIds} is null or t.deptId in :#{#virtualNumberPageRequest.deptIds} ) " +
            "and (:#{#virtualNumberPageRequest.startTime} is null or t.modifytime >= :#{#virtualNumberPageRequest.startTime}) " +
            "and (:#{#virtualNumberPageRequest.endTime} is null or t.modifytime <= :#{#virtualNumberPageRequest.endTime})" +
            //加入模糊搜索条件
            "and(:#{#virtualNumberPageRequest.vagueParameter} is null or t.virtualNumber like concat('%',:#{#virtualNumberPageRequest.vagueParameter},'%') )")
    Page<VirtualNumberMysqlEntity> longPage(@Param("virtualNumberPageRequest") LongVirtualNumberPageRequest virtualNumberPageRequest,
                                            @Param("virtualNumberType") Integer virtualNumberType,
                                            Pageable pageable);

    @Query(value = "select t " +
            "from VirtualNumberMysqlEntity as t " +
            "where " +
            "t.deptId = :deptId and " +
            "t.deptType = :deptType and " +
            "t.utype = :virtualNumberType and " +
            "t.isBinding = :isBinding ")
    Page<VirtualNumberMysqlEntity> subPage(@Param("deptId") Integer deptId,
                                           @Param("deptType") String deptType,
                                           @Param("virtualNumberType") Integer virtualNumberType,
                                           @Param("isBinding") Boolean isBinding,
                                           Pageable pageable);

    Integer countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(Integer deptId, String deptType, Integer virtualNumberType, Boolean isBinding);

    @Query(value = "select t.virtualNumber " +
            "from VirtualNumberMysqlEntity as t " +
            "where t.utype = :#{#queryAllRequest.virtualNumberType} and t.isdelete = :isdelete " +
            "and (:#{#queryAllRequest.isAssign} is null or t.isAssign = :#{#queryAllRequest.isAssign}) " +
            "and (:#{#queryAllRequest.isBinding} is null or t.isBinding = :#{#queryAllRequest.isBinding}) " +
            "and (:#{#queryAllRequest.deptId} is null or t.deptId = :#{#queryAllRequest.deptId}) " +
            "and (:#{#queryAllRequest.deptType} is null or t.deptType = :#{#queryAllRequest.deptType}) " +
            " " +
            " ")
    Page<String> virtualNumberList(@Param("queryAllRequest") VirtualNumberQueryAllRequest queryAllRequest, @Param("isdelete") Boolean isdelete, Pageable pageable);

    VirtualNumberMysqlEntity findByVirtualNumberAndUtypeAndIsdelete(String virtualNumber, Integer virtualNumberType, Boolean isdelete);

    Integer countByVirtualNumberAndUtypeAndIsdelete(String virtualNumber, Integer virtualNumberType, Boolean isdelete);

    Integer countBySonVirtualNumberAndUtypeAndIsdeleteFalse(String virtualNumber, Integer virtualNumberType);

    Integer countByAccountCodeAndIsdeleteFalse(Integer accountCode);

    Integer countByAccountCodeAndIsBindingTrueAndIsdeleteFalse(Integer accountCode);

    /**
     * 根据部门id查询
     **/
    List<VirtualNumberMysqlEntity> findAllByDeptIdAndIsdeleteFalse(Integer deptId);

    /**
     * 查询是否有空闲隐号
     **/
    List<VirtualNumberMysqlEntity> findAllByDeptIdAndIsAssignTrueAndIsBindingFalseAndIsdeleteFalseAndUtype(Integer deptId, Integer utype);
}
