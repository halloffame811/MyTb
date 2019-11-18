<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled" >
    <#--边栏-->
    <#include "../common/nav.ftl">
    <#--内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
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
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageResult.content as dto>
                            <tr>
                                <td>${dto.orderId}</td>
                                <td>${dto.buyerName}</td>
                                <td>${dto.buyerPhone}</td>
                                <td>${dto.buyerAddress}</td>
                                <td>${dto.buyerOpenid}</td>
                                <td>${dto.orderAmount}</td>
                                <td>${dto.getOrderStatusEnum().getName()}</td>
                                <td>${dto.getPayStatusEnum().getName()}</td>
                                <td>${dto.createTime}</td>
                                <td>${dto.updateTime}</td>
                                <td><a href="${url}/mytb/OrderMasterSeller/orderDetail?orderId=${dto.orderId}">详情</a></td>
                                <#if dto.orderStatus == 1 >
                                    <td><a href="${url}/mytb/OrderMasterSeller/orderCancel?orderId=${dto.orderId}">取消</a></td>
                                </#if>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column ">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li ><a href="${url}/mytb/OrderMasterSeller/orderList?size=${size}&page=${currentPage -1}">上一页</a></li>
                        </#if>
                        <#list 1..pageResult.getTotalPages() as pageIndex>
                            <#if currentPage == pageIndex>
                                <li class="disabled"><a href="#">${pageIndex}</a></li>
                            <#else >
                                <li><a href="${url}/mytb/OrderMasterSeller/orderList?size=${size}&page=${pageIndex}">${pageIndex}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte pageResult.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li ><a href="${url}/mytb/OrderMasterSeller/orderList?size=${size}&page=${currentPage +1}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>