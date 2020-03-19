package cn.tellsea.freestyle.system.service.impl;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.system.entity.MapRoleResource;
import cn.tellsea.freestyle.system.entity.RoleInfo;
import cn.tellsea.freestyle.system.mapper.RoleInfoMapper;
import cn.tellsea.freestyle.system.service.MapRoleResourceService;
import cn.tellsea.freestyle.system.service.RoleInfoService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

    @Autowired
    private MapRoleResourceService mapRoleResourceService;

    @Override
    public List<RoleInfo> getByUserName(String userName) {
        return this.baseMapper.getByUserName(userName);
    }

    @Override
    public ResponseResult listByTable(RoleInfo roleInfo) {
        int count = this.baseMapper.countByTable(roleInfo);
        List<RoleInfo> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listByTable(roleInfo);
        }
        return ResponseResult.table(count, list);
    }

    @Override
    public void saveRole(RoleInfo roleInfo) {
        this.baseMapper.insert(roleInfo);
        saveMapRoleResource(roleInfo);
    }

    @Override
    public void updateRole(RoleInfo roleInfo) {
        this.baseMapper.updateById(roleInfo);
        mapRoleResourceService.remove(new LambdaUpdateWrapper<MapRoleResource>().eq(MapRoleResource::getRoleId, roleInfo.getId()));
        saveMapRoleResource(roleInfo);
    }

    private void saveMapRoleResource(RoleInfo roleInfo) {
        String[] split = roleInfo.getResourceIds().split(",");
        List<MapRoleResource> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(new MapRoleResource().setRoleId(roleInfo.getId()).setResourceId(Integer.valueOf(split[i])));
        }
        mapRoleResourceService.saveBatch(list);
    }

    @Override
    public void deleteRole(int id) {
        this.baseMapper.deleteById(id);
        mapRoleResourceService.remove(new LambdaUpdateWrapper<MapRoleResource>().eq(MapRoleResource::getRoleId, id));
    }
}
