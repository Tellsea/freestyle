package cn.tellsea.freestyle.system.entity;

import cn.tellsea.freestyle.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源表 实体类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resource_info")
public class ResourceInfo extends BaseEntity {

    /**
     * 资源编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    @TableField("name")
    private String name;

    /**
     * 父级ID
     */
    @TableField("pid")
    private Integer pid;

    /**
     * URL
     */
    @TableField("url")
    private String url;

    /**
     * 权限标识
     */
    @TableField("perms")
    private String perms;

    /**
     * 0 未使用 1 菜单 2 按钮 3 链接
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否叶子节点，0 否，1 是
     */
    @TableField("is_leaf")
    private Boolean isLeaf;

    @TableField(exist = false)
    private List<ResourceInfo> children;
}
