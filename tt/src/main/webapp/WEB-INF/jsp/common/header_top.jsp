<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> (+375 17) 292-45-90</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> office@trikotag.by</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">

				<div class="row">
					<div class="col-sm-4">
						<!-- div class="logo pull-left">
							<a href="index.html"><img src="resources/images/home/logo.png" alt="" /></a>
						</div -->
						
						<ul>
							<li class="dropdown"><a class="btn btn-default dropdown-toggle usa" data-toggle="dropdown" >Язык | Lang<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li role="presentation"><a role="menuitem" tabindex="0"
										href="index?locale=ru">RU</a></li>
									<li role="presentation"><a role="menuitem" tabindex="1"
										href="index?locale=en">EN</a></li>
								</ul>
							</li>
						</ul>
						<!-- div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									DOLLAR
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#">Canadian Dollar</a></li>
									<li><a href="#">Pound</a></li>
								</ul>
							</div>
						</div -->
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
								<ul class="">
									<li class="dropdown"><a href="cart"><spring:message code="cart"/>  <span class="badge"><c:out value="${fn:length(sessionScope.sessBean.orderItems)}"/></span></a>
										<!-- ul class="dropdown-menu">
											<li>
							                        <div class="row">
							                            <div class="col-xs-4 col-sm-4 no-margin text-center">
							                                <div class="thumb">
							                                    <img src="resources/images/products/nopicture.jpg" width="73" height="73"  onerror="this.onerror=null;this.src='resources/assets/images/products/nopicture.jpg';" />
							                                </div>
							                            </div>
								                            <div class="col-xs-8 col-sm-8 no-margin">
								                                <div class="title">Blueberry</div>
								                                <div class="price">$270.00</div>
								                            </div>
							                            
							                        </div>
							                        <a class="close-btn" href="del-from-backet?id=${particleboard.key.id}"></a>
											</li>
							                <li>
							                        <div class="">
							                            <div class="col-xs-12 col-sm-6">
							                                <a href="createOrder" class="le-button"><spring:message code="to.order"/></a>
							                            </div>
							                        </div>
							                </li>
											
										</ul -->
					                </li>
									<!-- li><a href="login"><i class="fa fa-user"></i>Account</a></li -->
									<!-- li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li -->
									<!-- li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li -->
									<li><a href="login"><i class="fa fa-lock"></i> Login</a>  <c:if test="${fn:length(sessionScope.authUser.username) >0}" ><c:out value="(${sessionScope.authUser.username})"/></c:if></li>
					                
					            </ul>							
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
		</div>
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="index.html" class="active"><spring:message code="home"/></a></li>
								<!-- li class="dropdown"><a href="#"><spring:message code="shop"/><i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="index"><spring:message code="products"/></a></li>
										<li><a href="cart.html"><spring:message code="cart"/></a></li> 
										<li><a href="login.html">Login</a></li> 
                                    </ul>
                                </li --> 
								<!-- li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="blog.html">Blog List</a></li>
										<li><a href="blog-single.html">Blog Single</a></li>
                                    </ul>
                                </li --> 
								<!-- li><a href="404.html">404</a></li -->
								<li><a href="our_shops" ><spring:message code="our.shops"/></a></li>
								<li class="dropdown"><a href="contact-us.html"><spring:message code="contacts"/></a>
								<li><a href="vacancies" ><spring:message code="info.vacancies"/></a></li>
								<li><a href="action" ><spring:message code="action1"/></a></li>
								<li><a href="about_company" ><spring:message code="about.company"/></a></li>
								</ul>
								
								</li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" class="search_text" value="${findText}" placeholder="<spring:message code="search"/>"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
