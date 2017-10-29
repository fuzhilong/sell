package com.fzl.sell.dao;

import com.fzl.sell.bean.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("空心菜");
        productInfo.setProductPrice(new BigDecimal(2.01));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("这是多文空心菜");
        productInfo.setProductIcon("douwen.jepg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        productInfoDao.save(productInfo);
    }
    @Test
    @Transactional
    public void  updateTest(){
        ProductInfo productInfo=productInfoDao.findOne("1");
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("更新成上架的多文空心菜");
        productInfoDao.save(productInfo);
    }
    @Test
    public void findOneTest(){
        ProductInfo productInfo=productInfoDao.findOne("1");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> resultList=productInfoDao.findByProductStatus(0);
        System.out.println(resultList.toString());
        Assert.assertNotEquals(0,resultList.size());
    }

}
