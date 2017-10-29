package com.fzl.sell.vo;

import lombok.Data;

/**
 * http请求返因的最外层对象
 */
@Data
public class ResultVo<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
}
