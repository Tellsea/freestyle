package cn.tellsea.freestyle.system.service.impl;

import cn.tellsea.freestyle.system.entity.SystemLog;
import cn.tellsea.freestyle.system.mapper.SystemLogMapper;
import cn.tellsea.freestyle.system.service.SystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统日志表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {

}
