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
    <link rel="stylesheet" href="/gymshoesbbs/res/layui/css/layui.css">
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
            <h1 class="page-title">会员管理</h1>
        </div>
                <ul class="breadcrumb">
            <li><a href="admin/index.jsp">Home</a> <span class="divider">/</span></li>
            <li class="active">会员管理</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
            	<div class="btn-toolbar">
					<div class="search-well">
						<form action="admin/searchMember.do" method="post" class="form-inline">
							<input class="input-xlarge" placeholder="用户名或姓名..." id="key" name="key" type="text" value="${key}">
		                    <button class="btn" type="submit"><i class="icon-search"></i> Go</button>
						</form>	
					</div>  	
				</div>
<div class="well">

    <table class="table">
      <thead>
        <tr>
          <th>登陆名</th>
          <th>登陆密码</th>
          <th>姓名</th>
          <th>性别</th>
          <th>联系电话</th>
          <th>email</th>
          <th>qq</th>
          <th>注册日期</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${list}" var="member">
        <tr>
          <td>${member.uname}</td>
          <td>${member.upass}</td>
          <td>${member.tname}</td>
          <td>${member.sex}</td>
          <td>${member.tel}</td>
          <td>${member.email}</td>
          <td>${member.qq}</td>
          <td>${member.savetime}</td>
          <td>
             <c:if test="${member.shstatus eq '通过审核'}">
          		  
	          	  <c:choose>
	          	    <c:when test="${member.isjy eq 'no'}">
	          	    <a href="javascript:jy('${member.id}')" >禁言</a>
	          	    </c:when>
	          	    <c:otherwise>
	          	    <a href="javascript:jyjc('${member.id}')" >禁言解除</a>
	          	    </c:otherwise>
	          	  </c:choose>
	          	  
	          	  
	          	  <c:choose>
	          	    <c:when test="${member.isfh eq 'no'}">
	          	    <a href="javascript:fh('${member.id}')" >封号</a>
	          	    </c:when>
	          	    <c:otherwise>
	          	    <a href="javascript:fhjc('${member.id}')" >封号解除</a>
	          	    </c:otherwise>
	          	  </c:choose>
          	  </c:if>
          	    
          	    <c:choose>
	          	    <c:when test="${member.shstatus eq '待审核'}">
	          	    <a href="admin/shStatus.do?id=${member.id}&type=tg" >通过</a>
	          	    <a href="admin/shStatus.do?id=${member.id}&type=jj" >拒绝</a>
	          	    </c:when>
	          	  </c:choose>
              <a href="admin/memberDel.do?id=${member.id}" ><i class="icon-remove"></i></a>
          </td>
        </tr>
        </c:forEach>
        <tr>
         <td style="font-weight: bold;font-family:楷体;font-weight: bold; color:blue;text-align: right;" colspan="9">
                                                                 共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%=path %>/admin/memberList?index=1" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
                        
                        <c:choose>
                        <c:when test="${index >1}">
                        <a href="<%=path %>/admin/memberList.do?index=${index-1}" style="font-family:微软雅黑;">上一页</a>
                        </c:when>
                        <c:otherwise>
                        <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
                        </c:otherwise>
                        </c:choose>
                        &nbsp;&nbsp;
                        <c:choose>
                        <c:when test="${pages>index}">
                        <a href="<%=path %>/admin/memberList.do?index=${index+1}" style="font-family:微软雅黑;">下一页</a>
                        </c:when>
                        <c:otherwise>
                        <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
                        </c:otherwise>
                        </c:choose>
                        &nbsp;&nbsp;
                        <a href="<%=path %>/admin/memberList.do?index=${pages}" style="font-family:微软雅黑;">末页</a>
                    </td>
        </tr>
      </tbody>
    </table>
</div>
</div>
</div>
</div>

	<script src="/gymshoesbbs/res/layui/layui.js"></script>
    <script src="/gymshoesbbs/admin/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
     function jy(id){
					
					$.ajax({  
				        type: "POST",      
				        url: "admin/jinYan.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	if(data==0){
				        		alert('禁言成功!');
				        		location.href="admin/memberList.do";
				        	}
				        	
				 		}     
					});
	}
	
	     function jyjc(id){
					
					$.ajax({  
				        type: "POST",      
				        url: "admin/jinyanJc.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	if(data==0){
				        		alert('操作成功!');
				        		location.href="admin/memberList.do";
				        	}
				        	
				 		}     
					});
	}
	
	function fh(id){
					$.ajax({  
				        type: "POST",      
				        url: "admin/fengHao.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	if(data==0){
				        		alert('封号成功!');
				        		location.href="admin/memberList.do";
				        	}
				        	
				 		}     
					});
	}
	     function fhjc(id){
					$.ajax({  
				        type: "POST",      
				        url: "admin/jiechuFenhao.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	if(data==0){
				        		alert('操作成功!');
				        		location.href="admin/memberList.do";
				        	}
				        	
				 		}     
					});
		}
    </script>
  </body>
</html>
