package cn.machine.geek.controller;

/**
 * @Author: MachineGeek
 * @Description: Token控制器
 * @Date: 2020/11/2
 */

import cn.machine.geek.dto.R;
import cn.machine.geek.entity.LoginUser;
import cn.machine.geek.service.ITokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 验证码控制器
 * @Date: 2020/10/25
 */
@Api(tags = "验证码接口")
@RestController
@RequestMapping(value = "/api/token/")
public class TokenController {
    @Autowired
    private ITokenService tokenService;

    @ApiOperation(value = "刷新Token",notes = "使用RefreshToken刷新AccessToken")
    @GetMapping(value = "/refreshToken")
    public R refreshToken(@RequestParam(value = "token") String token){
        if(tokenService.existsRefreshToken(token)){
            LoginUser loginUser = tokenService.getRefreshToken(token);
            tokenService.deleteRefreshToken(token);
            Map<String,Object> map = new HashMap<>();
            map.put("accessToken",tokenService.createAccessToken(loginUser));
            map.put("refreshToken",tokenService.createRefreshToken(loginUser));
            return R.ok(map);
        }
        return R.fail("Token已过期");
    }
}
