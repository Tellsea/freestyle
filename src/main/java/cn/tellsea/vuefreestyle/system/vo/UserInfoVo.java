package cn.tellsea.vuefreestyle.system.vo;

import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户表 Vo
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@Getter
@Setter
public class UserInfoVo extends UserInfo {

    private String roleName;
}
