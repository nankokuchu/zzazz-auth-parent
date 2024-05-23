package com.zzazz.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.vo.system.SysRoleQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: SysRoleMapper
 * Package: com.zzazz.mapper
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 10:10
 * @Description: SysRoleMapper
 * @Version: v1.0
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> selectPage(IPage<SysRole> page,
                              @Param("vo") SysRoleQueryVo roleQueryVo);
}
