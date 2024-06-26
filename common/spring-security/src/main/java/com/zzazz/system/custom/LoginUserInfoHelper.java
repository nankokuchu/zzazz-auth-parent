package com.zzazz.system.custom;

/**
 * ClassName: LoginUserInfoHelper
 * Package: com.zzazz.system.custom
 *
 * @Author: zzazz
 * @Create: 2024/5/27 - 15:22
 * @Description: LoginUserInfoHelper
 * @Version: v1.0
 */
public class LoginUserInfoHelper {

    private static final ThreadLocal<Long> userId = new ThreadLocal<Long>();
    private static final ThreadLocal<String> username = new ThreadLocal<String>();

    public static void setUserId(Long _userId) {
        userId.set(_userId);
    }
    public static Long getUserId() {
        return userId.get();
    }
    public static void removeUserId() {
        userId.remove();
    }
    public static void setUsername(String _username) {
        username.set(_username);
    }
    public static String getUsername() {
        return username.get();
    }
    public static void removeUsername() {
        username.remove();
    }
}
