package com.kimnit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Bad_goods;
import com.kimnit.service.IBad_goodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/badgoods")
@Slf4j
public class Bad_goodsController {

    @Autowired
    private IBad_goodsService iBad_goodsService;

    @GetMapping
    public R<Page<Bad_goods>> page(int page, int rows, String goods){
        log.info ("page = {},rows = {}, goods = {}", page, rows, goods);

        Page<Bad_goods> badGoodsPage = new Page<Bad_goods> ( page,rows );

        LambdaQueryWrapper<Bad_goods> queryWrapper = new LambdaQueryWrapper (  );
        //添加一个过滤条件
        queryWrapper.like (StringUtils.hasText (goods), Bad_goods::getGoods,goods);
        //添加排序
        queryWrapper.orderByAsc (Bad_goods::getId);

        //执行查询
        iBad_goodsService.page (badGoodsPage, queryWrapper);

        return R.success (badGoodsPage);
    }

    @PostMapping
    public R<String> add(@RequestBody Bad_goods bad_goods){
        bad_goods.setSubtime (LocalDateTime.now ());
        iBad_goodsService.save (bad_goods);
        return R.success ("成功");
    }

    @PutMapping
    public R<String> end(@RequestBody Bad_goods bad_goods){
        bad_goods.setRsotime (LocalDateTime.now ());
        iBad_goodsService.updateById (bad_goods);
        return R.success ("完成维修");
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Integer id){
        iBad_goodsService.removeById (id);
        return R.success ("成功");
    }
}

