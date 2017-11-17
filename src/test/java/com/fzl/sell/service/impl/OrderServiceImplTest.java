package com.fzl.sell.service.impl;

import com.fzl.sell.bean.OrderDetail;
import com.fzl.sell.dto.OrderDTO;
import com.fzl.sell.enums.OrderStatusEnum;
import com.fzl.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private String orderId="1509272307154468905";
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
       OrderDTO result= orderService.findOne(orderId);
      log.info("查询单个订单:"+result);
      Assert.assertEquals(orderId,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);
        log.info("订单列表："+orderDTOPage.getContent());
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO= orderService.findOne(orderId);
       OrderDTO result= orderService.cancel(orderDTO);
       Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO= orderService.findOne(orderId);
        OrderDTO result= orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO= orderService.findOne(orderId);
        OrderDTO result= orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

}