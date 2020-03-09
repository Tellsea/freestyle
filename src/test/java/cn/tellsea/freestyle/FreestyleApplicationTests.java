package cn.tellsea.freestyle;

import cn.tellsea.freestyle.system.entity.Student;
import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.mapper.StudentMapper;
import cn.tellsea.freestyle.system.mapper.UserInfoMapper;
import com.alibaba.druid.filter.config.ConfigTools;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("cn.tellsea.freestyle.*.mapper")
class FreestyleApplicationTests {

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

//        Page<UserInfo> page = new Page<>(1, 10);
//        Page<UserInfo> infoPage = userInfoMapper.selectPage(page, null);
//        System.out.println(infoPage);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getOrders());
//        System.out.println(page.getSize());
//        System.out.println(page.getRecords());
//        System.out.println(page.getTotal());

    }

    @Test
    public void druidEncrypt() throws Exception {
        String password = "Root123!@#";
        System.out.println("明文: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        String privateKey = keyPair[0];
        String publicKey = keyPair[1];
        password = ConfigTools.encrypt(privateKey, password);
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);
        System.out.println("密文:" + password);
        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密:" + decryptPassword);
    }

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void dyDataSource() {
        List<Student> studentList = studentMapper.selectList(null);
        studentList.forEach(System.out::println);
    }
}
