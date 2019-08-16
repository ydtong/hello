package com.allqj.virtual_number_administrate.util.headers;

import com.allqj.virtual_number_administrate.business.vo.PageHeadersResult;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HeadersUtil {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Headers {
        String name();

        int width();
    }

    public static List<PageHeadersResult> getHeaders(Class<?> clazz) {
        List<PageHeadersResult> result = new ArrayList<>();
        if (clazz == Object.class)
            return result;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Headers headers = field.getAnnotation(Headers.class);
            if (headers != null)
                result.add(new PageHeadersResult(field.getName(), headers.name(), headers.width()));
        }
        result.addAll(getHeaders(clazz.getSuperclass()));
        return result;
    }
}
