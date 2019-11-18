<html>
<#include "../common/header.ftl">
<body>

<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${url}/mytb/ProductInfoSeller/productAdd">

                <div class="form-group">
                    <label name="productName">商品名称</label>
                    <input type="text" class="form-control" name="productName"
                           placeholder="商品名称">
                </div>
                <div class="form-group">
                    <label name="productPrice">价格</label>
                    <input type="text" class="form-control" name="productPrice"
                           placeholder="价格">
                </div>
                <div class="form-group">
                    <label name="productStock">库存</label>
                    <input type="text" class="form-control" name="productStock"
                           placeholder="库存">
                </div>
                <div class="form-group">
                    <label name="productCategory">请选择类目</label>
                    <select name="categoryType" class="form-control">
                        <#list productCategories as pc>
                                <option name="categoryType" value="${pc.categoryType}">${pc.categoryName}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label name="productDescription">描述</label>
                    <textarea  class="form-control" rows="3" name="productDescription"
                               placeholder="请输入商品描述"></textarea>
                </div>

                <div class="form-group">
                    <label name="productStatus">请选择状态[上架/下架]</label>
                    <select name="productStatus" class="form-control">
                            <option name="productStatus" value="1"><p class="text-success">上架</p></option>
                            <option name="productStatus" value="0"><p class="text-danger">下架</p></option>
                    </select>
                </div>
                <div class="form-group">
                    <label name="productIcon">图片地址</label>
                    <input type="text" class="form-control" name="productIcon"
                           placeholder="图片地址">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg">确认添加</button>
                </div>
                </br>
                <div class="form-group">
                    <label name="icon" >商品预览</label>
                </div>
                <div class="form-group">
                    <img src="" width="200" height="200">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>