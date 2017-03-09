<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ">
    <meta name="keywords" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ" />
    <meta name="yandex-verification" content="fd805143899aabee" />
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
    <link rel="shortcut icon" href="resources/resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<!-- header_top -->
	<%@include file="common/header_top.jsp" %>
	<!-- header_top -->
	 
	 <div id="contact-page" class="container">
			
			<div id="bs-example-modal-sm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="">
			  <div class="modal-dialog modal-sm" role="document">
			    <div class="modal-content">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
			    	<div class="modal-body text-center">
			      		<h2><spring:message code="contact.info.ok"/></h2></a>
      				</div>
      				<div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
				    </div>
			    </div>
			  </div>
			</div>	 
			
			
    	<div class="bg">
	    	<div class="row">    		
	    		<div class="col-sm-12">    			
	    		
	    		<div>
	    			<h3>${sessionScope.sessBean.errorMap}</h3>
	    		</div>   			
				<!-- Error form message -->            
   		            <c:if test="${not empty contactus_error}">
		            <div class="form-message error"  onclick="clearSessErrors('contactus_error')">
		              <ul>
		                <li><h3><spring:message code="contact.info.error"/></h3></li>
		              </ul>
		            </div>
		            </c:if>	
		            <c:if test="${not empty contactus_ok}">
		            <div class="form-message error"  onclick="clearSessErrors('contactus_ok')">
		              <ul>
		                <li><h3> <spring:message code="contact.info.ok"/></h3></li>
		              </ul>
		            </div>
		            </c:if>	
				</div>	
						 		
			</div>    	
    		<div class="row">  	
	    		<div class="col-sm-6">
	    			<div class="contact-form">
	    				<h2 class="title text-center"><spring:message code="contact.info.get_in_touch"/></h2>
	    				<div class="status alert alert-success" style="display: none"></div>
				    	<form id="main-contact-form" class="contact-form row" name="contact-form" modelAttribute="contactUsMessages" method="post" action="contactus?${_csrf.parameterName}=${_csrf.token}">
				            <div class="form-group col-md-6">
				                <input type="text" name="name" class="form-control"  placeholder="<spring:message code="name"/>">
				            </div>
				            <div class="form-group col-md-6">
				                <input type="email" name="email" class="form-control" required="required" placeholder="<spring:message code="email"/>">
				            </div>
				            <div class="form-group col-md-12">
				                <input type="text" name="subject" class="form-control" required="required" placeholder="<spring:message code="subject"/>">
				            </div>
				            <div class="form-group col-md-12">
				                <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="<spring:message code="you.message.hear"/>"></textarea>
				            </div>                        
				            <div class="form-group col-md-12">
				                <button type="submit" class="btn btn-primary pull-right" ><spring:message code="submit"/></button>
				            </div>
				        </form>
	    			</div>
	    		</div>
	    		<div class="col-sm-6">
	    			<div class="contact-info">
	    				<h2 class="title text-center">  <spring:message code="contact.info"/>  </h2>
	    				<address>
	    					<p><spring:message code="contact.info.organization_name"/></h2></p>
							<p><spring:message code="contact.info.address"/></p>
							<p><spring:message code="contact.info.phone1"/></p>
							<p><spring:message code="contact.info.phone2"/></p>
							
							<p><h2 class="title text-center"><spring:message code="contact.info.main"/></h2></p>
							<ul>
								<li>
									<p><b><spring:message code="contact.info.boss"/></b>: <spring:message code="contact.info.boss_name"/></p>
								</li>
								<li>
									<p><b><spring:message code="contact.info.mainoper1"/></b>: <spring:message code="contact.info.mainoper1_name"/></p>
								</li>
								<li>
									<p><b><spring:message code="contact.info.mainoper2"/></b>: <spring:message code="contact.info.mainoper2_name"/></p>
								</li>
							</ul>
	    				</address>
	    				<!-- div class="social-networks">
	    					<h2 class="title text-center">Social Networking</h2>
							<ul>
								<li>
									<a href="#"><i class="fa fa-facebook"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-google-plus"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-youtube"></i></a>
								</li>
							</ul>
	    				</div -->
	    			</div>
    			</div>    			
	    	</div>  
    	</div>	
    </div><!--/#contact-page-->
	
	<div class="col-sm-12">
		<!-- footer -->
		<%@include file="common/footer.jsp" %>
		<!-- footer -->
	</div>

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="resources/js/gmaps.js"></script>
	<script src="resources/js/contact.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.scrollUp.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/app.js"></script>
    
    
    
    <script>
    $(document).ready(function(){
    	
    	/*alert(${sessionScope.sessBean.errorMap}) ;*/
    	
    	
    	
	    $('#main-contact-form').submit(function(e){
			this.submit();
			//$('#bs-example-modal-sm').modal('show');
		})
    });
    </script>
</body>
</html>