package cn.tellsea.vuefreestyle;

import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import cn.tellsea.vuefreestyle.system.mapper.UserInfoMapper;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan("cn.tellsea.vuefreestyle.system.mapper")
class VueFreestyleApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
//        UserInfo userInfo = userInfoMapper.selectById(1);
//        System.out.println(JSON.toJSONString(userInfo));

//        Map<String, Object> map = new HashMap<>();
//        map.put("user_name", "admin");
//        List<UserInfo> list = userInfoMapper.selectByMap(map);
//        list.forEach(temp -> System.out.println(JSON.toJSONString(temp)));

//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", "admin")
//                .like("nick_name", "超级");
//        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
//        System.out.println(JSON.toJSONString(userInfo));
//        List<Map<String, Object>> maps = userInfoMapper.selectMaps(queryWrapper);
//        System.out.println(JSON.toJSONString(maps));
//        List<Object> objects = userInfoMapper.selectObjs(queryWrapper);
//        System.out.println(objects.get(0));

//        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("user_name", "admin")
//                .like("nick_name", "超级");
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName("Tellsea");
//        int count = userInfoMapper.update(userInfo, updateWrapper);
//        System.out.println(count);

//        LambdaQueryWrapper<UserInfo> lambda = new QueryWrapper<UserInfo>().lambda();
//        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<UserInfo> lambdaQuery = Wrappers.<UserInfo>lambdaQuery();
//        lambdaQuery.like(UserInfo::getUserName, "te")
//                .lt(UserInfo::getLoginTimes, 0);
//        List<Object> objects = userInfoMapper.selectObjs(lambdaQuery);
//        objects.forEach(System.out::println);

        Page<UserInfo> page = new Page<>(1, 10);
        Page<UserInfo> infoPage = userInfoMapper.selectPage(page, null);
        System.out.println(infoPage);
        System.out.println(page.getCurrent());
        System.out.println(page.getOrders());
        System.out.println(page.getSize());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
}
