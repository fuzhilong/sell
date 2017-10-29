package com.fzl.sell.dao;

import com.fzl.sell.bean.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("呵呵");
        orderMaster.setBuyerPhone("17789768803");
        orderMaster.setBuyerAddress("海口");
        orderMaster.setBuyerOpenid("mlm");
        orderMaster.setOrderAmount(new BigDecimal(2.59));
        OrderMaster result=orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest=new PageRequest(0,3);
        String openid="mlm";
        Page<OrderMaster> result=orderMasterDao.findByBuyerOpenid(openid,pageRequest);
        System.out.println(result.getContent());
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}
