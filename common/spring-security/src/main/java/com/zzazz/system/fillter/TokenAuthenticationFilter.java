package com.zzazz.system.fillter;

import com.zzazz.common.result.R;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.common.util.JwtHelper;
import com.zzazz.common.util.ResponseUtil;
import com.zzazz.system.custom.LoginUserInfoHelper;
import com.zzazz.system.service.SysAuthMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: TokenAuthenticationFilter
 * Package: com.zzazz.fillter
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 10:06
 * @Description: TokenAuthenticationFilter
 * @Version: v1.0
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final SysAuthMenuService sysAuthMenuService;

    public TokenAuthenticationFilter(SysAuthMenuService sysAuthMenuService) {
        this.sysAuthMenuService = sysAuthMenuService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // logger.info("uri:"+request.getRequestURI());
        //ログインurlはpassする
        if("/admin/system/index/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, R.build(null, ResultCodeEnum.PERMISSION));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // headからtokenを取得
        String token = request.getHeader("token");
        // logger.info("token:"+token);
        if (!StringUtils.isEmpty(token)) {
            String username = JwtHelper.getUsername(token);
            Long userId = JwtHelper.getUserId(token);
            // logger.info("username:"+username);
            if (!StringUtils.isEmpty(username)) {
                // 現在のユーザー情報をThreadLocalに保存する
                LoginUserInfoHelper.setUserId(userId);
                LoginUserInfoHelper.setUsername(username);
                // ボタン権限を取得
                List<String> userButtonList = sysAuthMenuService.getUserButtonList(userId);
                // log.info(String.valueOf(userButtonList));
                // redisの代わりに、毎回DBから検索するように
                if (!userButtonList.isEmpty()){
                    // List<SimpleGrantedAuthority> authorities = new ArrayList<>(); SpringSecurityの形式
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for (String s : userButtonList) {
                        authorities.add(new SimpleGrantedAuthority(s.trim()));
                    }
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                } else {
                    return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                }
            }
        }
        return null;
    }
}
