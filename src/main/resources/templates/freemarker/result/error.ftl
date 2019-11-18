<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include "../common/header.ftl">
<body>

<div class="container center-block">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    操作失败！
                    </br>
                    原因：${msg}
                    </br>
                    代码：${code}
                </h4> <strong></strong> 跳转到订单列表页面: </br><a href="${url}/mytb/OrderMasterSeller/orderList" class="alert-link">  <ins>点击此处</ins></a>
            </div>
        </div>
    </div>
</div>

</body>
</html>