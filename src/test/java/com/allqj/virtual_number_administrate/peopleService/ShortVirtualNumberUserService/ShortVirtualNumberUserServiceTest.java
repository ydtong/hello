package com.allqj.virtual_number_administrate.peopleService.ShortVirtualNumberUserService;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberUseService;
import com.allqj.virtual_number_administrate.business.vo.BindingVirtualNumberRequest;
import com.allqj.virtual_number_administrate.business.vo.BindingVirtualNumberResult;
import com.allqj.virtual_number_administrate.business.vo.CancelBindingVirtualNumberRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: cj
 * @description TODO
 * @date: 2019/4/4 16:19
 **/
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortVirtualNumberUserServiceTest {
    @Autowired
    private IVirtualNumberUseService shortVirtualNumberUserServiceImpl;

    */
/**
     * 绑定
     *//*

    @Test
    public void binding(){
        BindingVirtualNumberRequest bindingVirtualNumberRequest = new BindingVirtualNumberRequest();
        bindingVirtualNumberRequest.setAccountCode(0);
        bindingVirtualNumberRequest.setVirtualNumberType(1);
        bindingVirtualNumberRequest.setCardno("130229198811081836");
        bindingVirtualNumberRequest.setName("井志立");
        bindingVirtualNumberRequest.setPhone("15081659826");
        bindingVirtualNumberRequest.setUserId(23);
        bindingVirtualNumberRequest.setVirtualNumber("123124343254325");

        BindingVirtualNumberResult bindingVirtualNumberResult =
                shortVirtualNumberUserServiceImpl.binding(bindingVirtualNumberRequest);
        Assert.assertTrue(bindingVirtualNumberResult.getResult());
    }

    */
/**
     * 解除绑定
     *//*

    @Test
    public void cancelBinding(){
        CancelBindingVirtualNumberRequest cancelBindingVirtualNumberRequest = new CancelBindingVirtualNumberRequest();
        cancelBindingVirtualNumberRequest.setPhone("15081659826");
        cancelBindingVirtualNumberRequest.setVirtualNumber("123124343254325");
        cancelBindingVirtualNumberRequest.setVirtualNumberType(0);

        CancelBindingVirtualNumberResult cancelBindingVirtualNumberResult =
                shortVirtualNumberUserServiceImpl.cancelBinding(cancelBindingVirtualNumberRequest);
        Assert.assertTrue(cancelBindingVirtualNumberResult.getResult());
    }
}
*/
