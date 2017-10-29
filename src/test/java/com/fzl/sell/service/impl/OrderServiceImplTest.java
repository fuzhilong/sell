package com.fzl.sell.service.impl;

import com.fzl.sell.bean.OrderDetail;
import com.fzl.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private String openid="16816888";
    @Test
    public void create() throws Exception {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("临高人");
        orderDTO.setBuyerAddress("海口市");
        orderDTO.setBuyerPhone("123456789168");
        orderDTO.setBuyerOpenid(openid);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2=new OrderDetail();
        orderDetail2.setProductId("2");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        log.info("[创建订单]result={}",result);
        Assert.assertNotNull(result);


    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}