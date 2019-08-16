package com.allqj.virtual_number_administrate.util.IDUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: cj
 * @description 验证身份证公共包
 * @date: 2019/4/30 10:02
 **/
@Getter
@Setter
public class CheckIdCardUtil {

    public static Boolean checkIdCard(String idCard) {

        if (idCard == null || "".equals(idCard)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾

        boolean matches = idCard.matches(regularExpression);

        //18位校验规则是：
        //（1）十七位数字本体码加权求和公式
        //                S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
        //        Ai:表示第i位置上的身份证号码数字值
        //        Wi:表示第i位置上的加权因子
        //        Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
        //（2）计算模
        //        Y = mod(S, 11)
        //（3）通过模得到对应的校验码
        //        Y: 0 1 2 3 4 5 6 7 8 9 10
        //        校验码: 1 0 X 9 8 7 6 5 4 3 2

        if (matches) {

            if (idCard.length() == 18) {
                try {
                    //身份证本体码Char集合
                    char[] charArray = idCard.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    //利用本体码加权求和
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        //获得身份证前17位
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        //身份证数字*加权因子
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    //获得第18位本体码
                    char idCardLast = charArray[17];
                    //获得本体码的验证码
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }
}
