package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    private T result;
    private Integer statusCode;
    private String message;

    public static <T> ResultVO<T> newInstance(T result, Integer statusCode, String message) {
        return new ResultVO<>(result, statusCode, message);
    }

    public static <T> ResultVO<T> newInstance(T result) {
        return new ResultVO<>(result, StatusCodeEnum.OK.getCode(), StatusCodeEnum.OK.getMessage());
    }
}
