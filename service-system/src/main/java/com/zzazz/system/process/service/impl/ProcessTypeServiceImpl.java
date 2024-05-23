package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.ProcessType;
import com.zzazz.system.process.mapper.ProcessTypeMapper;
import com.zzazz.system.process.service.ProcessTypeService;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProcessType
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:42
 * @Description:
 * @Version: v1.0
 */
@Service
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {
}
