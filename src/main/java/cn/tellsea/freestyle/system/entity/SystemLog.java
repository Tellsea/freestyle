package cn.tellsea.freestyle.system.entity;

import cn.tellsea.freestyle.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统日志表 实体类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_log")
public class SystemLog extends BaseEntity {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 操作描述
     */
    @TableField("operation")
    private String operation;

    /**
     * 耗时(毫秒)
     */
    @TableField("time")
    private Integer time;

    /**
     * 操作方法
     */
    @TableField("method")
    private String method;

    /**
     * 操作参数
     */
    @TableField("params")
    private String params;

    /**
     * IP地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 操作地点
     */
    @TableField("location")
    private String location;

    /**
     * 操作设备
     */
    @TableField("device")
    private String device;

    /**
     * 操作时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 状态(1正常，2删除)
     */
    @TableField("status")
    @TableLogic
    private Integer status;
}
