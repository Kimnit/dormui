package com.kimnit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Bad_goods;
import com.kimnit.entity.Dormm;
import com.kimnit.entity.Leav;
import com.kimnit.service.IDormmService;
import com.kimnit.service.ILeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController {

    @Autowired
    private ILeaveService iLeaveService;

    @Autowired
    private IDormmService iDormmService;

    @GetMapping
    public R<Page<Leav>> page(int page, int rows){
        Page<Leav> leavePage = new Page<Leav> ( page,rows );
        iLeaveService.page (leavePage);
        return R.success (leavePage);
    }

    @PostMapping
    public R<String> leave(@RequestBody Leav leave){
        LambdaQueryWrapper<Dormm> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Dormm::getStuid,leave.getStuid ());
        Dormm dormm = iDormmService.getOne (queryWrapper);

        leave.setFloor (dormm.getFloor ());
        leave.setDormid (dormm.getDormid ());
        leave.setLtime (LocalDateTime.now ());

        LambdaQueryWrapper<Leav> lambdaQueryWrapper = new LambdaQueryWrapper<> ();
        lambdaQueryWrapper.eq (Leav::getStuid,leave.getStuid ());
        Leav one = iLeaveService.getOne (lambdaQueryWrapper);
        if(one == null){
            iLeaveService.save (leave);
            return R.success ("成功");
        }else {
            return R.error ("你已离校");
        }
    }

    @DeleteMapping("{stuid}")
    public R<String> delete(@PathVariable String stuid){
        LambdaQueryWrapper<Leav> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Leav::getStuid,stuid);
        Leav one = iLeaveService.getOne (queryWrapper);
        if (one != null){
            iLeaveService.remove (queryWrapper);
            return R.success ("成功");
        }else {
            return R.error ("你未离校");
        }
    }
}

