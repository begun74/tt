<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



					<div class="left-sidebar">
						<form  method="GET" name="mA_search" action="${pageContext.request.contextPath}/search">
								<div class="brands_products"><!-- genders -->
									<h2><spring:message code="sex.attribute"/></h2>
									<div class="brands-name" >
										<ul class="nav nav-pills nav-stacked">
											<c:forEach items="${genders}" var="gender" varStatus="loop"> 
												<li class="checkbox"><a><label><input type="checkbox" name="gndr" id="gndr_${gender.id}" value="${gender.id}"/>${gender.name}</label></a></li>
											</c:forEach>
										</ul>
									</div>
								</div><!--/genders-->
							
								<div class="brands_products"><!--brands_products-->
									<h2><spring:message code="provider"/></h2>
									<div class="brands-name" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<ul class="nav nav-pills nav-stacked">
											<c:forEach items="${providers}" var="provider" varStatus="loop"> 
												<li class="checkbox"><a><label><input type="checkbox" name="pn" id="pn_${provider.id}" value="${provider.id}"/>${provider.name}</label></a></li>
											</c:forEach>
										</ul>
									</div>
								</div><!--/brands_products-->
		
								<!-- Категория -->
								<!-- div class="brands_products">
									<h2><spring:message code="category"/></h2>
									<div class="panel-group category-products" id="accordian" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<ul class="nav nav-pills nav-stacked">
											<c:forEach items="${categories}" var="category" varStatus="loop"> 
													<li class="checkbox"><label><input type="checkbox" />${category.name}</label></li>
											</c:forEach>
										</ul>
									</div>
								</div -->
								<!--/Категория-->
							
								
								<div class="price-range"><!--price-range-->
									<h2><spring:message code="price.range"/></h2>
									<div class="well text-center">
										 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="200" data-slider-step="5" data-slider-value="[0,45]" id="sl2"><br />
										 <b class="pull-left">BYN 0</b> <b class="pull-right">BYN 60</b>
									</div>
								</div><!--/price-range-->
								
								<div class="well text-center">
								
									<button type="submit" class="btn"><spring:message code="show"/></button>
									<button type="reset" class="btn btn-fefault"><spring:message code="reset"/></button>
								</div>
						</form>
						
						
						<div class="shipping text-center"><!--shipping-->
							<img src="resources/images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>
