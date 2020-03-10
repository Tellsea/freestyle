package cn.tellsea.freestyle.system.service.impl;

import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.mapper.UserInfoMapper;
import cn.tellsea.freestyle.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public Set<String> getRoleByUserName(String userName) {
        return baseMapper.getRoleByUserName(userName);
    }

    @Override
    public Set<String> getPermissonByUserName(String userName) {
        return baseMapper.getPermissonByUserName(userName);
    }
}
