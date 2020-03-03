package cn.tellsea.vuefreestyle.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户表 实体类
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@Getter
@Setter
public class UserInfo {

//    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 账户，登录名，不可更改
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 1男 2女
     */
    private Integer sex;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    /**
     * 最后登录时间
     */
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 状态 1 正常2 锁定 3 删除
     */
    private Integer status;
}
