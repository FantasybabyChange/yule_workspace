<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
   	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="dns-prefetch" href="//static.yuleing.com">
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <title>用户评论</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
</head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix mt-10">
        <div class="user-center-left">
        	<%@ include file="/common/menu.jsp" %>
        </div>
        <div class="user-center-right">
            <h2 class="user-center-title">我的评论</h2>
            <input type="hidden" name="order_num" data-type="" value="${orders.order_num }"/>
            <input type="hidden" name="company_id" data-type="" value="${orders.company_id }"/>
            <div class="caption">我要评论</div>
            <div class="user-comment">
                <div class="user-comment-title">
                    <img src="${image_path}" alt="" class="pull-left" />
                    <h2>${orders.company_name}</h2>
                    <p>
                        <span>${orders.product_name}</span>消费时间：${create_time}至${arrive_time}</p>
                </div>
                <p class="user-comment-tips">点评成功后即可获得
                    <span>500积分</span>
                </p>
                <table cellpadding="0" cellspacing="0" border="0" class="user-comment-table">
                    <tbody>
                        <tr>
                            <td class="user-comment-type">总体评价</td>
                            <td class="user-comment-content user-comment-content-bg">
                                <div class="user-raty" id="user_point_comment" style="height:1%;">
                                		正在加载。。请稍后。。
                                </div>
                               <!--   <div class="user-totle-comment">
                                    <h2>总评</h2>
                                    <div class="uset-totle-c-item">
                                        <span class="t active">值得推荐</span>
                                    </div>
                                    <div class="uset-totle-c-item">
                                        <span class="d">有待改善</span>
                                    </div>
                                </div>
                                -->
                            </td>
                        </tr>
                        <tr>
                            <td class="user-comment-type">点评内容</td>
                            <td class="user-comment-content">
                                <textarea name="comment" validword="" data-type="" null-message="请输入点评内容" class="comment-textarea"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td class="user-comment-type">点评建议</td>
                            <td class="user-comment-content">
                                <textarea name="advice" validword="" data-type="" null-message="请输入点评建议"class="comment-textarea"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td id="commentType"class="user-comment-type">聚会类型</td>
                            <td class="user-comment-content">
                                <span class="user-t-type active" data-name="commentType" data-value='0'>朋友聚会</span>
                                <span class="user-t-type"  data-name="commentType" data-value='1'>商务聚会</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="comment-submit" data-submit=""><a class="b-button w165 text-center" href="#"> 提交</a></div>
            </div>
        </div>
    </div>
    <div class="footer">
        <%@ include file="/common/footer.jsp" %>
    </div>
    <div class="footer-other">
        <%@ include file="/common/footer-other.jsp" %>
    </div>
    <div class="right-sider-bar w1000">
    	<%@ include file="/common/right-sider-bar.jsp" %>
    </div>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/slides.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/ajax.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/modal.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/panes.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/tab.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/imagePreview.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/jquery.raty.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/collapse.js"></script>
    <script src="http://static.yuleing.com/www/js/slides.min.jquery.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript">
    $(function() {
    	$('.user-icon-2').addClass('cur');
        $('.detailAblum').slides({
            effect: 'slide, fade',
            crossfade: true,
            slideSpeed: 350,
            fadeSpeed: 500,
            generateNextPrev: true,
            generatePagination: false
        });
       
        $('#commentType').parent().find('.user-t-type').click(function(){
        	$(this).parent().find('.user-t-type').each(function(){
        		$(this).removeClass('active');
        	});
        	$(this).addClass('active');
        });
    	$("textarea[validword]").on('blur',function(){
    		var parent = $(this).parent();
			parent.find("div[data-error]").remove();
			var val = $.trim($(this).val());
			if(val===""||val===undefined||val==null){
				return true;
			}else{
				$.ajax({
					url:'/word/doFilter.do',
					type:'post',
					data:{'word':$(this).val()},
					dataType:'json',
					async:false,
					success:function(data){
						if(!data.status){
							parent.append("<div data-error='' style='color:red'>敏感词"+decodeURIComponent(data.word)+"!</div>");
						}
					}
				});
			}
		});
        $.ajax({
			type: "POST",
			url: '/comment/findCommentCategorys.do',
			dataType:"json",
			async:false,
			success: function(data){
				if(data != null){
					$('#user_point_comment').html(data.commentCategoryHTML);
					 $('.raty-star').raty({
				            target     : '.hint',
				             targetKeep : true,
				             noRatedMsg   : '0/尚无评论',
				             number:data.poinJSON.length,
				             hints:data.poinJSON,
				             score:'',
				             scoreName:'companyCommentPoint',
				             click:function(){
				            	$(this).find('span[data-error]').remove();
				             }
				        });
				}
			}
		});
        $('div[data-submit]').click(function(){
        	var jsonData = {};
        	var flag = true;
        	$('input[data-type]').each(function(){
        		jsonData[$(this).attr('name')] =$(this).val(); 
        	});
        	$('textarea[data-type]').each(function(){
        		var parent = $(this).parent();
        		$(this).parent().find('div[data-error]').remove();
        		var value = $.trim($(this).val());
					if( value == '' ){
						$(this).parent().append("<div data-error='' style='color:red'>请输入"+$(this).attr('null-message')+"</div>");
						flag = false;
					}else{
						$.ajax({
							url:'/word/doFilter.do',
							type:'post',
							data:{'word':value},
							dataType:'json',
							async:false,
							success:function(data){
								if(!data.status){
									flag = false;
									parent.append(("<div data-error='' style='color:red'>敏感词"+decodeURIComponent(data.word)+"!</div>"));
								}
							}
						});
						if(flag){
							jsonData[$(this).attr('name')] =$(this).val();	
						}else{
							return false;
						}
					}
        	});
        	var commentType = $('#commentType').parent().find('.active');
        	jsonData[commentType.attr('data-name')] =commentType.attr('data-value');
        	var i = 0;
        	$('div[data-type="categoryDiv"]').each(function(){
        		jsonData[$(this).attr('data-name')+"["+i+"]"] =$(this).attr('data-value');
        		jsonData["commentCategory_name"+"["+i+"]"] =$(this).attr('categoryName');
        		var companyCommentPointInput = $(this).find('input[name="companyCommentPoint"]');
        		var pointVal = companyCommentPointInput.val();
        		companyCommentPointInput.parent().find('span[data-error]').remove();
        		if($.trim(pointVal) == ''){
        			flag = false;
        			companyCommentPointInput.parent().append("<span data-error='' style='color:red;'>请打分</span>");
        			return;
        			//pointVal = 0;
        		}else{
        			pointVal = parseInt(pointVal) + 1;
        		}
        		jsonData["companyPointCategory_point"+"["+i+"]"] =pointVal;
        		i++;
        	});
        	if(flag){
        		$.ajax({
        			type: "POST",
        			url: '/comment/insertCommentPoints.do',
        			dataType:"json",
        			data:jsonData,
        			async:false,
        			success: function(data){
        				if(data != null && data.status){
        					alert('评论成功');
        					location.href="/orders.do";
        				}else{
        					alert('评论失败');
        				}
        			}
        		});
        	}
        });
        
    });
    </script>
</body>
</html>