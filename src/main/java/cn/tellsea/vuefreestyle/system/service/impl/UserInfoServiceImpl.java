package cn.tellsea.vuefreestyle.system.service.impl;

import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import cn.tellsea.vuefreestyle.system.mapper.UserInfoMapper;
import cn.tellsea.vuefreestyle.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表 Service 接口层实现类
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
