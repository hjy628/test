<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>tips</title>

<script language="javascript">
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
			pattern = "yyyy/MM/dd hh:mm:ss";
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
			pattern = "yyyy/MM/dd hh:mm:ss";
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

<style type="text/css">
* {
	padding: 0;
	margin: 0;
	font-weight: 150;
}
</style>
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

<div style="padding-left: 8px;padding-top: 10px; border-top-color: #CDCDCD; border-top-style: solid;border-top-width: thin;"><span>主题：</span> <span id="title" style="font-size: 120%;font-weight: 120% "></span></div>

<div style="padding-left: 8px;padding-top: 10px;padding-bottom:10px; border-bottom-color: #CDCDCD; border-bottom-style: solid;border-bottom-width: thin;"><span>医院：</span><span id="hosname" style="color: #4c4c4c"></span></div>

<div style="height: 50px; clear: both; border-bottom-color: #CDCDCD; border-bottom-style: solid;border-bottom-width: thin;">
<div style="width: 40%;float: left;padding-left: 8px;padding-top: 8px"><span>价格：</span><span id="sale" style="color:#ff008c; font-size: 120%;"></span><span>RMB</span> </div>
<div style="float: right; text-decoration: line-through; width: 40%;padding-right: 5%;margin-top:10px; height: auto;"><span>原价</span><span id="price"style="color:#ff008c; text-decoration: line-through"></span><span>RMB</span></div>
</div>
<div style="border-bottom-color: #CDCDCD; border-bottom-style: solid;border-bottom-width: thin; padding-top: 15px; padding-left:8px; padding-bottom: 15px;height: 30px "><span>活动时间：</span><span id="beginDate" style="color:#ff008c"></span>至<span id="endDate" style="color: #ff008c"></span></div>
<div style="border-bottom-color: #CDCDCD; border-bottom-style: solid;border-bottom-width: thin; padding-top: 15px; padding-left:8px; padding-bottom: 15px;height: 30px "><span>联系方式：</span><span id="mobile" style="color:#ff008c"></span></div>
<div style="border-bottom-color: #CDCDCD; border-bottom-style: solid;border-bottom-width: thin; padding-top: 15px; padding-left:8px; padding-bottom: 15px;height: 30px "><span>到院时间：</span><span id="comeDate" style="color:#ff008c"></span></div>


	<script type="text/javascript">
		(function() {
			$.post("./ajax//my/activity/submit/detail/" + getUrlParam("id") +"/" + getUrlParam("uid"), function(data) {
				if (data && data.result && data.result.code == 1) {
					var body = data.body;
						$("#title").html(body.title);
						$("#hosname").html(body.hosName);
						$("#sale").html(body.sale);
						$("#price").html(body.price);
						$("#beginDate").html(
								getSmpFormatDateByLong(body.beginDate, false));
						$("#endDate").html(
								getSmpFormatDateByLong(body.endDate, false));
						$("#comeDate").html(
								getSmpFormatDateByLong(body.come_date, false));
						$("#mobile").html(body.mobile_no);
						
						
					
				}
			})
		})();
	</script>

</body>

</html>