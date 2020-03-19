package cn.tellsea.freestyle.system.service;

import cn.tellsea.freestyle.system.entity.ResourceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 资源表 Service接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface ResourceInfoService extends IService<ResourceInfo> {

    List<ResourceInfo> getByUserName(String userName);

    List<ResourceInfo> listByTree();
}
