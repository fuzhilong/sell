package com.fzl.sell.controller;

import com.fzl.sell.bean.ProductCategory;
import com.fzl.sell.bean.ProductInfo;
import com.fzl.sell.service.ProductCategoryService;
import com.fzl.sell.service.ProductInfoService;
import com.fzl.sell.utils.ResultVoUtils;
import com.fzl.sell.vo.ProductInfoVo;
import com.fzl.sell.vo.ProductVo;
import com.fzl.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //1、查询所有上架的商品
        List<ProductInfo> productInfoList=productInfoService.findUpAll();
        //2、查询类目(一次性查询)
        /*
        List<Integer> categoryTypeList=new ArrayList<>();
        //传统遍历添加
        for (ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
*/
        //精简遍历(java8,lambda)
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3、数据拼装
        List<ProductVo>  productVoList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVo productVo=new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVoList=new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtils.susscess(productVoList);
    }
}
