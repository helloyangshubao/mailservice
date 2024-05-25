package com.payc.tool.domain.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * staff
 *
 * @author yangshubao
 * @date 2020/2/15 10:08
 */
@Data
public class TaskVO {

    @ApiModelProperty(value = "操作人")
    private String create_user_name;

    @ApiModelProperty(value = "操作时间")
    private String create_time;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "task_id")
    private String task_id;

    @ApiModelProperty(value = "任务状态")
    private Integer task_status;

    @ApiModelProperty(value = "任务状态名称")
    private String task_status_name;

    @ApiModelProperty(value = "任务类型")
    private String task_type;

    @ApiModelProperty(value = "任务类型名称")
    private String task_type_name;

    @ApiModelProperty(value = "导入总数")
    private Integer import_count;

    @ApiModelProperty(value = "导入成功总数")
    private Integer success_count;

    @ApiModelProperty(value = "导入失败总数")
    private Integer fail_count;

}
