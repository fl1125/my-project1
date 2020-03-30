<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.util.Info"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    <div class="layui-col-md4">

      <div class="fly-panel">
        <h3 class="fly-panel-title">站内资讯</h3>
        <ul class="fly-panel-main fly-list-static">
        <c:forEach items="${newslist}" var="news">
          <li style="list-style: none;">
            <a href="newsDetail.do?id=${news.id}" style="color:#333333">${news.title}</a>
          </li>
        </c:forEach>
        </ul>
      </div>


      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title">今日话题</dt>
        <c:forEach items="${nowtzinfolist}" var="tzinfo">
        <dd>
          <a href="tzDetail.do?id=${tzinfo.id}">${tzinfo.title}</a>
          <span> </span>
        </dd>
        </c:forEach>

        <!-- 无数据时 -->
        <!--
        <div class="fly-none">没有相关数据</div>
        -->
      </dl>
      
      
      
      <div class="fly-panel fly-link">
        <h3 class="fly-panel-title">友情链接</h3>
        <dl class="fly-panel-main">
        <c:forEach items="${yqlinklist}" var="yqlink">
          <dd><a href="${yqlink.linkurl}" target="_blank">${yqlink.linkname}</a><dd>
       </c:forEach>
        </dl>
      </div>

    </div>
  </body>
</html>
