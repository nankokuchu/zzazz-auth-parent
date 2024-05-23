package com.zzazz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.system.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: SysUserMapper
 * Package: com.zzazz.system.mapper
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:15
 * @Description: SysUserMapper
 * @Version: v1.0
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectPage(IPage<SysUser> pageParam,
                              @Param("vo") SysUserQueryVo sysUserQueryVo);
}
