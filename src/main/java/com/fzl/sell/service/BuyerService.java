package com.fzl.sell.service;

import com.fzl.sell.dto.OrderDTO;

public interface BuyerService {
    public OrderDTO findOrderOne(String openid,String orderid);
    public OrderDTO cancelOrder(String openid,String orderid);
}
