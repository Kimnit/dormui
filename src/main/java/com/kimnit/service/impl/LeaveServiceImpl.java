package com.kimnit.service.impl;

import com.kimnit.entity.Leav;
import com.kimnit.mapper.LeaveMapper;
import com.kimnit.service.ILeaveService;
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
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leav> implements ILeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

}
