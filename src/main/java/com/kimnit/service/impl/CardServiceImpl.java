package com.kimnit.service.impl;

import com.kimnit.entity.Card;
import com.kimnit.mapper.CardMapper;
import com.kimnit.service.ICardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

}
