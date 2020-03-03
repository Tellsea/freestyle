package cn.tellsea.vuefreestyle.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统日志表 实体类
 *
 * @author Tellsea
 * @date 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SystemLog对象", description = "系统日志表")
public class SystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "操作用户id")
    private Integer userId;

    @ApiModelProperty(value = "操作描述")
    private String operation;

    @ApiModelProperty(value = "耗时(毫秒)")
    private Integer time;

    @ApiModelProperty(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "操作参数")
    private String params;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "操作地点")
    private String location;

    @ApiModelProperty(value = "操作设备")
    private String device;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createTime;
}
