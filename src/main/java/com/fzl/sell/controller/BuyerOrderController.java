package com.fzl.sell.controller;

import com.fzl.sell.form.OrderForm;
import com.fzl.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 买家订单
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    //创建订单
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,BindingResult bindingResult){
        return null;

    }
}
