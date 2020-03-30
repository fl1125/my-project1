<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <h1>${tzinfo.title}</h1>
        <div class="fly-detail-info">

          <c:if test="${tzinfo.istop eq 'yes'}">
          <span class="layui-badge layui-bg-black">置顶</span>
          </c:if>
          <c:if test="${tzinfo.isjh eq 'yes'}">
          <span class="layui-badge layui-bg-red">精帖</span>
          </c:if>
          
          <div class="fly-admin-box" data-id="123">
          <c:if test="${isbz eq 'isbz'}">
            <span class="layui-btn layui-btn-xs jie-admin" type="del" onclick="delTzinfo('${tzinfo.id}')">删除</span>
            <c:if test="${tzinfo.istop eq 'no'}">
            <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1" onclick="tzzd('${tzinfo.id}')">置顶</span> 
            </c:if>
            <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->
            <c:if test="${tzinfo.istop eq 'yes'}">
            <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1" onclick="tzzdqx('${tzinfo.id}')">取消置顶</span>
            </c:if> 
            <c:if test="${tzinfo.isjh eq 'no'}">
            <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1" onclick="tzjj('${tzinfo.id}')">加精</span> 
            </c:if>
            <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
            <c:if test="${tzinfo.isjh eq 'yes'}">
          	<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1" onclick="tzjjqx('${tzinfo.id}')">取消加精</span> 
          	</c:if>
          	<!-- 
          	<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1" onclick="addfav('${tzinfo.id}')">收藏此帖</span> 
          	 -->
          	<span >&nbsp;</span>
          	</c:if>
          	<!-- 分享 -->
          	<c:if test="${sessionScope.member ne null}">
            <div style="float: right;">
            <div class="bshare-custom">
					<a title="分享到" href="http://www.bShare.cn/" id="bshare-shareto">分享到</a>
					<a title="分享到QQ空间" class="bshare-qzone"></a>
					<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
					<a title="分享到人人网" class="bshare-renren"></a>
					<a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
					</div>
					<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
					<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
            </div>
            </c:if>
            <!-- 分享 -->
          </div>
          <span class="fly-list-nums"> 
            <i class="iconfont" title="跟帖">&#xe60c;</i> ${fn:length(tzhtlist) }
            <i class="iconfont" title="人气">&#xe60b;</i> ${tzinfo.looknum }
          </span>
        </div>
        <div class="detail-about" style="margin-top: 20px;">
          <a class="fly-avatar" href="Home.do?memberid=${tzinfo.member.id}">
            <img src="<%=path %>/upload/${tzinfo.member.filename}" alt="${tzinfo.member.tname}">
          </a>
          <div class="fly-detail-user">
            <a href="Home.do?memberid=${tzinfo.member.id}" class="fly-link">
              <cite>${tzinfo.member.tname}</cite>
              <i class="iconfont icon-renzheng" title="实名认证"></i>
            </a>
            <span>${tzinfo.savetime }</span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
            <c:if test="${tzinfo.member.id eq sessionScope.member.id}">
            <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="tzinfoShow.do?id=${tzinfo.id }">编辑此贴</a></span>
            </c:if>
            <span  type="edit">&nbsp;</span>
          </div>
        </div>
        <div class="detail-body photos">
          ${tzinfo.note}
        </div>
      </div>

      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
        <c:choose>
          <c:when test="${tzinfo.canht eq 'yes'}">
            <legend>回帖</legend>
          </c:when>
          <c:otherwise>
            <legend>此帖已设置不能回帖</legend>
          </c:otherwise>
        </c:choose>
        </fieldset>
        
        
		<c:if test="${tzinfo.canht ne 'no'}">
        <ul class="jieda" id="jieda">
        
        <c:forEach items="${tzhtlist}" var="tzhtinfo">
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="Home.do?memberid=${tzhtinfo.htmember.id}">
                <img src="<%=path %>/upload/${tzhtinfo.htmember.filename}" alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="Home.do?memberid=${tzhtinfo.htmember.id}" class="fly-link">
                  <cite>${tzhtinfo.htmember.tname}</cite>
                  <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                </a>
                
                <span>${tzhtinfo.author eq tzinfo.id ?'(楼主)':''}</span>
                <!--
                <span style="color:#5FB878">(管理员)</span>
                <span style="color:#FF9E3F">（社区之光）</span>
                <span style="color:#999">（该号已被封）</span>
                -->
                <c:if test="${tzhtinfo.htmember.isjy eq 'yes'}">
                <span style="color:#999">（该号已被禁言）</span>
                </c:if>
              </div>

              <div class="detail-hits">
                <span>${tzhtinfo.savetime }&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </div>

              
            </div>
            <div class="detail-body jieda-body photos">
              <p>${tzhtinfo.note}</p>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan" onclick="dz('${tzhtinfo.id}')">
                <i class="iconfont icon-zan"></i>
                <em id="emid${tzhtinfo.id}">${tzhtinfo.dznum}</em>
              </span>
              <!--  <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>-->
              <div class="jieda-admin">
                <!--<span type="edit">编辑</span>-->
                <c:if test="${isbz eq 'isbz'}">
                <span type="del" onclick="delht('${tzinfo.id}','${tzhtinfo.id}')">删除</span>
                </c:if>
                <!-- <span class="jieda-accept" type="accept">采纳</span> -->
              </div>
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
			          <a href="tzDetail.do?index=1&id=${id}" style="font-family:微软雅黑;">首页</a>&nbsp;&nbsp;
			          
			          <c:choose>
			          <c:when test="${index >1}">
			          <a href="tzDetail.do?index=${index-1}&id=${id}" style="font-family:微软雅黑;">上一页</a>
			          </c:when>
			          <c:otherwise>
			          <a href="javascrip:void(0)" style="font-family:微软雅黑;">上一页</a>
			          </c:otherwise>
			          </c:choose>
			          &nbsp;&nbsp;
			          <c:choose>
			          <c:when test="${pages>index}">
			          <a href="tzDetail.do?index=${index+1}&id=${id}" style="font-family:微软雅黑;">下一页</a>
			          </c:when>
			          <c:otherwise>
			          <a href="javascrip:void(0)" style="font-family:微软雅黑;">下一页</a>
			          </c:otherwise>
			          </c:choose>
			          &nbsp;&nbsp;
			          <a href="tzDetail.do?index=${pages}&id=${id}" style="font-family:微软雅黑;">末页</a>
	        	
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
            	<input type="hidden" id="tzid" name="tzid" value="${tzinfo.id }">
              <button class="layui-btn" onclick="checksub()">提交回复</button>
            </div>
        </div>
        </c:if>
        
      </div>
    </div>
    <jsp:include page="right.jsp"></jsp:include>
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

