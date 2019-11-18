<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<style type="text/css">
    h1{
            font-size:30px;
            color:#930;
            text-align:center;
        }

</style>

<body>
<h1>用户资料：</h1>
<img src="${userInfo.headimgurl}" width="200" height="200"></br>
<h2>昵称：${userInfo.nickname}</h2>
<h2>性别：
<#if userInfo.sex == "1">
    男
    <#else >
    女
</#if>
</h2>
<h2>省份：${userInfo.province}</br></h2>
<h2>城市：${userInfo.city}</br></h2>
<h2>国家：${userInfo.country}</br></h2>

</body>
</html>