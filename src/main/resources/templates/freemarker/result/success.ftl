<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div class="container center-block">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    操作成功！
                    </br>
                </h4> <strong>${msg}</strong> 跳转到【${pageMsg}】 <a href="${returnUrl}" class="alert-link">  <ins>点击此处</ins></a>
            </div>
        </div>
    </div>
</div>

</body>
</html>