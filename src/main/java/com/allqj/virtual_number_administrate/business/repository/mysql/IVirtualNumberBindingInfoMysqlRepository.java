package com.allqj.virtual_number_administrate.business.repository.mysql;

import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBindingQueryBase;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVirtualNumberBindingInfoMysqlRepository extends JpaRepository<VirtualNumberBindingInfoMysqlEntity, Integer> {
    @Query(value = " select t " +
            "from VirtualNumberBindingInfoMysqlEntity as t " +
            "where t.isdelete = true " +
            "and t.virtualNumber = :virtualNumber " +
            "and t.virtualNumberType = :virtualNumberType" +
            "")
    Page<VirtualNumberBindingInfoMysqlEntity> page(@Param("virtualNumber") String virtualNumber, @Param("virtualNumberType") Integer virtualNumberType, Pageable pageable);

    List<VirtualNumberBindingInfoMysqlEntity> findAllByPhoneAndIsdeleteFalse(String phone);

    @Query(value = "select new com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBindingQueryBase(t1,t2) " +
            "from VirtualNumberBindingInfoMysqlEntity as t1 " +
            "join com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity as t2 " +
            "on t1.virtualNumber = t2.virtualNumber " +
            "where t1.isdelete = false and t1.phone = :phone and t1.virtualNumberType = :virtualNumberType ")
    List<VirtualNumberBindingQueryBase> bindingInfo(@Param("phone") String phone, @Param("virtualNumberType") Integer virtualNumberType);

    List<VirtualNumberBindingInfoMysqlEntity> findAllByVirtualNumberAndIsdeleteTrue(String virtualNumber);

    VirtualNumberBindingInfoMysqlEntity findByVirtualNumberAndPhoneAndVirtualNumberTypeAndIsdelete(String virtualNumber, String phone, Integer virtualNumberType, Boolean isdelete);

    VirtualNumberBindingInfoMysqlEntity findByVirtualNumberAndVirtualNumberTypeAndIsdeleteFalse(String virtualNumber, Integer virtualNumberType);

    VirtualNumberBindingInfoMysqlEntity findByPhoneAndIsdeleteFalse(String phone);
}
