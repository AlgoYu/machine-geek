package cn.machine.geek.controller;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.dto.AuthorityTree;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.LoginUser;
import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.enums.AuthorityEnum;
import cn.machine.geek.service.ISystemAuthorityService;
import cn.machine.geek.service.ITokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: MachineGeek
 * @Description: 系统权限控制器
 * @Date: 2020/10/28
 */
@Api(tags = "系统权限接口")
@RestController
@RequestMapping(value = "/systemAuthority")
public class SystemAuthorityController {
    @Autowired
    private ISystemAuthorityService systemAuthorityService;
    @Autowired
    private ITokenService tokenService;

    @ApiOperation(value = "获取所有权限树",notes = "获取所有权限树")
    @GetMapping(value = "/getAllAuthorityTree")
    @PreAuthorize("hasAnyRole('MANAGEMENT:SYSTEMAUTHORITY:GET')")
    public R getAllAuthorityTree(){
        // 转换为菜单树返回
        return R.ok(this.getChildren(0L,systemAuthorityService.list()));
    }

    @ApiOperation(value = "获取权限树",notes = "也是菜单及API")
    @GetMapping(value = "/getAuthorityTree")
    @PreAuthorize("hasAnyRole('MANAGEMENT:SYSTEMAUTHORITY:GET')")
    public R getAuthorityTree(HttpServletRequest request){
        // 获取当前用户的权限
        String tokenStr = request.getHeader(WebConstant.TOKEN_HEADER);
        LoginUser loginUser = tokenService.getAccessToken(tokenStr);
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        // 转换为菜单树返回
        return R.ok(this.getChildren(0L,authorities));
    }

    /** @Author: MachineGeek
    * @Description: 递归转换权力菜单树
    * @Date: 2020/10/28
    * @param: id
    * @param: authorities
    * @Return java.util.List<cn.machine.geek.dto.Menu>
    */
    private List<AuthorityTree> getChildren(Long id, Collection<? extends GrantedAuthority> authorities){
        // 递归获取子树
        List<AuthorityTree> authorityTrees = new ArrayList<>();
        authorities.forEach((authority)->{
            SystemAuthority systemAuthority = (SystemAuthority) authority;
            if(id.equals(systemAuthority.getParentId())){
                AuthorityTree authorityTree = new AuthorityTree();
                BeanUtils.copyProperties(systemAuthority, authorityTree);
                authorityTree.setChildren(this.getChildren(authorityTree.getId(),authorities));
                authorityTrees.add(authorityTree);
            }
        });
        // 排序
        Collections.sort(authorityTrees, new Comparator<AuthorityTree>() {
            @Override
            public int compare(AuthorityTree o1, AuthorityTree o2) {
                return o2.getSort() - o1.getSort();
            }
        });
        return authorityTrees;
    }

    @ApiOperation(value = "增加系统权限",notes = "增加权限")
    @PostMapping(value = "/add")
    @PreAuthorize("hasAnyRole('MANAGEMENT:SYSTEMAUTHORITY:ADD')")
    @Transactional
    public R add(@RequestBody SystemAuthority systemAuthority){
        systemAuthority.setSort(0);
        systemAuthority.setType(AuthorityEnum.API);
        systemAuthority.setDisable(false);
        systemAuthority.setVersion(0);
        systemAuthority.setCreateTime(LocalDateTime.now());
        return R.ok(systemAuthorityService.save(systemAuthority));
    }

    @ApiOperation(value = "根据ID修改系统权限",notes = "根据ID修改系统权限")
    @GetMapping(value = "/modifyById")
    @PreAuthorize("hasAnyRole('MANAGEMENT:SYSTEMAUTHORITY:MODIFY')")
    @Transactional
    public R modifyById(@RequestBody SystemAuthority systemAuthority){
        systemAuthority.setUpdateTime(LocalDateTime.now());
        return R.ok(systemAuthorityService.updateById(systemAuthority));
    }

    @ApiOperation(value = "根据ID获取系统权限",notes = "根据ID获取系统权限")
    @GetMapping(value = "/getById")
    @PreAuthorize("hasAnyRole('MANAGEMENT:SYSTEMAUTHORITY:GET')")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemAuthorityService.getById(id));
    }
}
