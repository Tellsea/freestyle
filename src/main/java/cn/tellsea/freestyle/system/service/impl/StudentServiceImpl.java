package cn.tellsea.freestyle.system.service.impl;

import cn.tellsea.freestyle.common.entity.FreestyleConst;
import cn.tellsea.freestyle.system.entity.Student;
import cn.tellsea.freestyle.system.mapper.StudentMapper;
import cn.tellsea.freestyle.system.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 学生表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
@DS(FreestyleConst.DB_SLAVE)
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
