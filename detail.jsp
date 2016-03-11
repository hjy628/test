<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<script>
	var imgUrl = ""; //'http://topic.xcar.com.cn/201403/ad_q3/pic/banner.jpg';  
	var lineLink = location.href; //'http://topic.xcar.com.cn/201403/ad_q3/index.php';  
	var descContent = ""; //"http://topic.xcar.com.cn/201403/ad_q3/index.php";  
	var shareTitle = ""; //'【奥迪Q3开启尊享礼遇季】报名试驾，赢取精美礼品';  
	var appid = 'wxb34935e30063c8f1';

	function shareFriend() {
		WeixinJSBridge.invoke('sendAppMessage', {
			"appid" : appid,
			"img_url" : imgUrl,
			"img_width" : "640",
			"img_height" : "640",
			"link" : lineLink,
			"desc" : descContent,
			"title" : shareTitle
		}, function(res) {
			_report('send_msg', res.err_msg);
		})
	}
	function shareTimeline() {
		WeixinJSBridge.invoke('shareTimeline', {
			"img_url" : imgUrl,
			"img_width" : "640",
			"img_height" : "640",
			"link" : lineLink,
			"desc" : descContent,
			"title" : shareTitle
		}, function(res) {
			_report('timeline', res.err_msg);
		});
	}
	function shareWeibo() {
		WeixinJSBridge.invoke('shareWeibo', {
			"content" : descContent,
			"url" : lineLink,
		}, function(res) {
			_report('weibo', res.err_msg);
		});
	}
	// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。  
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {

		// 发送给好友  
		WeixinJSBridge.on('menu:share:appmessage', function(argv) {
			shareFriend();
		});

		// 分享到朋友圈  
		WeixinJSBridge.on('menu:share:timeline', function(argv) {
			shareTimeline();
		});

		// 分享到微博  
		WeixinJSBridge.on('menu:share:weibo', function(argv) {
			shareWeibo();
		});
	}, false);
</script>


<style type="text/css">
#price {
	position: relative;
	overflow: auto;
	margin: 0;
	padding: 0;
	font-weight: 150%;
	width: 100%;
}

#header {
	position: fixed;
}

#detail {
	position: relative;
	overflow: auto;
	margin: 0;
	padding: 0;
	width: 100%;
	font-weight: 150%;
	background-color: #F8F8F8;
}

.thetitle {
	padding-right: 3%;
	padding-left: 3%;
}

* {
	padding: 0;
	margin: 0;
	font-weight: 120%;
	border-left-width: 0px;
	border-right-width: 0px;
}

.banner {
	position: relative;
	overflow: hidden;
	background-color: #fff;
}

.banner li, .banner ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

.banner ul {
	float: left;
}

.banner ul li {
	display: block;
	float: left;
	box-shadow: inset 0 -3px 6px rgba(0, 0, 0, .1);
}

