package com.kimnit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Advice;
import com.kimnit.service.IAdviceService;
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
@RequestMapping("/advice")
@Slf4j
public class AdviceController {

    @Autowired
    private IAdviceService iAdviceService;

    @GetMapping
    public R<Page> page(int page, int rows){
        log.info ("page = {},rows = {}", page, rows);

        Page advicePage = new Page ( page,rows );

        iAdviceService.page (advicePage);
        return R.success (advicePage);
    }

    @PostMapping
    public R<String> add(@RequestBody Advice advice){
        advice.setCtime (LocalDateTime.now ());
        LambdaQueryWrapper<Advice> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Advice::getId,advice.getId ());
        iAdviceService.saveOrUpdate (advice,queryWrapper);
        return R.success ("成功");
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Integer id){
        iAdviceService.removeById (id);
        return R.success ("成功");
    }
}

