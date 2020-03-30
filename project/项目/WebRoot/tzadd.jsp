<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>球鞋资讯与交易网站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="/gymshoesbbs/res/layui/css/layui.css">
  	<link rel="stylesheet" href="/gymshoesbbs/res/css/global.css">
  	<script charset="utf-8" src="/gymshoesbbs/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/plugins/code/prettify.js"></script>
	
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="/gymshoesbbs/layer/layer.js"></script>

	
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="note"]', {
				cssPath : '/gymshoesbbs/kindeditor/plugins/code/prettify.css',
				uploadJson : '/gymshoesbbs/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/gymshoesbbs/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
					});
				}
			});
			prettyPrint();
		});

</script>
  </head>
  <body>
    <jsp:include page="top.jsp"></jsp:include>

	
	<div class="layui-container fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
    <!--<div class="fly-none">没有权限</div>-->
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form action="tzinfoAdd.do" method="post">
            <input type="hidden" id="author" name="author" value="${member.id}" >
              <div class="layui-row layui-col-space15 layui-form-item">
                <div class="layui-col-md6">
				      <label class="layui-form-label">所在版块</label>
				      <div class="layui-input-inline">
				        <select id="sid" name="sid">
				          <option value="">请选择版块</option>
				          <c:forEach items="${bbstypelist}" var="fathertype">
				          <optgroup label="${fathertype.typename}">
				            <c:forEach items="${fathertype.childlist}" var="childtype">
				            <option value="${childtype.id}">${childtype.typename}</option>
				            </c:forEach>
				          </optgroup>
				        </c:forEach>
				        </select>
				      </div>
				</div>
                <div class="layui-col-md6">
                	<label for="L_title" class="layui-form-label">允许回帖</label>
                  	<input type="radio" name="canht" value="yes" title="是" checked="">
				  	<input type="radio" name="canht" value="no" title="否">
                </div>
                
                <div class="layui-col-md9">
                  <label for="L_title" class="layui-form-label">标题</label>
                  <div class="layui-input-block">
                    <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                  </div>
                </div>
                
              </div>
              
              <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                  <textarea id="note" name="note"  class="layui-textarea "  style="height: 260px;" ></textarea>
                </div>
              </div>
              <c:choose>
                 <c:when test="${member.isjy eq 'no'}">
	              <div class="layui-form-item">
	                <button class="layui-btn" >立即发布</button>
	              </div>
	             </c:when>
	             <c:otherwise>
              	<div class="layui-form-item">
	                <a class="layui-btn">对不起,你已被禁言</a> 
	             </div>
	             </c:otherwise>
	          </c:choose>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
      

<jsp:include page="foot.jsp"></jsp:include>
 
<script src="/gymshoesbbs/res/layui/layui.js"></script>
<script>
layui.cache.page = '';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '/gymshoesbbs/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '/gymshoesbbs/res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
  fly: 'index'
}).use('fly');



function up(tt)
{
		    layer.open({
		      type: 2,
		      title: '上传文件',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['450px', '200px'],
		      content: '/gymshoesbbs/upload.jsp?Result='+tt
		    });
}
</script>


  </body>
</html>
