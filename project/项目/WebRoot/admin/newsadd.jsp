<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="/gymshoesbbs/layer/layer.js"></script>

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

    <script charset="utf-8" src="/gymshoesbbs/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/plugins/code/prettify.js"></script>
	
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/gymshoesbbs/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="/gymshoesbbs/kindeditor/jwplayer.js"></script>
	<script type='text/javascript'>
	$(document).ready(function(e) {  
		//非视频，不加载播放器  
		 if(document.getElementById('player') != null) {
			jwplayer('player').onReady(function() {});  
			jwplayer('player').onPlay(function() {});
			jwplayer("player").play(); 
		} 
	});
	</script>
	
	<script>
KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
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
  
  <body class="">
	<jsp:include page="/admin/top.jsp"></jsp:include>
    
    <jsp:include page="/admin/left.jsp"></jsp:include>
    

    
       <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">站内资讯新增</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="admin/index.jsp">Home</a> <span class="divider">/</span></li>
            <li class="active">站内资讯新增</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">

<div class="well">
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
		    <form id="form" action="<%=path %>/admin/newsAdd.do" method="post">
		        <label>标题</label>
		        <input type="text" id="title" name="title" class="input-xlarge" oninvalid="setCustomValidity('标题不能为空')" oninput="setCustomValidity('');">
		        <label>图片</label>
		        <input name='filename' type='text' class="input-xlarge" id='url'  />
		        <label></label>
		        <input type='button' value='上传' onClick="up('url')" style="width: 60px;height: 30px;"/>
		        <label></label>
		        <label></label>
		        <label>内容</label>
		        <textarea id="content" name="content" style="width:1000px;height:400px;" ></textarea>
		        <button class="btn btn-primary"><i class="icon-save"></i> Save</button>
		    </form>
      </div>
  </div>

</div>

<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Delete Confirmation</h3>
  </div>
  <div class="modal-body">
    
    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>Are you sure you want to delete the user?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
    <button class="btn btn-danger" data-dismiss="modal">Delete</button>
  </div>
</div>
            </div>
        </div>
    </div>

    <script src="/gymshoesbbs/admin/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });


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
  </body>
</html>
