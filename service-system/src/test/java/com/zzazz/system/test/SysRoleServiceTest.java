package com.zzazz.system.test;

import com.zzazz.model.vo.system.AssignRoleVo;
import com.zzazz.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleServiceTest
 * Package: com.zzazz.system.test
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 16:26
 * @Description: SysRoleServiceTest
 * @Version: v1.0
 */
@Slf4j
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void getRolesByUserId() {
        Map<String, Object> r = sysRoleService.getRolesByUserId(4L);
        log.info("r:{}", r);
    }

    @Test
    void doAssign() {
        List<Long> roles = Arrays.asList(11L, 12L, 13L, 14L, 15L, 16L);
        AssignRoleVo assignRoleVo = new AssignRoleVo();
        assignRoleVo.setUserId(4L);
        assignRoleVo.setRoleIdList(roles);
        sysRoleService.doAssign(assignRoleVo);
    }
}
