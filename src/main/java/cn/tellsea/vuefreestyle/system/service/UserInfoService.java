package cn.tellsea.vuefreestyle.system.service;

import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* 用户表 Service接口
*
* @author Tellsea
* @date 2020-03-04
*/
public interface UserInfoService extends IService<UserInfo> {

    Set<String> getRoleByUserName(String userName);

    Set<String> getPermissonByUserName(String userName);
}