img, object {
	max-width: 100%;
}
</style>
<script type="text/javascript">
	//扩展Date的format方法 
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}

		}
		return format;
	}
	/** 
	 *转换日期对象为日期字符串 
	 * @param date 日期对象 
	 * @param isFull 是否为完整的日期数据, 
	 * 为true时, 格式如"2000-03-05 01:05:04" 
	 * 为false时, 格式如 "2000-03-05" 
	 * @return 符合要求的日期字符串 
	 */
	function getSmpFormatDate(date, isFull) {
		var pattern = "";
		if (isFull == true || isFull == undefined) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		} else {
			pattern = "yyyy/MM/dd";
		}
		return getFormatDate(date, pattern);
	}
	/** 
	 *转换当前日期对象为日期字符串 
	 * @param date 日期对象 
	 * @param isFull 是否为完整的日期数据, 
	 * 为true时, 格式如"2000-03-05 01:05:04" 
	 * 为false时, 格式如 "2000-03-05" 
	 * @return 符合要求的日期字符串 
	 */
	function getSmpFormatNowDate(isFull) {
		return getSmpFormatDate(new Date(), isFull);
	}
	/** 
	 *转换long值为日期字符串 
	 * @param l long值 
	 * @param isFull 是否为完整的日期数据, 
	 * 为true时, 格式如"2000-03-05 01:05:04" 
	 * 为false时, 格式如 "2000-03-05" 
	 * @return 符合要求的日期字符串 
	 */
	function getSmpFormatDateByLong(l, isFull) {
		return getSmpFormatDate(new Date(l), isFull);
	}
	/** 
	 *转换long值为日期字符串 
	 * @param l long值 
	 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
	 * @return 符合要求的日期字符串 
	 */
	function getFormatDateByLong(l, pattern) {
		return getFormatDate(new Date(l), pattern);
	}
	/** 
	 *转换日期对象为日期字符串 
	 * @param l long值 
	 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
	 * @return 符合要求的日期字符串 
	 */
	function getFormatDate(date, pattern) {
		if (date == undefined) {
			date = new Date();
		}
		if (pattern == undefined) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		}
		return date.format(pattern);
	}
	//alert(getSmpFormatDate(new Date(1279849429000), true)); 
	//alert(getSmpFormatDate(new Date(1279849429000),false)); 
	//alert(getSmpFormatDateByLong(1279829423000, true)); 
	//alert(getSmpFormatDateByLong(1279829423000,false)); 
	//alert(getFormatDateByLong(1279829423000, "yyyy-MM")); 
	//alert(getFormatDate(new Date(1279829423000), "yy-MM")); 
	//alert(getFormatDateByLong(1279849429000, "yyyy-MM hh:mm"));
</script>


<script type="text/javascript">
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
</script>


<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/jQuery.fontFlex.js" type="text/javascript"></script>
<script type="text/javascript" src="js/iscroll.js"></script>

<script type="text/javascript">
	//字体自适应大小
	$(function() {
		// All elements
		$('body').fontFlex(10, 30, 20);
		//div
		$('div').fontFlex(15, 30, 20);
	});
</script>

