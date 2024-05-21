package com.zzazz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzazz.model.system.SysOperLog;
import org.springframework.stereotype.Repository;

/**
 * ClassName: OperLogMapper
 * Package: com.zzazz.system.mapper
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:28
 * @Description: OperLogMapper
 * @Version: v1.0
 */
@Repository
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
