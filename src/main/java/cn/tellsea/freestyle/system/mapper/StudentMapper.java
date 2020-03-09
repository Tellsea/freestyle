package cn.tellsea.freestyle.system.mapper;

import cn.tellsea.freestyle.system.entity.Student;
import cn.tellsea.freestyle.system.entity.UserInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
* 用户表 Mapper接口
*
* @author Tellsea
* @date 2020-03-04
*/
@DS("db1")
public interface StudentMapper extends BaseMapper<Student> {

}
