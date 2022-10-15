package com.kimnit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kimnit.entity.Dormm;
import com.kimnit.mapper.DormmMapper;
import com.kimnit.service.IDormmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@Service
public class DormmServiceImpl extends ServiceImpl<DormmMapper, Dormm> implements IDormmService {

}
