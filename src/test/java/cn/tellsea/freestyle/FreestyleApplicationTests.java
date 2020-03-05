package cn.tellsea.freestyle;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("cn.tellsea.freestyle.*.mapper")
class FreestyleApplicationTests {

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

//        Page<UserInfo> page = new Page<>(1, 10);
//        Page<UserInfo> infoPage = userInfoMapper.selectPage(page, null);
//        System.out.println(infoPage);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getOrders());
//        System.out.println(page.getSize());
//        System.out.println(page.getRecords());
//        System.out.println(page.getTotal());

    }
}
