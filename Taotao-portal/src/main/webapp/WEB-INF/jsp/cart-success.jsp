<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" /> 
	<meta name="format-detection" content="telephone=no" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="//misc.360buyimg.com/jdf/1.0.0/unit/??ui-base/1.0.0/ui-base.css,shortcut/2.0.0/shortcut.css,global-header/1.0.0/global-header.css,myjd/2.0.0/myjd.css,nav/2.0.0/nav.css,shoppingcart/2.0.0/shoppingcart.css,global-footer/1.0.0/global-footer.css,service/1.0.0/service.css"/>
	<link rel="stylesheet" type="text/css" href="//misc.360buyimg.com/user/cart/widget/addtocart-album/addtocart-album.css"/>
    <link rel="stylesheet" href="/css/base.css">
    <link href="/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
	<title>商品已成功加入购物车</title>
	<script type="text/javascript">
		window.pageConfig={compatible:true};
	</script>

</head>
<body>
<script type="text/javascript">
	var dataLayer = null;

	//********过渡页切新版推荐-start***************
	function getParam(paramName) {
		var paramValue = "";
		isFound = false;
		if (this.location.search.indexOf("?") == 0
				&& this.location.search.indexOf("=") > 1) {
			arrSource = unescape(this.location.search).substring(1,
					this.location.search.length).split("&");
			i = 0;
			while (i < arrSource.length && !isFound) {
				if (arrSource[i].indexOf("=") > 0) {
					if (arrSource[i].split("=")[0].toLowerCase() == paramName
							.toLowerCase()) {
						paramValue = arrSource[i].split("=")[1];
						isFound = true;
					}
				}
				i++;
			}
		}
		return paramValue;
	}
	try{
		var nr = getParam("nr");
	    var heads = document.getElementsByTagName("head");
	    var link = document.createElement("link");
	    var href = nr?"//misc.360buyimg.com/user/cart/widget/??addtocart-201608/addtocart-201608.css":"//misc.360buyimg.com/user/cart/widget/??addtocart-2016/addtocart-2016.css?v=201609201019";
	    link.setAttribute("rel", "stylesheet");
	    link.setAttribute("type", "text/css");
	    link.setAttribute("href", href);
	  	heads[0].appendChild(link);
	}catch(e){}
	//********过渡页切新版推荐-end***************
