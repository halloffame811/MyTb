package com.hzy.mytb.mEnum;

import lombok.Getter;

@Getter
public enum ResultVOEnum {
    ERROR("失败", 0),
    SUCCESS("成功", 1),
    PARAMS_ERROR("表单参数不正确",2),

    CRUD_ERROR("操作数据库时出错",3),

    PRODUCT_NOT_EXIST("商品不存在", 1002),
    PRODUCT_STOCK_NOT_ENOUGH("库存不足", 1003),
    PRODUCT_STOCK_UPDATE_ERROR("库存更新失败", 1004),
    PRODUCT_STATUS_UPDATE_SUCCESS("商品上下架状态更新成功", 1005),
    PRODUCT_STATUS_UPDATE_ERROR("商品上下架状态更新失败", 1006),
    PRODUCT_UPDATE_ERROR("商品信息更新失败", 1006),
    PRODUCT_UPDATE_SUCCESS("商品信息更新成功", 1007),
    PRODUCT_ADD_ERROR("商品添加失败", 1008),
    PRODUCT_ADD_SUCCESS("商品添加成功", 1009),

    ORDER_NOT_EXIST("订单不存在", 2005),
    ORDER_DETAIL_NOT_EXIST("订单详情不存在",2006),
    ORDER_CAN_NOT_CANCEL("订单已经取消或者完结，无法取消！",2007),
    ORDER_CAN_NOT_FINISH("订单已经完结或者取消,无法完结",2008),
    ORDER_CAN_NOT_PAY("订单已经完结或者支付,无法再次支付",2009),
    ORDER_STATUS_UPDATE_ERROR("修改订单状态失败！",2010),
    ORDER_DETAIL_IS_EMPTY("商品详情列表为空",2011),
    ORDER_PAY_ERROR("订单支付失败！",2012),
    ORDER_LIST_IS_EMPTY("用户还未下订单，订单列表为空",2013),
    ORDER_AND_OPENID_NOT_MATE("订单和openId不匹配，不可查询他人订单",2014),

    CART_CAN_NOT_EMPTY("下单时购物车不能为空",3001),

    WECHAT_MP_ERROR("微信公众号错误",4001),

    WECHAT_OPEN_USER_INFO_ERROR("微信公众平台拉取用户信息错误",6002),
    WECHAT_OPEN_USER_INFO_SUCCESS("微信公众平台拉取用户信息错误",6001),

    SELLER_ORDER_CANCEL_ERROR("商家取消订单失败！",5001),
    SELLER_ORDER_CANCEL_SUCCESS("商家取消订单成功！",5002),
    SELLER_ORDER__DETAIL_ERROR("卖家获取订单详情失败！",5003),
    SELLER_ORDER_FINISH_SUCCESS("商家完结订单成功！",5004),
    SELLER_ORDER__FINISH_ERROR("卖家完结订单失败！",5005),

    ;


    private String msg;
    private Integer code;

    ResultVOEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
