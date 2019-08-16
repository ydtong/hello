package com.allqj.virtual_number_administrate.business.microService.longHiddencall.inte;


import com.allqj.virtual_number_administrate.business.microService.longHiddencall.vo.LongBindingRequest;
import com.allqj.virtual_number_administrate.business.microService.longHiddencall.vo.LongBindingResult;
import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallResult;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "longhiddencall")
public interface LongService {
    @PostMapping(value = "/longHiddenCall/basicServices/bindOperation/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<LongBindingResult> binding(@RequestBody LongBindingRequest longBindingRequest);

    @PatchMapping("/longHiddenCall/basicServices/bindOperation/delete/{telx}")
    ResultVO<Boolean> cancelBinding(@PathVariable(value = "telx") String telx);

    @PostMapping(value = "/longHiddenCall/basicServices/onlinecall/operation", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<CallResult> call(@RequestBody CallReqest callReqest);
}
