package cn.tellsea.freestyle.system.service;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.system.entity.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表 Service接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface RoleInfoService extends IService<RoleInfo> {

    List<RoleInfo> getByUserName(String userName);

    ResponseResult listByTable(RoleInfo roleInfo);

    void saveRole(RoleInfo roleInfo);

    void updateRole(RoleInfo roleInfo);

    void deleteRole(int id);
}
