package com.kimnit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Violation;
import com.kimnit.service.IViolationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/violation")
@Slf4j
public class ViolationController {

    @Autowired
    private IViolationService iViolationService;

    @GetMapping
    public R<Page<Violation>> page(int page, int rows){
        log.info ("page = {},rows = {}", page, rows);

        Page<Violation> violationPage = new Page<Violation> ( page,rows );

        //执行查询
        iViolationService.page (violationPage);

        return R.success (violationPage);
    }

    @PostMapping
    public R<String> add(@RequestBody Violation violation){
        violation.setWtime (LocalDateTime.now ());
        iViolationService.save (violation);
        return R.success ("成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Violation violation){
        violation.setWtime (LocalDateTime.now ());
        iViolationService.updateById (violation);
        return R.success ("修改成功");
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Integer id){
        iViolationService.removeById (id);
        return R.success ("成功");
    }
}

