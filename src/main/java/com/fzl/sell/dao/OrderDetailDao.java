package com.fzl.sell.dao;

import com.fzl.sell.bean.OrderDetail;
import com.fzl.sell.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
