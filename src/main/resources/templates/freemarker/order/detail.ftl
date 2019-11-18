<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled" >
    <#--边栏-->
    <#include "../common/nav.ftl">

<#--内容-->
<div id="page-content-wrapper">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>用户名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>openId</th>
                    <th>总金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th>修改时间</th>

                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${orderDetail.orderId}</td>
                        <td>${orderDetail.buyerName}</td>
                        <td>${orderDetail.buyerPhone}</td>
                        <td>${orderDetail.buyerAddress}</td>
                        <td>${orderDetail.buyerOpenid}</td>
                        <td>${orderDetail.orderAmount}</td>
                        <td>${orderDetail.getOrderStatusEnum().getName()}</td>
                        <td>${orderDetail.getPayStatusEnum().getName()}</td>
                        <td>${orderDetail.createTime}</td>
                        <td>${orderDetail.updateTime}</td>
                    </tr>
                </tbody>
            </table>
            <hr color="red" size="5px" noshade="true">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>商品名称</th>
                    <th>图片</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>

                </tr>
                </thead>
                <tbody>
                <#list orderDetail.orderDetailList as detail>
                    <tr>
                        <td>${detail.productId}</td>
                        <td>${detail.productName}</td>
                        <td><img src="${detail.productIcon}" width="100" height="100"></td>
                        <td>${detail.productPrice}￥</td>
                        <td>${detail.productQuantity}</td>
                        <td>${detail.productQuantity * detail.productPrice}</td>

                    </tr>
                </#list>
                </tbody>
            </table>
            <div class="col-md-12 column">
                <#if orderDetail.getOrderStatusEnum().getName() =="新订单" >
                <a href="${url}/mytb/OrderMasterSeller/orderFinish?orderId=${orderDetail.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="${url}/mytb/OrderMasterSeller/orderCancel?orderId=${orderDetail.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
            </div>

        </div>
    </div>
</div>
</body>
</html>