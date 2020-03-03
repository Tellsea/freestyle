package cn.tellsea.vuefreestyle.system.service;

import cn.tellsea.vuefreestyle.system.entity.SystemLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* 系统日志表 Service接口
*
* @author Tellsea
* @date 2020-03-03
*/
public interface SystemLogService extends IService<SystemLog> {

    boolean saveAsync(SystemLog entity);
}