function checksub(){
	var tzid = document.getElementById("tzid").value;
	var note = $('#note').val();
	location.replace("tzhtinfoAdd.do?tzid="+tzid+"&note="+note);
}

function dz(id){
					$.ajax({  
				        type: "POST",      
				        url: "tzhtDz.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	layer.msg('点赞+1');
						    document.getElementById("emid"+id).innerText=data;
				 		}     
					});
}

function del(id,ppid){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "tzdel.action", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	layer.closeAll();
						    location.href="tzlist.jsp?ppid="+ppid;
				 		}     
					});
}
function tzzd(id){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "tzzd.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){  
				        	layer.msg('置顶成功!');
						    location.href="tzDetail.do?id="+id;
				 		}     
					});
}

function tzjj(id){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "tzjj.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
						    location.href="tzDetail.do?id="+id;
				 		}     
					});
}

function tzzdqx(id){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "tzzdqx.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	layer.msg('取消置顶成功!');
						    location.href="tzDetail.do?id="+id;
				 		}     
					});
}

function tzjjqx(id){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "tzjjqx.do", //servlet的名字     
				        data: "id="+id, 
				        success: function(data){   
				        	layer.msg('取消加精成功!');
						    location.href="tzDetail.do?id="+id;
				 		}     
					});
}

function delht(id,htid){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "delHt.do", //servlet的名字     
				        data: "id="+htid, 
				        success: function(data){   
				        	layer.closeAll();
				        	layer.msg('删除成功!');
						    location.href="tzDetail.do?id="+id;
				 		}     
					});
}
function addfav(id,memberid){
					var index = layer.load(1, {
					  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
					$.ajax({  
				        type: "POST",      
				        url: "addfav.action", //servlet的名字     
				        data: "tzid="+id+"&memberid="+memberid, 
				        success: function(data){   
				        	layer.closeAll();
				        	if(data=='0'){
				        		layer.msg('收藏成功!');
				        	}else{
				        		layer.msg('此帖已经被你收藏过!');
				        	}
				        	
				 		}     
					});
}


function delTzinfo(id){
	$.ajax({  
        type: "POST",      
        url: "admin/tzinfoDel.do", //servlet的名字     
        data: "id="+id, 
        success: function(data){ 
        	layer.closeAll();  
        	alert("删除成功");
		    location.href="index.do";
 		}     
	});
}

</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

  </body>
</html>
