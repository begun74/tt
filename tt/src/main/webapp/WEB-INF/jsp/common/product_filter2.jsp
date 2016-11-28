<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


					<div class="left-sidebar">
						<h2><spring:message code="category"/></h2><!-- Категория -->
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<c:forEach items="${categories}" var="category" varStatus="loop"> 

								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
												<!-- span class="badge pull-right"><i class="fa fa-plus"></i></span -->
												${category.name}
											</a>
										</h4>
									</div>
								</div>
							
							</c:forEach>
							
						</div><!--/category-products-->
					
						<div class="brands_products"><!--brands_products-->
							<h2><spring:message code="brands"/></h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<c:forEach items="${brands}" var="brand" varStatus="loop"> 
										<li><a href="#"><span class="pull-right"></span>${brand.name}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div><!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2><spring:message code="price.range"/></h2>
							<div class="well text-center">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="200" data-slider-step="5" data-slider-value="[0,45]" id="sl2"><br />
								 <b class="pull-left">BYN 0</b> <b class="pull-right">BYN 60</b>
							</div>
						</div><!--/price-range-->
						
						<div class="shipping text-center"><!--shipping-->
							<img src="resources/images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>
