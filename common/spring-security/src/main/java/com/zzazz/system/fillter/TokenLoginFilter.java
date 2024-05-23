package com.zzazz.system.fillter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzazz.common.result.R;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.common.util.IpUtil;
import com.zzazz.common.util.JwtHelper;
import com.zzazz.common.util.ResponseUtil;
import com.zzazz.system.custom.CustomUser;
import com.zzazz.model.vo.system.LoginVo;
import com.zzazz.system.service.SysLoginLogService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TokenLoginFilter
 * Package: com.zzazz.fillter
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 9:49
 * @Description: TokenLoginFilter
 * @Version: v1.0
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final SysLoginLogService sysLoginLogService;

    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            SysLoginLogService sysLoginLogService) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        this.sysLoginLogService = sysLoginLogService;
        //ログインpathを指定する。
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login", "POST"));
    }


    /**
     * 登録認証
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 認証が成功した時
     *
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());

        // 成功した時登録ログを追加
        sysLoginLogService.recordLoginLog(customUser.getUsername(), 1, IpUtil.getIpAddress(request), "登录成功");

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, R.ok(map));
    }

    /**
     * 認証が失敗した時
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        if (e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, R.build(null, 204, e.getMessage()));
        } else {
            ResponseUtil.out(response, R.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
        }
    }
}
