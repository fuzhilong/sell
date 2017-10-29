package com.fzl.sell.service.impl;

import com.fzl.sell.bean.ProductInfo;
import com.fzl.sell.dao.ProductInfoDao;
import com.fzl.sell.dto.CartDTO;
import com.fzl.sell.enums.ProductStatusEnum;
import com.fzl.sell.enums.ResultEnum;
import com.fzl.sell.exception.SellException;
import com.fzl.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo=productInfoDao.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //查询库存是否大于当前购买数量
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }
}
