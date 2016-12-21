<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<%@include file="common/header_top.jsp" %>

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="index"><spring:message code="home"/></a></li>
				  <li class="active"><spring:message code="cart"/></li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image"></td>
							<td class="description"><spring:message code="name2"/></td>
							<td class="quantity"><spring:message code="quantity"/></td>
							<!-- td class="total"><spring:message code="total"/></td -->
							<td></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order" varStatus="loop">
						<tr>
							<td class="cart_product">
								<a href="product-details?id=${order.dirNomenclature.id}"><img src="resources/images/products/${order.dirNomenclature.code}/S/${order.dirNomenclature.code}_S_0.jpg" alt="" onError="this.onerror=null;this.src='resources/images/products/blank.jpg';"></a>
							</td>
							<td class="cart_description">
								<h4>${order.dirNomenclature.name}</h4>
								<p><b><spring:message code="model"/></b>: ${order.dirNomenclature.model}</p>
								<p><b><spring:message code="size"/></b>: ${order.size}</p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_up" href="#"> + </a>
									<input class="cart_quantity_input" type="text" name="quantity" value="${order.amount}" autocomplete="off" size="2">
									<a class="cart_quantity_down" href="#"> - </a>
								</div>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="#"><i class="fa fa-times"></i></a>
							</td>
						</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
							<div class="heading">
								<h3><spring:message code="requisites.of.order"/></h3>
							</div>
							<form:form method="post" modelAttribute ="requestForm" id="requestForm"  
								action="createOrder">
										<div class="input-group">
											<label>Имя <span>*</span></label>
										</div>
										<div class="input-group">
											<input type="text" name="name" value="${requestForm.name}"/>
										</div>
										<div>
											 <font color="red"><form:errors path="name" cssClass="formError"/></font>
										</div>
										<div class="input-group">
											<label>Телефон <span>*</span></label>
										</div>
										<div class="input-group">
											<input type="text" name="phone" value="${requestForm.phone}" placeholder="+375 XX 123-12-12"/>
										</div>
										<div>
											 <font color="red"><form:errors path="phone" cssClass="formError"/></font>
										</div>
										<div class="input-group">
											<label>Эл. почта</label>
										</div>
										<div class="input-group">
											<input type="text" name="email" value="${requestForm.email}"/>
										</div>
										
										<div class="input-group">
											<label>Комментарии</label>
										</div>
										<div class="input-group">
											<textarea name="description" id="description"   class="form-control" maxlength = "45"
								                   rows="2" cols="20">${requestForm.description}</textarea>
										</div>

										<div class="col-sm-12 text-center">
											<input  type="submit" name="toOrder" class="submit_order" value="<spring:message code="label.button.toOrder"/>"/>
										</div>
			
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form:form>
					</div>
					<div class="col-sm-3"></div>
				</div>
				
			</div>
		</div>
	</section><!--/#do_action-->

	<footer id="footer"><!--Footer-->
		<!-- footer -->
		<%@include file="common/footer.jsp" %>
		<!-- footer -->
	</footer><!--/Footer-->
	


    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    
    <script>
    $(document).ready(function(){
    	
    	if('${fn:length(sessionScope.sessBean.orders) == 0}')
    	{
    		$(".submit_order").attr("disabled","disabled");
    	}
    	else
    	{
    		$(".submit_order").attr("disabled","");
    	}
    	
    	
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
    	
    	$(".submit_order").click(function(id) {
    		Product.delOrder(id);
    	});

    	
    });
    
    </script>
</body>
</html>