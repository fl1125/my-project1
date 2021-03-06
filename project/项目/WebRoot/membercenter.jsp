<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
  </head>
  
  <body>
    <jsp:include page="top.jsp"></jsp:include>

	<div class="layui-container fly-marginTop fly-user-main">
	  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
	    <li class="layui-nav-item">
	      <a href="Home.do?memberid=${sessionScope.member.id}">
	        <i class="layui-icon">&#xe609;</i>
	        我的主页
	      </a>
	    </li>
	    <li class="layui-nav-item layui-this">
	      <a href="memberCenter.do">
	        <i class="layui-icon">&#xe612;</i>
	        用户中心
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="memberSet.do">
	        <i class="layui-icon">&#xe620;</i>
	        基本设置
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="bzApply.do">
	        <i class="layui-icon">&#xe857;</i>
	       版主申请
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="myFollow.do">
	        <i class="layui-icon">&#xe658;</i>
	        我的关注
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="myPingbi.do">
	        <i class="layui-icon">&#x1006;</i>
	        屏蔽用户
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="mysendSx.do">
	        <i class="layui-icon">&#xe63a;</i>
	        我的私信
	      </a>
	    </li>
	  </ul>
	
	  <div class="site-tree-mobile layui-hide">
	    <i class="layui-icon">&#xe602;</i>
	  </div>
	  <div class="site-mobile-shade"></div>
	  
	  <div class="site-tree-mobile layui-hide">
	    <i class="layui-icon">&#xe602;</i>
	  </div>
	  <div class="site-mobile-shade"></div>
	  
	  
	  <div class="fly-panel fly-panel-user" pad20>
	    <!--
	    <div class="fly-msg" style="margin-top: 15px;">
	      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
	    </div>
	    -->
	    <div class="layui-tab layui-tab-brief" lay-filter="user">
	      <ul class="layui-tab-title" id="LAY_mine">
	      
	      	<li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>${fn:length(list)}</span>）</li>
	      <!-- <li><a href="myfav.jsp">我收藏的帖（<span>${fn:length(list)}</span>）</a></li>  --> 
	        <li><a href="myHf.do">我回复的帖（<span>${fn:length(myhflist) }</span>）</a></li>
	      </ul>
	      <div class="layui-tab-content" style="padding: 20px 0;">
	        <div class="layui-tab-item layui-show">
	          <ul class="mine-view jie-row">
	          <c:forEach items="${list}" var="tzinfo">
	            <li>
	              <p><a class="layui-badge" href="tzlist.jsp?sid=${tzinfo.stype.id}">&nbsp;&nbsp;${tzinfo.stype.typename}</a></p>  
	              <a class="jie-title" href="tzDetail.do?id=${tzinfo.id}" target="_blank">&nbsp;&nbsp;&nbsp;${tzinfo.title}</a>
	              <i></i>
	              <a class="mine-edit" href="tzinfoShow.do?id=${tzinfo.id }">编辑</a>
	              <a class="mine-edit" href="javascript:tzdel('${tzinfo.id }')">删除</a>
	              <em>${tzinfo.looknum}阅/${fn:length(tzinfo.allhtlist) }答</em>
	            </li>
	            </c:forEach>
	            
	          </ul>
	          <div id="LAY_page">
	          <ul>
	            <li style="color: #009688">
                                                                 共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%=path %>/memberCenter.do?index=1" style="font-family:微软雅黑;color:#009688;font-weight: bold;">首页</a>&nbsp;&nbsp;
                        
                        <c:choose>
                        <c:when test="${index >1}">
                        <a href="<%=path %>/memberCenter.do?index=${index-1}" style="font-family:微软雅黑;color:#009688;font-weight: bold;">上一页</a>
                        </c:when>
                        <c:otherwise>
                        <a href="javascrip:void(0)" style="font-family:微软雅黑;color:#009688;font-weight: bold;">上一页</a>
                        </c:otherwise>
                        </c:choose>
                        &nbsp;&nbsp;
                        <c:choose>
                        <c:when test="${pages>index}">
                        <a href="<%=path %>/memberCenter.do?index=${index+1}" style="font-family:微软雅黑;color:#009688;font-weight: bold;">下一页</a>
                        </c:when>
                        <c:otherwise>
                        <a href="javascrip:void(0)" style="font-family:微软雅黑;color:#009688;font-weight: bold;">下一页</a>
                        </c:otherwise>
                        </c:choose>
                        &nbsp;&nbsp;
                        <a href="<%=path %>/memberCenter.do?index=${pages}" style="font-family:微软雅黑;color:#009688;font-weight: bold;">末页</a>
	            </li>
	          </ul>
	          </div>
	        </div>
	        <div class="layui-tab-item">
	          <div id="LAY_page1"></div>
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




function tzdel(id){
	
	$.ajax({  
        type: "POST",      
        url: "admin/tzinfoDel.do", //servlet的名字     
        data: "id="+id, 
        success: function(data){   
        	if(data==0){
        		alert('删除成功!');
        		location.href="memberCenter.do";
        	}
        	
 		}     
	});
}
</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
