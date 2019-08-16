package com.allqj.virtual_number_administrate.peopleService.DictionaryService;

import com.allqj.virtual_number_administrate.business.service.IDictionaryService;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
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
 * @description 字典表单元测试
 * @date: 2019/4/4 11:13
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryServiceTest {
    @Autowired
    private IDictionaryService dictionaryServiceImpl;

    *//**
     * 隐号类型
     *//*
    @Test
    public void virtualNumberType(){
        List<DictionaryResult<Integer,String>> dictionaryResults = dictionaryServiceImpl.virtualNumberType();
        Assert.assertThat(dictionaryResults.size(),is(2));
    }
}*/
