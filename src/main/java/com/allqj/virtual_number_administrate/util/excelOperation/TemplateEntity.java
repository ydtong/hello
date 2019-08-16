package com.allqj.virtual_number_administrate.util.excelOperation;

import java.lang.annotation.*;

public class TemplateEntity {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ExcelHead {
        String name();
    }
}
