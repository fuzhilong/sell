package com.fzl.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fzl.sell.bean.OrderDetail;
import com.fzl.sell.utils.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)//这个注解是代表某个字段的值为空时，就不返回它的字段给前端
public class OrderDTO {
    private  String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 支付状态
     */
    private Integer payStatus;
    @JsonSerialize(using =Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using =Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
