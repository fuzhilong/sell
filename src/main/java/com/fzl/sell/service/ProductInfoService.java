package com.fzl.sell.service;

import com.fzl.sell.bean.ProductInfo;
import com.fzl.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    /**
     * 查询单个商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加商品与更新商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