</head>
<body>
	<div class="banner" id="myBanner"></div>
	<div
		style="height: 40px; overflow: hidden; line-height: 40px; padding-top: 10px; padding-bottom: 10px">
		<span id="sale"
			style="padding-left: 5%; padding-right: 5px; color: #ff008c; font-size: 35px; font-family: Helvetica LT Std"></span><span
			style="color: #c4c4c4">RMB</span><span
			style="text-decoration: line-through; padding-left: 50px">原价</span><span
			id="price"
			style="color: #ff008c; padding-left: 5px; padding-right: 5px; text-decoration: line-through"></span><span
			style="text-decoration: line-through;">RMB</span>
	</div>

	<div id="detail">

		<div
			style="clear: both; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; overflow: auto; word-break: break-all;">
			<div
				style="height: 40px; float: left; width: 50px; padding-top: 20px; font-weight: bolder;"
				align="center">主题</div>
			<div id="title"
				style="margin-left: 62px; vertical-align: middle; padding-top: 20px; font-family: STHeiti Light [ STXihei ];"></div>
		</div>
		<div
			style="height: 60px; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; vertical-align: middle; line-height: 60px; word-break: break-all;">
			<div
				style="float: left; margin-left: 0; padding-left: 0; width: 50px; font-weight: bolder;"
				align="center">医院</div>
			<div id="hosname"
				style="vertical-align: middle; margin-left: 62px; font-family: STHeiti Light [ STXihei ]"></div>
		</div>
		<div
			style="height: 60px; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; line-height: 60px; word-break: break-all;">
			<div style="float: left; width: 50px; font-weight: bolder;"
				align="center">医生</div>
			<div id="docnames"
				style="vertical-align: middle; margin-left: 62px; font-family: STHeiti Light [ STXihei ]"></div>
		</div>
		<div
			style="height: 60px; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; line-height: 60px">
			<div style="float: left; width: 50px; font-weight: bolder;"
				align="center">截止</div>
			<div style="vertical-align: middle; margin-left: 62px">
				<span id="begindate" style="color: #ff008c"></span><span
					style="color: #ff008c">-</span><span id="enddate"
					style="color: #ff008c"></span>
			</div>
		</div>
		<div
			style="height: 60px; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; line-height: 60px">
			<div style="float: left; width: 50px; font-weight: bolder;"
				align="center">名额</div>
			<div
				style="vertical-align: middle; margin-left: 62px; font-family: STHeiti Light [ STXihei ]">
				<span id="quota"></span><span
					style="font-family: STHeiti Light [ STXihei ]">人，已报名</span><span
					id="hasquota"></span><span
					style="font-family: STHeiti Light [ STXihei ]">人</span>
			</div>
		</div>
		<div
			style="clear: both; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin; background-color: #FFFFFF">
			<div
				style="float: left; width: 60px; vertical-align: top; clear: both; margin-top: 20px; font-weight: bolder;"
				align="center">详情</div>
			<div id="content"
				style="margin-top: 20px; margin-left: 62px; font-family: STHeiti Light [ STXihei ];overflow: auto;word-wrap: break-word;"></div>
		</div>
	</div>

	<div
		style="width: auto; height: 30px; background-color: #F8F8F8; border-top-color: #CDCDCD; border-top-style: solid; border-top-width: thin;">
		<span style="padding-left: 7.5%; padding-top: 15px;">评价</span>
	</div>
	<div id="reviewlist" style="background-color: #F8F8F8"></div>

	<script type="text/javascript">
		(function() {
			$
					.post(
							"./ajax/events/detail/" + getUrlParam("id"),
							function(data) {
								if (data && data.result
										&& data.result.code == 1) {
									var body = data.body;
									var _data = body.data;
									var ul = $("<ul/>");
									$.each(_data.images, function(i, val) {
										ul.append($("<li/>").append(
												$("<img>").attr(
														"src",
														"http://img.imiaofu.com"
																+ val)));
									});
									ul.css("width", (_data.images.length * 100)
											+ "%");
									ul.find("li").css("width",
											(100 / _data.images.length) + "%");
									var _weight = $(document).width();
									ul.find("li").css("height",
											_weight * 0.4516 + "px");
									$(".banner").append(ul);
									imgUrl = "http://img.imiaofu.com" + _data.images[0];
									$("#sale").html(_data.sale);
									$("#price").html(_data.price);
									$("#title").html(_data.title);
									shareTitle = _data.title
									$("#hosname").html(_data.hosName);
									$("#docnames").html(_data.docNames);
									$("#begindate").html(
											getSmpFormatDateByLong(
													_data.beginDate, false));
									$("#enddate").html(
											getSmpFormatDateByLong(
													_data.endDate, false));
									$("#quota").html(_data.quota);
									$("#hasquota").html(_data.hasQuota);
									$("#hasquotatop").html(_data.hasQuota);
									$("#content").html(_data.content);
									
									if (_data.content.length < 61) {
										descContent = _data.content
									} else {
										descContent = _data.content.substring(0, 60);
									}
									$("#content img").removeAttr("style").css({
										"width" : "90%",
										"margin" : "0px auto",
										"display" : "block"
									});
									$(document).attr("title", _data.title);
									var review = body.review;
									$.each(review,function(i, val) {var item = $("<div/>");
														item.attr("style","width:100%;margin: 0;padding: 0; clear:both; border-top-color: #CDCDCD; border-top-style: solid;border-top-width: thin; ")
														item.append($("<div/>").append($("<img>").attr("src","http://img.imiaofu.com"+ val.user_avatar).attr("style","width:40px;height:40px; float: left; border-radius:20px;  -webkit-border-radius: 50%;border:thin solid #555555; margin-top:8px; margin-bottom:8px")))
																.append($("<div/>").attr("style","margin-left:50px").append($("<div/>").attr("style","padding-right:10px; padding-top: 10px; ")
																.append($("<span/>").html(val.user_nick)).append($("<span/>").html(getSmpFormatDateByLong(val.datetime,false)).attr("style","float:right;")))
																				.append($("<div/>").html(val.content).attr("style","display:block;color:#4c4c4c; width:100%;word-break:break-all;")));
														$("#reviewlist").append(item);
													});

								}

								myScroll = new IScroll('#myBanner', {
									scrollX : true,
									scrollY : false,
									momentum : false,
									snap : true,
									snapSpeed : 400,
									keyBindings : true
								});

							});
		})();
	</script>
</body>
</html>