package com.fzl.sell.dao;

import com.fzl.sell.bean.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("家常菜");
        productCategory.setCategoryType(1);
        dao.save(productCategory);


    }
    @Test
    public void findOneTest(){
        ProductCategory productCategory=dao.findOne(1);
        System.out.println(productCategory.toString());
    }
    @Test
    public void updateTest(){
        ProductCategory productCategory=dao.findOne(1);
        productCategory.setCategoryName("家常菜3");
        dao.save(productCategory);
    }

    @Test
    public void  findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(1,2,3);
        List<ProductCategory> categoryList=dao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,categoryList.size());//不等于0时，说明成功
    }

}
