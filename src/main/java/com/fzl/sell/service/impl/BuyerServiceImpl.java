package com.fzl.sell.service.impl;

import com.fzl.sell.dto.OrderDTO;
import com.fzl.sell.enums.ResultEnum;
import com.fzl.sell.exception.SellException;
import com.fzl.sell.service.BuyerService;
import com.fzl.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return  checkOrderOwener(openid,orderid);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO=  checkOrderOwener(openid,orderid);
        if(orderDTO==null){
            log.error("【取消订单】 查不到该订单，orderid={},o",orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwener(String openid, String orderid){
        OrderDTO orderDTO= orderService.findOne(orderid);
        if(orderDTO==null){
            return null;

        }
        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】 订单的openid不一致，openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);

        }
        return orderDTO;
    }

}
