<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.util.Info"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<meta charset="utf-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <meta name="description" content="Admin panel developed with the Bootstrap from Twitter.">
    <meta name="author" content="travis">
    
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/stylesheets/theme.css">
    <link rel="stylesheet" href="<%=path %>/admin/lib/font-awesome/css/font-awesome.css">

    <script src="<%=path %>/admin/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	
    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">

	
  </head>
  
  <body class="">

	<jsp:include page="/admin/top.jsp"></jsp:include>
    
    <jsp:include page="/admin/left.jsp"></jsp:include>
    

    
       <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">评论管理</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="admin/index.jsp">Home</a> <span class="divider">/</span></li>
            <li class="active">评论管理</li>
        </ul>

        
			
<div class="well">
    <table class="table" border=0>
      <thead>
        <tr>
          <th>评论内容</th>
          <th>评论人</th>
          <th>评论日期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${list}" var="pj">
        <tr>
          <td>${pj.note}</td>
          <td>${pj.htmember.tname}</td>
          <td>${pj.savetime}</td>
          <td>
		    <a href="<%=path %>/admin/delInfospj.do?id=${pj.id}&type=${type}&infoid=${infoid}" >删除</a>
		 
          </td>
        </tr>
       </c:forEach>
       <tr>
         <td style="font-weight: bold;font-family:楷体;font-weight: bold; color:blue;text-align: right;" colspan="8">
                             共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="<%=path %>/admin/infospjList.do?index=1&type=${type}&infoid=${infoid}" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
          
          <c:choose>
          <c:when test="${index >1}">
          <a href="<%=path %>/admin/infospjList.do?index=${index-1}&type=${type}&infoid=${infoid}" style="font-family:微软雅黑;">上一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <c:choose>
          <c:when test="${pages>index}">
          <a href="<%=path %>/admin/infospjList.do?index=${index+1}&type=${type}&infoid=${infoid}" style="font-family:微软雅黑;">下一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <a href="<%=path %>/admin/infospjList.do?index=${pages}&type=${type}&infoid=${infoid}" style="font-family:微软雅黑;">末页</a>
      </td>
       </tr>
      </tbody>
    </table>
</div>
<div class="pagination">
    ${page.info }
</div>

    <script src="/gymshoesbbs/admin/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
    	function protypeadd(type){
			location.href="admin/infosadd.jsp?type="+type;
        }
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
                function cxbz(memberid,fid){
					$.ajax({  
				        type: "POST",      
				        url: "admin/bzDel.do", //servlet的名字     
				        data: "memberid="+memberid+"&fid="+fid, 
				        success: function(data){   
				        	alert('操作成功!');
						    location.href="admin/infosList.do";
				 		}     
					});
				}
    </script>
  </body>
</html>
