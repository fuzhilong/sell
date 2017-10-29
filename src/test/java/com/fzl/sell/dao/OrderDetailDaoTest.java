package com.fzl.sell.dao;

import com.fzl.sell.bean.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Test
    public void  saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("12345");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("1");
        orderDetail.setProductName("空心菜");
        orderDetail.setProductPrice(new BigDecimal(2.01));
        orderDetail.setProductQuantity(4);
        orderDetail.setProductIcon("douwen.jepg");
        OrderDetail result=orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> list=orderDetailDao.findByOrderId("123456");
        System.out.println(list);
        Assert.assertNotEquals(0,list.size());
    }

}
