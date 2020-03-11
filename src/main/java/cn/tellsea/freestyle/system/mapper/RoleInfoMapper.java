package cn.tellsea.freestyle.system.mapper;

import cn.tellsea.freestyle.system.entity.RoleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色表 Mapper接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    List<RoleInfo> getByUserName(String userName);
}
