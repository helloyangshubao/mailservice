package com.payc.tool.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生选餐表
 * </p>
 *
 * @author wrb
 * @since 2023-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("monthly_dining")
public class MonthlyDiningEntity implements Serializable {

private static final long serialVersionUID=1L;

    private String id;

    /**
     * 学校ID
     */
    private String schoolId;

    /**
     * 就餐月
     */
    private String month;

    /**
     * 包月配置ID
     */
    private String settingId;

    /**
     * 包月交费id
     */
    private String chargeId;

    /**
     * 包月缴费包ID
     */
    private String paymentId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 人员类型(可多选)
     */
    private String studentType;

    /**
     * 班级编码
     */
    private String classCode;

    /**
     * 班级名
     */
    private String className;

    /**
     * 状态（1正常 2请假审核中 3请假通过 4退款）
     */
    private Integer status;

    /**
     * 时间具体日期
     */
    private String mealDate;

    /**
     * 餐次
     */
    private Integer mealTimeIndex;

    /**
     * 菜品
     */
    private String mealId;

    /**
     * 菜品名称
     */
    private String mealName;

    /**
     * 菜品价钱
     */
    private BigDecimal mealPrice;

    /**
     * 代号
     */
    private String mark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后更新时间
     */
    private String lastUpdateTime;

    /**
     * 运营商ID
     */
    private String operatorId;

    /**
     * 运营商Code
     */
    private String operatorCode;

    /**
     * 请假id
     */
    private String leaveId;

    /**
     * 测试数据类型 1 真实学生内测 2 官方测试账号 3 产研测试人员
     */
    private Integer tester;

    /**
     * 付款关联订单号
     */
    private String payOrderId;


}
