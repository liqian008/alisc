package com.shan.yellowpages.base.utils;

import java.lang.reflect.Method;

/**
 * 根据错误码获取对应的枚举对象
 *
 * @author :xuejw
 * @date 2019-03-05 18:07
 */
public class EnumUtil {


//    /**
//     * 通过反射找到属性值为code 的枚举类型
//     * @param code 整型错误码
//     * @param enumClass 枚举类型的 class
//     * @param <T> 泛型  CodeEnum的子类
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
//        // 通过反射取出 Enum 所有常量的属性值
//        for (T each : enumClass.getEnumConstants()) {
//            // 利用code进行循环比较，获取对应的枚举
//            if (code.equals(each.getCode())) {
//                return each;
//            }
//        }
//
//        // 避免空指针，默认返回一个错误结果
//        return (T) ErrorCodeEnum.SYSTEM_ERROR;
//    }

    /**
     * 根据传入的 type 值, 获取对应的枚举类.
     * <p>
     * 此方法有强制性前提
     * 1. 传入的类型为 java.lang.Enum;
     * 2. 此枚举类型有一个变量值, 类型为 int, 变量名叫 type;
     * 3. 此枚举类有一个方法, 方法名叫 getType;
     *
     * @param type   类型
     * @param aClass 枚举类
     * @return 成功返回对应的枚举类, 失败返回 NULL
     */
    public static <T extends Enum> T getEnumTypeByType(int type, Class<T> aClass) {
        // TODO 判断是枚举类型

        try {
            Method valuesMethod = aClass.getMethod("values");

            T[] invoke = (T[]) valuesMethod.invoke(aClass);

            for (T t : invoke) {

                Method getTypeMethod = t.getClass().getMethod("getType");

                int typeValue = (int) getTypeMethod.invoke(t);

                if (typeValue == type) {
                    return t;
                }
            }
        } catch (Exception e) {

        }

        return null;
    }

//    public static void main(String[] args) {
//        String a = EnumUtil.getByCode(430, ErrorCodeEnum.class).getMessage();
//        System.out.println("a = " + a);
//    }

}
