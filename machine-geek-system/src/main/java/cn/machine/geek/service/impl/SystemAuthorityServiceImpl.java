package cn.machine.geek.service.impl;

import cn.machine.geek.dto.SystemAuthorityTree;
import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.mapper.ISystemAuthorityMapper;
import cn.machine.geek.service.ISystemAuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: MachineGeek
 * @Description: 系统权力实现类
 * @Date: 2020/10/3
 */
@Service
public class SystemAuthorityServiceImpl extends ServiceImpl<ISystemAuthorityMapper, SystemAuthority> implements ISystemAuthorityService {
    @Override
    public List<SystemAuthority> listByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SystemAuthority> listByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public List<SystemAuthorityTree> tree() {
        return this.getChildren(0L,baseMapper.selectList(new QueryWrapper<>()));
    }

    /** @Author: MachineGeek
     * @Description: 递归转换权限菜单树
     * @Date: 2020/10/28
     * @param: id
     * @param: authorities
     * @Return java.util.List<cn.machine.geek.dto.Menu>
     */
    private List<SystemAuthorityTree> getChildren(Long id, Collection<? extends GrantedAuthority> authorities){
        // 递归获取子树
        List<SystemAuthorityTree> authorityTrees = new ArrayList<>();
        authorities.forEach((authority)->{
            SystemAuthority systemAuthority = (SystemAuthority) authority;
            if(id.equals(systemAuthority.getParentId())){
                SystemAuthorityTree authorityTree = new SystemAuthorityTree();
                BeanUtils.copyProperties(systemAuthority, authorityTree);
                authorityTree.setChildren(this.getChildren(authorityTree.getId(),authorities));
                authorityTrees.add(authorityTree);
            }
        });
        // 排序
        Collections.sort(authorityTrees, new Comparator<SystemAuthorityTree>() {
            @Override
            public int compare(SystemAuthorityTree o1, SystemAuthorityTree o2) {
                return o2.getSort() - o1.getSort();
            }
        });
        return authorityTrees;
    }
}
