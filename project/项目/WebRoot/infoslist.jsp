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
	<link rel="stylesheet" href="/chinarensys/res/layui/css/layui.css">
  	<link rel="stylesheet" href="/chinarensys/res/css/global.css">
  </head>
  
  <body>
    <jsp:include page="top.jsp"></jsp:include>


<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8">

      <div class="fly-panel" style="margin-bottom: 0;">
        
        <div class="fly-panel-title fly-filter">
          <a class="layui-this">信息列表</a>
        </div>

        <ul class="fly-list">  
        <c:forEach items="${list}" var="infos">
          <li>
            <h2>
              <a href="infosShowQT.do?id=${infos.id }">${infos.title }</a>
            </h2>
            <div class="fly-list-info">
              
              <span>${infos.savetime }</span>
              
              <!--<span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
              <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
             
            </div>
            <div class="fly-list-badge">
           
            </div>
          </li>
          </c:forEach>
          
        </ul>
        <div style="text-align: center">
	        <div >
	        	<div id="LAY_page">
	        	共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="infosListQT.do?index=1&type=${type}" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
          
          <c:choose>
          <c:when test="${index >1}">
          <a href="admin/infosListQT.do?index=${index-1}&type=${type}" style="font-family:微软雅黑;">上一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <c:choose>
          <c:when test="${pages>index}">
          <a href="infosListQT.do?index=${index+1}&type=${type}" style="font-family:微软雅黑;">下一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <a href="infosListQT.do?index=${pages}&type=${type}" style="font-family:微软雅黑;">末页</a>
	        	
	        	</div>
	        </div>
        </div>
        <!-- <div style="text-align: center">
          <div class="laypage-main">
            <a href="jie/index.html" class="laypage-next">更多求解</a>
          </div>
        </div> -->

      </div>
    </div>
    
    <jsp:include page="right.jsp"></jsp:include>
  </div>
</div>

<jsp:include page="foot.jsp"></jsp:include>
 
<script src="/chinarensys/res/layui/layui.js"></script>
<script>
layui.cache.page = '';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '/chinarensys/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '/chinarensys/res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
  fly: 'index'
}).use('fly');
</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
