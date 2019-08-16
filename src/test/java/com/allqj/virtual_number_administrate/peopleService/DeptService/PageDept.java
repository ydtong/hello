package com.allqj.virtual_number_administrate.peopleService.DeptService;

import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.DeptPageResult;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @author: cj
 * @description TODO
 * @date: 2019/4/8 16:52
 **/

public class PageDept implements Page<DeptInfoMysqlEntity> {
    @Override
    public int getTotalPages() {
        return 1;
    }

    @Override
    public long getTotalElements() {
        return 1;
    }

    @Override
    public <U> Page<U> map(Function<? super DeptInfoMysqlEntity, ? extends U> function) {
        return null;
    }

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public int getNumberOfElements() {
        return 1;
    }

    @Override
    public List<DeptInfoMysqlEntity> getContent() {
        List<DeptInfoMysqlEntity> deptInfoMysqlEntityList = new ArrayList<>();
        DeptInfoMysqlEntity deptInfoMysqlEntity = new DeptInfoMysqlEntity();
        deptInfoMysqlEntity.setDeptId(43);
        deptInfoMysqlEntity.setDeptType("0001");
        deptInfoMysqlEntity.setModifytime(new Date());
        deptInfoMysqlEntity.setCreatetime(new Date());
        deptInfoMysqlEntity.setIsdelete(false);
        deptInfoMysqlEntityList.add(deptInfoMysqlEntity);
        return deptInfoMysqlEntityList;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }


    @Override
    public Iterator<DeptInfoMysqlEntity> iterator() {
        return null;
    }
}
