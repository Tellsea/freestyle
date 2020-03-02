package cn.tellsea.vuefreestyle.test.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户角色关联表
 *
 * @author Tellsea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MapUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;


}
