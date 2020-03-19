package cn.tellsea.freestyle.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基类 实体类
 *
 * @author Tellsea
 * @date 2020/3/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int limit;
}
