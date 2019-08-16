package com.allqj.virtual_number_administrate.util.jsonOperation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;


/**
 * json操作
 */
@Service
public class JsonOperation {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static Gson getGson() {
        return gson;
    }

    /**
     * 转到json
     *
     * @param obj 对象
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null)
            return null;
        try {
            return getGson().toJson(obj);
        } catch (Exception ex) {
        }
        return obj.toString();
    }

    /**
     * 转到对象
     *
     * @param json
     * @param type 对象类型
     * @param <T>
     * @return
     */
    public static <T> T toClass(String json, Class<T> type) {
        if (json == null || type == null)
            return null;
        try {
            return getGson().fromJson(json, type);
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * 转到对象
     *
     * @param json
     * @param type 对象类型
     * @param <T>
     * @return
     */
    public static <T> T toClass(String json, Type type) {
        if (json == null || type == null)
            return null;
        try {
            return getGson().fromJson(json, type);
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * 转到对象
     *
     * @param json
     * @param type    对象类型
     * @param pattern 时间格式
     * @param <T>
     * @return
     */
    public static <T> T toClass(String json, Class<T> type, String pattern) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        try {
            return gson.fromJson(json, type);
        } catch (Exception ex) {
        }
        return null;
    }
}