</script>
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-T947SH" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>
(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-T947SH');
</script>
<jsp:include page="commons/header.jsp"/>
<div id="o-header-2013"><div id="header-2013" style="display:none;"></div></div>

<div class="main">
	<div class="success-wrap">
		<div class="w" id="result"><div class="m succeed-box"><div class="mc success-cont"><div class="success-lcol"><div class="success-top"><b class="succ-icon"></b><h3 class="ftx-02">商品已成功加入购物车！</h3></div></div><div class="success-btns success-btns-new"><div class="success-ad"><a href="#none"></a></div><div class="clr"></div><div><a class="btn-tobback" href="#" onclick="window.history.back();return false;" clstag="pageclick|keycount|201601152|3">继续购物</a><a class="btn-addtocart" href="/cart/cart.html" id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a></div></div></div></div></div>
	</div>
	<div class="w">
		<div class="m m1" id="similar"></div>
		<div class="m m1" id="promotion"></div>
		<div class="m m1" id="whatbuy"></div>
		<div class="m m1" id="ebook"></div>
	</div>
</div>
<div id="service-2014">
	<div class="slogen">
		<span class="item fore1">
			<i></i><b>多</b>品类齐全，轻松购物
		</span>
		<span class="item fore2">
			<i></i><b>快</b>多仓直发，极速配送
		</span>
		<span class="item fore3">
			<i></i><b>好</b>正品行货，精致服务
		</span>
		<span class="item fore4">
			<i></i><b>省</b>天天低价，畅选无忧
		</span>
	</div>
	<div class="w">
		<dl class="fore1">
			<dt>购物指南</dt>
			<dd>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-29.html">购物流程</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-151.html">会员介绍</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-297.html">生活旅行/团购</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue.html">常见问题</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-136.html">大家电</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/index.html">联系客服</a></div>
			</dd>
		</dl>
		<dl class="fore2">		
			<dt>配送方式</dt>
			<dd>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-81-100.html">上门自提</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-81.html">211限时达</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/103-983.html">配送服务查询</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/109-188.html">配送费收取标准</a></div>				
				<div><a target="_blank" href="//en.jd.com/chinese.html">海外配送</a></div>
			</dd>
		</dl>
		<dl class="fore3">
			<dt>支付方式</dt>
			<dd>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-172.html">货到付款</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-173.html">在线支付</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-176.html">分期付款</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-174.html">邮局汇款</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-175.html">公司转账</a></div>
			</dd>
		</dl>
		<dl class="fore4">		
			<dt>售后服务</dt>
			<dd>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/321-981.html">售后政策</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-132.html">价格保护</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/130-978.html">退款说明</a></div>
				<div><a rel="nofollow" target="_blank" href="//myjd.jd.com/repair/repairs.action">返修/退换货</a></div>
				<div><a rel="nofollow" target="_blank" href="//help.jd.com/user/issue/list-50.html">取消订单</a></div>
			</dd>
		</dl>
		<dl class="fore5">
			<dt>特色服务</dt>
			<dd>		
				<div><a target="_blank" href="//help.jd.com/user/issue/list-133.html">夺宝岛</a></div>
				<div><a target="_blank" href="//help.jd.com/user/issue/list-134.html">DIY装机</a></div>
				<div><a rel="nofollow" target="_blank" href="//fuwu.jd.com/">延保服务</a></div>
				<div><a rel="nofollow" target="_blank" href="//o.jd.com/market/index.action">淘淘E卡</a></div>				
				<div><a rel="nofollow" target="_blank" href="//mobile.jd.com/">淘淘通信</a></div>
				<div><a rel="nofollow" target="_blank" href="//s.jd.com/">淘淘JD+</a></div>
			</dd>
		</dl>
		<span class="clr"></span>
	</div>
</div>
<div class="w">
	<div id="footer-2014">
		<div class="links"><a rel="nofollow" target="_blank" href="//www.jd.com/intro/about.aspx">关于我们</a>|<a rel="nofollow" target="_blank" href="//www.jd.com/contact/">联系我们</a>|<a rel="nofollow" target="_blank" href="//help.jd.com/user/custom.html">联系客服</a>|<a rel="nofollow" target="_blank" href="//vc.jd.com/cooperation.html">合作招商</a>|<a rel="nofollow" target="_blank" href="//helpcenter.jd.com/venderportal/index.html">商家帮助</a>|<a rel="nofollow" target="_blank" href="//jzt.jd.com">营销中心</a>|<a rel="nofollow" target="_blank" href="//app.jd.com/">手机淘淘</a>|<a target="_blank" href="//club.jd.com/links.aspx">友情链接</a>|<a target="_blank" href="//media.jd.com/">销售联盟</a>|<a href="//club.jd.com/" target="_blank">淘淘社区</a>|<a href="//sale.jd.com/act/FTrWPesiDhXt5M6.html" target="_blank">风险监测</a>|<a href="//news.jd.com/30_1.html" target="_blank" clstag="h|keycount|2016|43">隐私政策</a>|<a href="//gongyi.jd.com" target="_blank">淘淘公益</a>|<a href="//en.jd.com/" target="_blank">English Site</a>|<a href="//en.jd.com/help/question-58.html" target="_blank">Contact Us</a></div>
		<div class="copyright"><a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11000002000088"><img src="//img13.360buyimg.com/cms/jfs/t2293/321/1377257360/19256/c267b386/56a0a994Nf1b662dc.png" /> 京公网安备 11000002000088号</a>&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证070359号&nbsp;&nbsp;|&nbsp;&nbsp;<a target="_blank" href="//img14.360buyimg.com/da/jfs/t256/349/769670066/270505/3b03e0bb/53f16c24N7c04d9e9.jpg">互联网药品信息服务资格证编号(京)-经营性-2014-0008</a>&nbsp;&nbsp;|&nbsp;&nbsp;新出发京零&nbsp;字第大120007号<br>互联网出版许可证编号新出网证(京)字150号&nbsp;&nbsp;|&nbsp;&nbsp;<a rel="nofollow" href="//sale.jd.com/act/pQua7zovWdJfcIn.html" target="_blank">出版物经营许可证</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="//misc.360buyimg.com/wz/wlwhjyxkz.jpg" target="_blank">网络文化经营许可证京网文[2014]2148-348号</a>&nbsp;&nbsp;|&nbsp;&nbsp;违法和不良信息举报电话：4006561155<br>Copyright&nbsp;&copy;&nbsp;2004-2017&nbsp;&nbsp;淘淘JD.com&nbsp;版权所有&nbsp;&nbsp;|&nbsp;&nbsp;消费者维权热线：4006067733&nbsp;&nbsp;&nbsp;&nbsp;<a class="mod_copyright_license" target="_blank" href="//sale.jd.com/act/7Y0Rp81MwQqc.html">经营证照</a><br>淘淘旗下网站：<a href="https://www.jdpay.com/" target="_blank">淘淘钱包</a>
		</div>		
		<div class="authentication">
			<a rel="nofollow" target="_blank" href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202007080200026">
				<img width="103" height="32" alt="经营性网站备案中心" src="//img12.360buyimg.com/da/jfs/t535/349/1185317137/2350/7fc5b9e4/54b8871eNa9a7067e.png" class="err-product" />
			</a>
			<script type="text/JavaScript">function CNNIC_change(eleId){var str= document.getElementById(eleId).href;var str1 =str.substring(0,(str.length-6));str1+=CNNIC_RndNum(6); document.getElementById(eleId).href=str1;}function CNNIC_RndNum(k){var rnd=""; for (var i=0;i < k;i++) rnd+=Math.floor(Math.random()*10); return rnd;}</script>
			<a rel="nofollow" target="_blank" id="urlknet" tabindex="-1" href="https://ss.knet.cn/verifyseal.dll?sn=2008070300100000031&ct=df&pa=294005">
				<img border="true" width="103" height="32" onclick="CNNIC_change('urlknet')" oncontextmenu="return false;" name="CNNIC_seal" alt="可信网站" src="//img11.360buyimg.com/da/jfs/t643/61/1174624553/2576/4037eb5f/54b8872dNe37a9860.png" class="err-product" />
			</a>
			<a rel="nofollow" target="_blank" href="http://www.bj.cyberpolice.cn/index.do">
				<img width="103" height="32" alt="网络警察" src="//img12.360buyimg.com/cms/jfs/t2050/256/1470027660/4336/2a2c74bd/56a89b8fNfbaade9a.jpg" class="err-product" />
			</a>
			<a rel="nofollow" target="_blank" href="https://search.szfw.org/cert/l/CX20120111001803001836">
				<img width="103" height="32" src="//img11.360buyimg.com/da/jfs/t451/173/1189513923/1992/ec69b14a/54b8875fNad1e0c4c.png" class="err-product" />
			</a>
			<a target="_blank" href="http://www.12377.cn"><img width="103" height="32" src="//img30.360buyimg.com/da/jfs/t1915/215/1329999964/2996/d7ff13f0/5698dc03N23f2e3b8.jpg"></a>
			<a target="_blank" href="http://www.12377.cn/node_548446.htm"><img width="103" height="32" src="//img14.360buyimg.com/da/jfs/t2026/221/2097811452/2816/8eb35b4b/5698dc16Nb2ab99df.jpg"></a>
		</div>
	</div>
</div>
</body>
</html>