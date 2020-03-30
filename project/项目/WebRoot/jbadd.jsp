<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String jbmemberid=request.getParameter("jbmemberid");
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
  	<script type="text/javascript" src="/gymshoesbbs/layer/jquery-2.0.3.min.js"></script>
  	<script type="text/javascript" src="/gymshoesbbs/layer/layer.js"></script>
  </head>
  
  <body>
    <jsp:include page="top.jsp"></jsp:include>
	<div class="layui-container fly-marginTop ">
	  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
        			<input type="hidden" id="memberid" name="memberid" value="${sessionScope.member.id}">
        			<input type="hidden" id="jbmemberid" name="jbmemberid" value="${jbmember.id }">
        			<div class="layui-form-item">
	        			<label for="L_username" class="layui-form-label">你正在举报</label>
			                <div class="layui-input-inline">
			                	<input type="text" id="uname" name="uname" readonly="readonly" value="${jbmember.tname}" required lay-verify="required" autocomplete="off" class="layui-input">
			                </div>
		            </div>
            		<div class="layui-form-item">
		              
		              <div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">举报原因</label>
					    <div class="layui-input-block">
					      <textarea id="note" name="note" placeholder="请输入内容" class="layui-textarea"></textarea>
					    </div>
					  </div>
				
		            <div class="layui-form-item">
		              <button class="layui-btn" onclick="sub()">提交</button>
		            </div>
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

	function sub(){
		var memberid = document.getElementById("memberid").value;
		var jbmemberid = document.getElementById("jbmemberid").value;
		var note = document.getElementById("note").value;
		
		if(note==""){
			layer.msg('请填写举报原因');
			return false;
		}
					$.ajax({  
				        type: "POST",      
				        url: "jbAdd.do", //servlet的名字     
				        data: "memberid="+memberid+"&jbmemberid="+jbmemberid+"&note="+note, 
				        success: function(data){   
						    if(data==0){     
						    	layer.msg('举报成功,等待管理员核实处理');
						    }else if(data==1){
						    	layer.msg('你已经举报过该用户');
						    }
				 		}     
					});
	}
</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
