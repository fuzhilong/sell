package com.fzl.sell.service.impl;

import com.fzl.sell.bean.OrderDetail;
import com.fzl.sell.bean.OrderMaster;
import com.fzl.sell.bean.ProductInfo;
import com.fzl.sell.converter.OrderMaster2OrderDTOConverter;
import com.fzl.sell.dao.OrderDetailDao;
import com.fzl.sell.dao.OrderMasterDao;
import com.fzl.sell.dto.CartDTO;
import com.fzl.sell.dto.OrderDTO;
import com.fzl.sell.enums.OrderStatusEnum;
import com.fzl.sell.enums.PayStatusEnum;
import com.fzl.sell.enums.ResultEnum;
import com.fzl.sell.exception.SellException;
import com.fzl.sell.service.OrderService;
import com.fzl.sell.service.ProductInfoService;
import com.fzl.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        //1、查询商品(数量、价格)
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo=productInfoService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总金额(单价*数量,再加上每次遍历的数量)
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);//把productInfo的属性拷到orderDetail里面
            orderDetailDao.save(orderDetail);

        }
        //订单入库
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);//这里需要注意，先拷再设置值，这样就不会覆盖设置的值
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterDao.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList= orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);

        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage= orderMasterDao.findByBuyerOpenid(buyerOpenId,pageable);
       List<OrderDTO> orderDTOList= OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
       Page<OrderDTO> orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,
               orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
