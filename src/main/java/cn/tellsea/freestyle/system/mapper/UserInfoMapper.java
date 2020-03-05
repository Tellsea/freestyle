package cn.tellsea.freestyle.system.mapper;

import cn.tellsea.freestyle.system.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
* 用户表 Mapper接口
*
* @author Tellsea
* @date 2020-03-04
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Set<String> getRoleByUserName(String userName);

    Set<String> getPermissonByUserName(String userName);
}
