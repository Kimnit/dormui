package com.kimnit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Card;
import com.kimnit.service.ICardService;
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
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    private ICardService iCardService;

    @GetMapping
    public R<Page<Card>> page(int page, int rows,int sure){
        log.info ("page = {},rows = {},sure = {}", page, rows, sure);

        Page<Card> cardPage = new Page<Card> ( page,rows );

        if (sure == 0){
            LambdaQueryWrapper<Card> queryWrapper = new LambdaQueryWrapper<> ();
            queryWrapper.eq (Card::getSure,sure);
            iCardService.page (cardPage,queryWrapper);

            return R.success (cardPage);
        }else {
            iCardService.page (cardPage);

            return R.success (cardPage);
        }
    }

    @PutMapping
    public R<String> add(@RequestBody Card card){
        card.setSure (1);
        card.setCtime (LocalDateTime.now (  ));
        LambdaQueryWrapper<Card> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Card::getStuid,card.getStuid ());
        iCardService.update (card,queryWrapper);
        return R.success ("成功");
    }
}

