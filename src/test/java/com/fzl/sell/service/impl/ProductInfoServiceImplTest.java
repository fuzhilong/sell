package com.fzl.sell.service.impl;

import com.fzl.sell.bean.ProductInfo;
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
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo=productInfoService.findOne("1");
        System.out.println(productInfo);
        Assert.assertEquals("1",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList=productInfoService.findUpAll();
        System.out.println(productInfoList);
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> result=productInfoService.findAll(request);
        System.out.println(result.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("临高乳猪");
        productInfo.setProductPrice(new BigDecimal(100.01));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("这是临高乳猪");
        productInfo.setProductIcon("douwen.jepg");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(1);
        productInfoService.save(productInfo);
    }

}
