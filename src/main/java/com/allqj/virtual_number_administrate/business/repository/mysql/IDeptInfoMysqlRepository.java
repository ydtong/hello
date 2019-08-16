package com.allqj.virtual_number_administrate.business.repository.mysql;

import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.DeptPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeptInfoMysqlRepository extends JpaRepository<DeptInfoMysqlEntity, Integer> {
    DeptInfoMysqlEntity findByDeptIdAndDeptTypeAndIsdelete(Integer deptId, String deptType, Boolean isdelete);

    @Query(value = "select t  " +
            "from DeptInfoMysqlEntity as t " +
            "where t.isdelete = false " +
            "and (:#{#deptPageRequest.isFreeShortNumber} is null or t.isFreeShortNumber = :#{#deptPageRequest.isFreeShortNumber}) " +
            "and (:#{#deptPageRequest.isFreeLongNumber} is null or t.isFreeLongNumber = :#{#deptPageRequest.isFreeLongNumber}) " +
            "and (:#{#deptPageRequest.deptIds} is null or t.deptId in :#{#deptPageRequest.deptIds}) " +
            "and (:#{#deptPageRequest.deptType} is null or t.deptType = :#{#deptPageRequest.deptType}) " +
            "and (:#{#deptPageRequest.startTime} is null or t.modifytime >= :#{#deptPageRequest.startTime}) " +
            "and (:#{#deptPageRequest.endTime} is null or t.modifytime <= :#{#deptPageRequest.endTime}) " +
            "and (:#{#deptPageRequest.vagueParameter} is null or t.deptName like %:#{#deptPageRequest.vagueParameter}% )")
    Page<DeptInfoMysqlEntity> page(@Param("deptPageRequest") DeptPageRequest deptPageRequest, Pageable pageable);
}
