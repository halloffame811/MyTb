<html>
<#include "../common/header.ftl">
<script type="text/javascript">
    function updateStatus(productId, productStatus) {
        var req = confirm("是否修改状态？")
        if (req == true) {
            window.location.href = "${url}/mytb/ProductInfoSeller/productUpdateStatus?productId=" + productId + "&productStatus=" + productStatus;
        }
    }
</script>
<body>
<div id="wrapper" class="toggled">
    <#--边栏-->
    <#include "../common/nav.ftl">
    <#--内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column ">
                    <table class="table table-bordered table-condensed table-responsive">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>预览</th>
                            <th>价格</th>
                            <th>库存</th>
                            <th>状态[点击切换]</th>
                            <th>描述</th>
                            <th>操作</th>
                            <th>修改时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageResult.content as dto>
                            <tr>
                                <td>${dto.productId}</td>
                                <td>${dto.productName}</td>
                                <td><img src="${dto.productIcon}" width="100" height="100"></td>
                                <td>${dto.productPrice}</td>
                                <td>${dto.productStock}</td>

                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <#if dto.getProductStatusEnum().getName() =="上架">
                                            <p class="text-success"> ${dto.getProductStatusEnum().getName()} </p>
                                        <#else >
                                            <p class="text-danger"> ${dto.getProductStatusEnum().getName()} </p>
                                            </#if><span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <#if dto.getProductStatusEnum().getName() =="上架">
                                                <li><a href="javascript:updateStatus('${dto.productId}',0);"><p
                                                                class="text-danger">下架</p></a>
                                                </li>
                                            <#else >
                                                <li><a href="javascript:updateStatus('${dto.productId}',1);"><p
                                                                class="text-success">上架</p></a></li>
                                            </#if>
                                        </ul>
                                    </div>
                                </td>
                                <td>${dto.productDescription}</td>
                                <td><a href="${url}/mytb/ProductInfoSeller/findOneForUpdate?productId=${dto.productId}">修改</a>
                                </td>
                                <td>${dto.updateTime}</td>
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
                            <li><a href="${url}/mytb/ProductInfoSeller/productList?size=${size}&page=${currentPage -1}">上一页</a>
                            </li>
                        </#if>
                        <#list 1..pageResult.getTotalPages() as pageIndex>
                            <#if currentPage == pageIndex>
                                <li class="disabled"><a href="#">${pageIndex}</a></li>
                            <#else >
                                <li>
                                    <a href="${url}/mytb/ProductInfoSeller/productList?size=${size}&page=${pageIndex}">${pageIndex}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte pageResult.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="${url}/mytb/ProductInfoSeller/productList?size=${size}&page=${currentPage +1}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>