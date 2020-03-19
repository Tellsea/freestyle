package cn.tellsea.freestyle.system.service.impl;

import cn.tellsea.freestyle.system.entity.ResourceInfo;
import cn.tellsea.freestyle.system.mapper.ResourceInfoMapper;
import cn.tellsea.freestyle.system.service.ResourceInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements ResourceInfoService {

    @Override
    public List<ResourceInfo> getByUserName(String userName) {
        return this.baseMapper.getByUserName(userName);
    }

    @Override
    public List<ResourceInfo> listByTree() {
        return createTree(0, this.baseMapper.selectList(new LambdaQueryWrapper<ResourceInfo>().orderByAsc(ResourceInfo::getSort)));
    }

    private List<ResourceInfo> createTree(Integer pid, List<ResourceInfo> rootList) {
        List<ResourceInfo> treeList = new ArrayList<>();
        rootList.forEach(info -> {
            if (pid.equals(info.getPid())) {
                treeList.add(info.setChildren(createTree(info.getId(), rootList)));
            }
        });
        return treeList;
    }
}
