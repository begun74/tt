<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ">
    <meta name="keywords" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ" />
    <meta name="author" content="">
    <title>ОАО «Трикотажторг»</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <!-- link href="resources/css/animate.css" rel="stylesheet" -->
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
    
    
</head><!--/head-->

<body>
	<%@include file="common/header_top.jsp" %>
	
	<section>
		<div class="container">
		
			<div id="bs-example-modal-sm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="">
			  <div class="modal-dialog modal-sm" role="document">
			    <div class="modal-content">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
			    	<div class="modal-body text-center">
			      		<h2><spring:message code="add.to.backet"/>. <a href="cart"><spring:message code="cart"/></h2></a>
      				</div>
      				<div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
				    </div>
			    </div>
			  </div>
			</div>
			
			<div class="row">
				
							
				
				<div class="col-sm-12 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
						<br/>
							<div>
								<img  id="img_01" src="/pics/products/${nomenclature.code}/M/${nomenclature.code}_M_0.jpg" data-zoom-image="/pics/products/${nomenclature.code}/L/${nomenclature.code}_L_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture.jpg';" />
							</div>


							<div id="similar-product" class="carousel slide" data-ride="carousel">
							    
								
								<div id="gal1">
									<div class="carousel-inner">							
										<div class="item active">
											<a href="#" data-image="/pics/products/${nomenclature.code}/M/${nomenclature.code}_M_0.jpg" data-zoom-image="/pics/products/${nomenclature.code}/L/${nomenclature.code}_L_0.jpg">
											   		<img id="img_01" width="30%" height="30%" src="/pics/products/${nomenclature.code}/S/${nomenclature.code}_S_0.jpg" onError="this.onerror=null;this.src='resources/images/products/blank.jpg'; //this.style.display='none'"/>
											</a>
											<a href="#" data-image="/pics/products/${nomenclature.code}/M/${nomenclature.code}_M_1.jpg" data-zoom-image="/pics/products/${nomenclature.code}/L/${nomenclature.code}_L_1.jpg">
										   		<img id="img_01" width="30%" height="30%" src="resources/images/products/${nomenclature.code}/S/${nomenclature.code}_S_1.jpg" onError="this.onerror=null;this.src='resources/images/products/blank.jpg'; //this.style.display='none'"/>
											</a>
										</div>
										<div class="item">
											<a href="#" data-image="/pics/products/${nomenclature.code}/M/${nomenclature.code}_M_2.jpg" data-zoom-image="/pics/products/${nomenclature.code}/L/${nomenclature.code}_L_2.jpg">
										   		<img id="img_01" width="30%" height="30%" src="resources/images/products/${nomenclature.code}/S/${nomenclature.code}_S_2.jpg" onError="this.onerror=null;this.src='resources/images/products/blank.jpg';"/>
											</a>
											<a href="#" data-image="/pics/products/${nomenclature.code}/M/${nomenclature.code}_M_3.jpg" data-zoom-image="/pics/products/${nomenclature.code}/L/${nomenclature.code}_L_3.jpg">
										   		<img id="img_01" width="30%" height="30%" src="resources/images/products/${nomenclature.code}/S/${nomenclature.code}_S_3.jpg" onError="this.onerror=null;this.src='resources/images/products/blank.jpg';"/>
											</a>
											
										</div>
										
									</div>
								
								</div>
								  <!-- Wrapper for slides>
								    <div class="carousel-inner">
										<div class="item active">
										  <a href=""><img src="resources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										<div class="item">
										  <a href=""><img src="resources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										<div class="item">
										  <a href=""><img src="resources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="resources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										
									</div -->
									

								  <!-- Controls -->
								  <a class="left item-control" href="#similar-product" data-slide="prev">
									<i class="fa fa-angle-left"></i>
								  </a>
								  <a class="right item-control" href="#similar-product" data-slide="next">
									<i class="fa fa-angle-right"></i>
								  </a>
							</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<img src="resources/images/product-details/new.jpg" class="newarrival" alt="" />
								<h2>${nomenclature.name } (${nomenclature.dirGender.name })</h2>
								<p><h2><spring:message code="price"/>: ${price}<c:if test="${empty price}"><spring:message code="price.isShow"/></c:if></h2></p>
								<p><b><spring:message code="model"/></b>: ${nomenclature.model}</p>
								<p><b><spring:message code="article"/>:  </b>${nomenclature.article} </p>
								<p>ID: ${nomenclature.code}</p>
								<img src="resources/images/product-details/rating.png" alt="" />
								<!-- p><b>Availability:</b> In Stock</p -->
								<!-- p><b>Condition:</b> New</p -->
								<p><b><spring:message code="brand.name"/>:  </b>${provider.name} </p>
								<p><b><spring:message code="size"/>:  </b></p>
								
								<div class="size_info"></div>
								
								
								<!-- a href=""><img src="resources/images/product-details/share.png" class="share img-responsive"  alt="" /></a -->
								
								
									<span><span><!-- BYN ${price} --></span></span>
									<select name="sizes" class="sizes">
									</select>

									
									<label><spring:message code="quantity"/>:
										<div class="cart_quantity_button">
											<a class="cart_quantity_up" maxval="1" href="#"> + </a>
											<input class="cart_quantity_input" type="text" name="quantity" value="1"  autocomplete="off" size="2">
											<a class="cart_quantity_down" href="#"> - </a>
										</div>
									</label>

									<div class="text-center">
										<button type="button" class="btn btn-default cart " href="#" data-toggle="modal" data-target=".bs-example-modal-sm">
											<i class="fa fa-shopping-cart"></i>
											<spring:message code="to.order"/>
										</button>
									</div><!--/product-details-->

							</div><!--/product-information-->
						</div>
					
					<div class="col-sm-12 category-tab shop-details-tab"><!--category-tab-->
							<div class="col-sm-12">
								<ul class="nav nav-tabs">
									<!-- li><a href="#details" data-toggle="tab">Details</a></li>
									<li><a href="#companyprofile" data-toggle="tab">Company Profile</a></li>
									<li><a href="#tag" data-toggle="tab">Tag</a></li -->
									<li class="active"><a href="#reviews" data-toggle="tab"><spring:message code="reviews"/></a></li>
								</ul>
							</div>
							
							<div class="tab-pane fade active in" id="reviews" >
								<div class="col-sm-12">
									<ul>
										<li><a href=""><i class="fa fa-user"></i>EUGEN</a></li>
										<li><a href=""><i class="fa fa-clock-o"></i>12:41</a></li>
										<li><a href=""><i class="fa fa-calendar-o"></i>31.12.2016</a></li>
									</ul>
									<p></p>
									<p><b><spring:message code="write.your.review"/></b></p>
									
									<form action="#">
										<span>
											<input type="text" placeholder='<spring:message code="name"/>'/>
											<input type="email" placeholder='<spring:message code="email"/>'/>
										</span>
										<textarea name="" ></textarea>
										<!-- b>Rating: </b> <img src="resources/images/product-details/rating.png" alt="" / -->
										<button type="button" class="btn btn-default pull-right">
											<spring:message code="submit"/>
										</button>
									</form>
								</div>
							</div>
							
						</div>
					</div><!--/category-tab-->
					
					<!--recommended_items-->
					<!-- div class="recommended_items">
						<h2 class="title text-center">recommended items</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">	
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend1.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend2.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend3.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">	
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend1.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend2.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/recommend3.jpg" alt="" />
													<h2>$56</h2>
													<p>Easy Polo Black Edition</p>
													<button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div --><!--/recommended_items-->
					
				</div>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->

	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/jquery.elevatezoom.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/app.js"></script>
    
    <script>
    $('#img_01').elevateZoom({
    	
    		easing: true,
      		gallery:'gal1', 
      		scrollZoom : true,
      		cursor: 'pointer', 
      		galleryActiveClass: 'active', 
      		imageCrossfade: true,
      		zoomWindowWidth:500,
            zoomWindowHeight:700,
            zoomWindowFadeIn: 300,
			zoomWindowFadeOut: 450
      		
      		
      		/*      	
      		cursor: "crosshair",
      		scrollZoom : true,
      		zoomWindowPosition: 1,
      		easing: true,
      		zoomWindowFadeIn: 500,
			zoomWindowFadeOut: 750
      		*/
		 /*
      		zoomType: "inner",
			cursor: "crosshair",
			zoomWindowFadeIn: 500,
			zoomWindowFadeOut: 750
			*/
		}); 
    
    $("#img_01").bind("click", function(e) {  
    	  	var ez =   $('#img_01').data('elevateZoom');
    	  	$.fancybox(ez.getGalleryList());
    	  return false;
    });
    
    $(document).ready(function(){
		

		Product.id = ${nomenclature.id};
    	Product.getDetail();
    	Product.localItemName = '<spring:message code="items"/>';

    	$(".cart_quantity_up").click(function () {
			var val = $(".cart_quantity_input").val();
			//if(val < $(".cart_quantity_up").attr("maxval"))
				$(".cart_quantity_input").val(++val);		
		});

		$(".cart_quantity_down").click(function () {
			var val = $(".cart_quantity_input").val();

			if(val > 1)
				$(".cart_quantity_input").val(--val);
		});
		
		$( ".sizes" ).change(function() {
			  //$(".cart_quantity_input").val(1);
			  //$(".cart_quantity_up").attr("maxval",$( ".sizes" ).val());
		});
		
    	$(".cart_quantity_input").keypress(function(event) {
    		return isNumberKey(event);
    	});

    	$(".cart").click(function() {
    		var res = Product.toOrder($( ".sizes option:selected" ).text(),$( ".cart_quantity_input" ).val());
			
    		$('.badge').text(res); //в header_top.jsp class='badge' Корзина - кол-во покупок
    		//alert('res -  ' + res);
    	});

    	//setInterval(Product.getDetail,15000);
    });
    

    </script>
    
</body>
</html>