<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <title>ОАО «Трикотажторг»</title>
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
			
			<div id="bs-example-modal-sm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="">
			  <div class="modal-dialog modal-sm" role="document">
			    <div class="modal-content">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
			    	<div class="modal-body text-center">
			      		<h2><a href="cart"><spring:message code="add.to.order"/></h2></a>
      				</div>
      				<div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
				    </div>
			    </div>
			  </div>
			</div>
		
			<!-- div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="index"><spring:message code="home"/></a></li>
				  <li class="active"><spring:message code="cart"/></li>
				</ol>
			</div -->

			<!-- Error form message -->            
            <c:if test="${not empty sessionScope.error}">
		            <div class="col-sm-12 form-message error"  onclick="clearErrors()">
		              <ul>
		                <li>"${sessionScope.error}"</li>
		              </ul>
		            </div>
	        </c:if>

			<section id="do_order">			
			<div class="">
					<form:form method="post" modelAttribute ="orderForm" id="orderForm"  
								action="createOrder">
							<div class="col-sm-4 form-check">
								<input type="text" name="person_name" class="form-control control-label" placeholder='<spring:message code="name"/>'/>
							</div>
							<div class="col-sm-4">
								<input type="email" name="email" class="form-control control-label" value="${orderForm.phone}" placeholder='<spring:message code="email"/>'/>
							</div>
							<div class="col-sm-4 form-check">
								<input type="text" name="phone" class="form-control control-label phone_input"  maxlength="20" placeholder='+375 XX XXX-XX-XX'/>
							</div>
							<div class="col-sm-12 form-check">
									<label>Комментарий</label>
							</div>
							<div class="col-sm-12 form-check">
									<textarea name="comment" class="form-control"  maxlength="100"></textarea>
							</div>

							<div class="text-center">
								<button type="submit" class="btn btn-default submit_order" >
												<spring:message code="submit"/>
								</button>
							</div>
					</form:form>			
			</div>
			</section>
			
			<div class="col-sm-12"></div>
			
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
						<tr class="cart_menu">
							<td class="image"></td>
							<td class="description"><spring:message code="name2"/></td>
							<td class="quantity"><spring:message code="quantity"/></td>
							<!-- td class="total"><spring:message code="total"/></td -->
							<td></td>
						</tr>
				</table>
				<div class="backet_info">
				<table width="100%">
							<c:forEach items="${orderItems}" var="orderItem" varStatus="loop">
							<tr>
								<td class="cart_product">
									<a href="product-details?id=${orderItem.dirNomenclature.id}"><img src="/pics/products/${orderItem.dirNomenclature.code}/S/${orderItem.dirNomenclature.code}_S_0.jpg" alt="" onError="this.onerror=null;this.src='resources/images/products/blank.jpg';"></a>
								</td>
								<td class="cart_description">
									<h4>${orderItem.dirNomenclature.name}</h4>
									<p><b><spring:message code="model"/></b>: ${orderItem.dirNomenclature.model}</p>
									<p><b><spring:message code="size"/></b>: ${orderItem.size}</p>
								</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button">
										<a class="cart_quantity_up" href="#"> + </a>
										<input class="cart_quantity_input" type="text" name="quantity" value="${orderItem.amount}" autocomplete="off" size="2">
										<a class="cart_quantity_down" href="#"> - </a>
									</div>
								</td>
								<td class="cart_delete">
									<a class="cart_quantity_delete" href="delOrder?npp=${orderItem.npp}"><i class="fa fa-times"></i></a>
								</td>
							</tr>
							</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</section> <!--/#cart_items-->


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
    <script src="resources/js/app.js"></script>
    
    <script>
    $(document).ready(function(){

    	if(${fn:length(sessionScope.sessBean.orderItems) == 0})
    	{
    		$(".submit_order").attr("disabled","disabled");
    	}
    	else
    	{
    		$(".submit_order").removeAttr('disabled');
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
    	
    	
    	$(".phone_input").keypress(function(event) {
    		var flag = isPhoneNumberKey(event);
    		//alert(flag);
    		return flag;
    	});
    	
    	
    	$('#orderForm').submit(function(e){
    		e.preventDefault();
    		this.submit();
    		$('#bs-example-modal-sm').modal('show');
    	}).formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                email: {
                    validators: {
                        emailAddress: {
                            message: 'The value is not a valid email address'
                        }
                    }
                },
	            person_name: {
	            	validators: {
	                    notEmpty: {
	                        message: 'The full name is required'
	                    }
	                }
	            }
            }
        });
    	
    	
    });
    
    </script>
</body>
</html>