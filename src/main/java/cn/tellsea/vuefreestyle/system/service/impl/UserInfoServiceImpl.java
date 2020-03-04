package cn.tellsea.vuefreestyle.system.service.impl;

import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import cn.tellsea.vuefreestyle.system.mapper.UserInfoMapper;
import cn.tellsea.vuefreestyle.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 用户表 Service接口实现类
*
* @author Tellsea
* @date 2020-03-04
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
