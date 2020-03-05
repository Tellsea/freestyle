package cn.tellsea.freestyle.system.vo;

import cn.tellsea.freestyle.system.entity.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Tellsea
 * @date 2020/3/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfoVo extends UserInfo {

    private String roleName;
}
