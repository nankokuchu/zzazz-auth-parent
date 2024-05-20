package com.zzazz.custom;

import com.zzazz.common.util.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ClassName: CustomMd5PasswordEncoder
 * Package: com.zzazz.custom
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 16:44
 * @Description: CustomMd5PasswordEncoder; springSecurityのパスワードを上書きする
 * @Version: v1.0
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        // MD5形式でパスワードを生成
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5.encrypt(charSequence.toString()));
    }
}
