package com.fzl.sell.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 */
@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 当前价格,单位分
     */
    private BigDecimal productPrice;
    /**
     * 数量
     */
    private Integer productQuantity;
    /**
     * 小图
     */
    private String productIcon;

}
