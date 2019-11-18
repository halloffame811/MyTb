<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<#include "../common/header.ftl">
<body>
<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${url}/mytb/ProductInfoSeller/productUpdate">
                <div class="form-group">
                    <label name="productId">商品Id</label>
                    <input type="text" class="form-control" name="productId" value="${product.productId}"
                           placeholder="商品Id">
                </div>
                <div class="form-group">
                    <label name="productName">商品名称</label>
                    <input type="text" class="form-control" name="productName" value="${product.productName}"
                           placeholder="商品名称">
                </div>
                <div class="form-group">
                    <label name="productPrice">价格</label>
                    <input type="text" class="form-control" name="productPrice" value="${product.productPrice}"
                           placeholder="价格">
                </div>
                <div class="form-group">
                    <label name="productStock">库存</label>
                    <input type="text" class="form-control" name="productStock" value="${product.productStock}"
                           placeholder="库存">
                </div>
                <div class="form-group">
                    <label name="productCategory">类目</label>
                    <select name="categoryType" class="form-control">
                        <#list categoryType as ct>
                            <#if ct.categoryType==product.categoryType>
                                <option name="categoryType" value="${ct.categoryType}"
                                        selected="selected">${ct.categoryName}</option>
                            <#else >
                                <option name="categoryType" value="${ct.categoryType}">${ct.categoryName}</option>
                            </#if>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                    <label name="productDescription">描述</label>
                    <textarea class="form-control" rows="3" name="productDescription"
                              value="${product.productDescription}"
                              placeholder="请输入商品描述">${product.productDescription}</textarea>
                </div>

                <div class="form-group">
                    <label name="productStatus">商品状态</label>
                    <select name="productStatus" class="form-control">
                        <#if product.productStatus==1>
                            <option name="productStatus" value="1" selected="selected">上架</option>
                            <option name="productStatus" value="0">下架}</option>
                        <#else >
                            <option name="productStatus" value="1">上架</option>
                            <option name="productStatus" value="0" selected="selected">下架</option>
                        </#if>
                    </select>
                </div>
                <div class="form-group">
                    <label name="productIcon">图片地址</label>
                    <input type="text" class="form-control" name="productIcon" value="${product.productIcon}"
                           placeholder="图片地址">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg">确认修改</button>
                </div>
                </br>
                <div class="form-group">
                    <label name="icon">商品预览</label>
                </div>
                <div class="form-group">
                    <img src="${product.productIcon}" width="200" height="200">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>

