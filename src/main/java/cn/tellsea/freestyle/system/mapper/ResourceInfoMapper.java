package cn.tellsea.freestyle.system.mapper;

import cn.tellsea.freestyle.system.entity.ResourceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 资源表 Mapper接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {

    List<ResourceInfo> getByUserName(String userName);
}
