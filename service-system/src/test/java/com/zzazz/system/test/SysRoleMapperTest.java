package com.zzazz.system.test;

import com.zzazz.model.system.SysRole;
import com.zzazz.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: SysRoleMapperTest
 * Package: com.zzazz
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 10:11
 * @Description: SysRoleMapperTest
 * @Version: v1.0
 */
@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    void TestR(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
    }
}
