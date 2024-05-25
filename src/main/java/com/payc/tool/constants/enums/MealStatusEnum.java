package com.payc.tool.constants.enums;

/**
 * 就餐状态
 *
 * @author yangshubao
 * @date 2022/11/19 10:05
 */
public enum MealStatusEnum {

    /**
     * 就餐状态
     */
    MEAL_NO_TAKEN(1, "未取餐"),
    MEAL_TAKEN(3, "已取餐");

    private Integer code;
    private String msg;

    MealStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public final Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }


    public static String getMsg(Integer code) {
        for (MealStatusEnum mealStatusEnum : MealStatusEnum.values()) {
            if (code.equals(mealStatusEnum.getCode())) {
                return mealStatusEnum.getMsg();
            }
        }
        return null;
    }

    public static Integer getCode(String msg) {
        for (MealStatusEnum mealStatusEnum : MealStatusEnum.values()) {
            if (msg.equals(mealStatusEnum.getMsg())) {
                return mealStatusEnum.getCode();
            }
        }
        return null;
    }
}
