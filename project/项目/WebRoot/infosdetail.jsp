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
  	<script charset="utf-8" src="/gymshoesbbs/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="/gymshoesbbs/layer/jquery-2.0.3.min.js"></script>
	<script src="/gymshoesbbs/layer/layer.js"></script>

	
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="note"]', {
				afterBlur: function () { this.sync(); },
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


<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8 content detail">
      <div class="fly-panel detail-box">
        <h1>${infos.title }</h1>
        <div class="fly-detail-info">

          
          
          
          
          <span class="fly-list-nums"> 
          
        
          </span>
        </div>
        <div class="detail-about">
          <div class="fly-detail-user">
            <span> ${infos.savetime }</span>
          </div>
          
        </div>
        <div class="detail-body photos">
          ${infos.note }
        </div>
      </div>



<div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
            <legend>评论</legend>
          
        </fieldset>
        
        
        <ul class="jieda" id="jieda">
        
        <c:forEach items="${pjlist}" var="pj">
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="Home.do?memberid=${pj.htmember.id}">
                <img src="<%=path %>/upload/${pj.htmember.filename}" alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="Home.do?memberid=${pj.htmember.id}" class="fly-link">
                  <cite>${pj.htmember.tname}</cite>
                  <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                </a>
              </div>

              <div class="detail-hits">
                <span>${pj.savetime }&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </div>

              
            </div>
            <div class="detail-body jieda-body photos">
              <p>${pj.note}</p>
            </div>
            <div class="jieda-reply">
            </div>
          </li>
          </c:forEach>
          
          <!-- 无数据时 -->
          <!-- <li class="fly-none">消灭零回复</li> -->
        </ul>
        <div style="text-align: center">
	        <div >
	        	<div id="LAY_page">
	        	共${total}条记录&nbsp;&nbsp;&nbsp;&nbsp;
			          <a href="infosShowQT.do?index=1&id=${id}" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
			          
			          <c:choose>
			          <c:when test="${index >1}">
			          <a href="infosShowQT.do?index=${index-1}&id=${id}" style="font-family:微软雅黑;">上一页</a>
			          </c:when>
			          <c:otherwise>
			          <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
			          </c:otherwise>
			          </c:choose>
			          &nbsp;&nbsp;
			          <c:choose>
			          <c:when test="${pages>index}">
			          <a href="infosShowQT.do?index=${index+1}&id=${id}" style="font-family:微软雅黑;">下一页</a>
			          </c:when>
			          <c:otherwise>
			          <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
			          </c:otherwise>
			          </c:choose>
			          &nbsp;&nbsp;
			          <a href="infosShowQT.do?index=${pages}&id=${id}" style="font-family:微软雅黑;">末页</a>
	        	
	        	</div>
	        </div>
        </div>
        <div class="layui-form layui-form-pane">
            <div class="layui-form-item layui-form-text">
              <a name="comment"></a>
              <div class="layui-input-block">
                <textarea id="note" name="note"  class="layui-textarea "  style="height: 150px;" ></textarea>
              </div>
            </div>
            <div class="layui-form-item">
            	<input type="hidden" id="infoid" name="infoid" value="${infos.id }">
              <button class="layui-btn" onclick="checksub()">提交评论</button>
            </div>
        </div>
        
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

function checksub(){
	var infoid = document.getElementById("infoid").value;
	var note = $('#note').val();
	location.replace("infospjAdd.do?infoid="+infoid+"&note="+note);
}


</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
