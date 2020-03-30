<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  	<script type="text/javascript" src="/gymshoesbbs/layer/jquery-2.0.3.min.js"></script>
  	<script src="/gymshoesbbs/layer/layer.js"></script>
  	<script type="text/javascript">
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
	    <li class="layui-nav-item">
	      <a href="memberCenter.do">
	        <i class="layui-icon">&#xe612;</i>
	        用户中心
	      </a>
	    </li>
	    <li class="layui-nav-item  layui-this">
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
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">我的资料</li>
        
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
          <form id="form" method="post" action="memberEdit.do">
          <input type="hidden" name="id" value="${member.id}"/>
            		  <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">姓名</label>
		                <div class="layui-input-inline">
		                  <input type="text" id="tname" name="tname" value="${member.tname}" required oninvalid="setCustomValidity('姓名不能为空')" oninput="setCustomValidity('');"  autocomplete="off" class="layui-input">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_pass" class="layui-form-label">密码</label>
		                <div class="layui-input-inline">
		                  <input type="password" id="upass" name="upass" required lay-verify="required" autocomplete="off" class="layui-input">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_repass" class="layui-form-label">确认密码</label>
		                <div class="layui-input-inline">
		                  <input type="password" id="upass1" name="upass1" required lay-verify="required" autocomplete="off" class="layui-input" onblur="validatorpwd()">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">性别</label>
		                <div class="layui-input-block">
					      <input type="radio"  name="sex" value="男" title="男" ${member.sex=='男'?'checked':''}>
					      <input type="radio" name="sex" value="女" title="女" ${member.sex=='女'?'checked':''}>
					    </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">头像</label>
		              	<div class="layui-upload">
						  <input name='filename' type='text' class="yanse" id='url' value="${member.filename}"  required class="layui-input" style="width: 190px;height: 38px;border-color: 1px solid #FBFBFB"/>&nbsp;<input type='button' value='上传' onClick="up('url')" style="width: 60px;height: 37px;"/>
						</div>
					 </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">电子邮箱</label>
		                <div class="layui-input-inline">
		                  <input type="email" id="email" name="email" value="${member.email}" required lay-verify="email" autocomplete="off" class="layui-input">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">QQ</label>
		                <div class="layui-input-inline">
		                  <input type="text" id="qq" name="qq" value="${member.qq}" required lay-verify="required" autocomplete="off" class="layui-input">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">电话</label>
		                <div class="layui-input-inline">
		                  <input type="text" id="tel" name="tel" value="${member.tel}" required oninvalid="setCustomValidity('电话不能为空')" oninput="setCustomValidity('');" autocomplete="off" class="layui-input">
		                </div>
		              </div>
		              <div class="layui-form-item">
		                <label for="L_username" class="layui-form-label">地址</label>
		                <div class="layui-input-inline">
		                  <input type="text" id="addr" name="addr" value="${member.addr}" required oninvalid="setCustomValidity('地址不能为空')" oninput="setCustomValidity('');" autocomplete="off" class="layui-input">
		                </div>
		              </div>
            <div class="layui-form-item">
              <button class="layui-btn">确认修改</button>
            </div>
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


	<%
	String msg = (String)request.getAttribute("msg");
	if(msg!=null){
	%>
	layer.msg('<%=msg%>');
	<%}%>


</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
