<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
	<meta charset="utf-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <meta name="description" content="Admin panel developed with the Bootstrap from Twitter.">
    <meta name="author" content="travis">
    
	<link rel="stylesheet" type="text/css" href="/gymshoesbbs/admin/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/gymshoesbbs/admin/stylesheets/theme.css">
    <link rel="stylesheet" href="/gymshoesbbs/admin/lib/font-awesome/css/font-awesome.css">

    <script src="/gymshoesbbs/admin/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	
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
            <h1 class="page-title">敏感字符</h1>
        </div>
                <ul class="breadcrumb">
            <li><a href="admin/index.jsp">Home</a> <span class="divider">/</span></li>
            <li class="active">敏感字符</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<div class="btn-toolbar">
					<div class="search-well">
						<form action="admin/searchMgword.do" method="post" class="form-inline">
							<input class="input-xlarge" placeholder="字符..." id="key" name="key" type="text">
		                    <button class="btn" type="submit"><i class="icon-search"></i> Go</button>
						    <button class="btn btn-primary" onclick="linkadd()" type="button"><i class="icon-plus"></i> 敏感字符</button>
						</form>	
					</div>  	
				</div>
			
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th>敏感字符</th>
          <th style="width: 26px;"></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="mgword">
        <tr>
          <td>${mgword.wordnote}</td>
          <td>
              <a href="admin/mgwordShow.do?id=${mgword.id}"><i class="icon-pencil"></i></a>
              <a href="admin/mgwordDel.do?id=${mgword.id}" ><i class="icon-remove"></i></a>
          </td>
        </tr>
       </c:forEach>
        <tr >
         <td style="font-weight: bold;font-family:楷体;font-weight: bold; color:blue;text-align: right;" colspan="8">
                                                   共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="<%=path %>/admin/mgwordList.do?index=1" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
          
          <c:choose>
          <c:when test="${index >1}">
          <a href="<%=path %>/admin/mgwordList.do?index=${index-1}" style="font-family:微软雅黑;">上一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <c:choose>
          <c:when test="${pages>index}">
          <a href="<%=path %>/admin/mgwordList.do?index=${index+1}" style="font-family:微软雅黑;">下一页</a>
          </c:when>
          <c:otherwise>
          <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
          </c:otherwise>
          </c:choose>
          &nbsp;&nbsp;
          <a href="<%=path %>/admin/mgwordList.do?index=${pages}" style="font-family:微软雅黑;">末页</a>
      </td>
       </tr>
      </tbody>
    </table>
</div>
</div>
</div>
</div>
    <script src="/gymshoesbbs/admin/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
    	function linkadd(){
			location.href="admin/mgwordadd.jsp";
        }
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
  </body>
</html>
