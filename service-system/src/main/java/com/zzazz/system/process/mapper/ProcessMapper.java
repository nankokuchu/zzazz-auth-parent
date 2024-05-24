package com.zzazz.system.process.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: Process
 * Package: com.zzazz.system.process.mapper
 *
 * @Author: zzazz
 * @Create: 2024/5/24 - 16:15
 * @Description: Process
 * @Version: v1.0
 */
@Repository
public interface ProcessMapper extends BaseMapper<Process> {


    IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam,
                                @Param("vo")ProcessQueryVo processQueryVo);
}