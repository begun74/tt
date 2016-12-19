<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | E-Shopper</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
	<link href="resources/css/simplePagination.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<!-- header_top -->
	<%@include file="common/header_top.jsp" %>
	<!-- header_top -->
		
	<section id="slider"><!--slider-->
		<div class="content">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER  </h1><h3>ver. ${version}</h3>
									<h2>Free E-Commerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="resources/images/home/girl1.jpg" class="girl img-responsive" alt="" />
									<img src="resources/images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER  </h1><h3>ver. ${version}</h3>
									<h2>100% Responsive Design</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="resources/images/home/girl2.jpg" class="girl img-responsive" alt="" />
									<img src="resources/images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER  </h1><h3>ver. ${version}</h3>
									<h2>Free Ecommerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="resources/images/home/girl3.jpg" class="girl img-responsive" alt="" />
									<img src="resources/images/home/pricing.png" class="pricing" alt="" />
								</div>
							</div>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<!-- product_filter -->
					<%@include file="common/product_filter.jsp" %>
					<!-- product_filter -->
				</div>
				<div class="col-sm-9 padding-right">
					<!-- content -->
					<%@include file="common/content2.jsp" %>
					<!-- content -->
				</div>
				
				
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->
	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/jquery.simplePagination.js"></script>
    <script src="resources/js/bootstrap-hover-dropdown.min.js"></script>
    
    <script>
	    $(function() {
				var pns = ${mA_search.pn};
				var pns_get ='';
				
				var gndrs = ${mA_search.gndr};
				var gndrs_get ='';
				
				var cats = ${mA_search.cat};
				var cats_get ='';
							
				for (var item in pns) {
	                $('#pn_'+pns[item]).attr("checked","checked");
	                pns_get += '&pn='+pns[item];
	            }
	
				for (var item in gndrs) {
	                $('#gndr_'+gndrs[item]).attr("checked","checked");
	                gndrs_get += '&gndr='+gndrs[item];
	            }

				for(var item in cats) {
					$('#cat_'+cats[item]).attr("checked","checked");
					cats_get += '&cat='+cats[item];
				}
				
		        $('#light-pagination').pagination({
		            items: ${allItems},
		            itemsOnPage: ${perPage},
		            cssStyle: 'light-theme',
		            prevText:"<<",
		            nextText:">>",
		            hrefTextPrefix: "?p=",
		            hrefTextSuffix: pns_get+gndrs_get+cats_get,
		            currentPage: ${p}
		        });
				
	    });
	    
    </script>
</body>
</html>