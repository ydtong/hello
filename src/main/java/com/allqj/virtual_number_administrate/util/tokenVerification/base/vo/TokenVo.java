package com.allqj.virtual_number_administrate.util.tokenVerification.base.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenVo {
    private String accessToken;
    private String refreshToken;
    private UserInfoResult userInfoResult;
}

@Setter
@Getter
class UserInfoResult {
    private Integer id;
}