package cn.tellsea.vuefreestyle.system.service.impl;

import cn.tellsea.vuefreestyle.system.entity.SystemLog;
import cn.tellsea.vuefreestyle.system.mapper.SystemLogMapper;
import cn.tellsea.vuefreestyle.system.service.SystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
* 系统日志表 Service接口实现类
*
* @author Tellsea
* @date 2020-03-03
*/
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {

    @Async
    @Override
    public boolean saveAsync(SystemLog entity) {
        int count = this.getBaseMapper().insert(entity);
        System.out.println("保存");
        return count > 0 ? true : false;
    }
}
