<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<article>
					<div class="left-sidebar">
						<form  method="GET" name="mA_search" action="${pageContext.request.contextPath}/search">
						
								<div class="row div_bkgr"><!-- genders -->
									<h4><spring:message code="sex.attribute"/></h4>
									<c:forEach items="${genders}" var="gender" varStatus="loop"> 
										<div class="div_Fitem">
											<em><label class="checkbox"><input type="checkbox" name="gndr" id="gndr_${gender.id}" value="${gender.id}"/>${gender.name}</label></em>
										</div>
									</c:forEach>
								</div><!--/genders-->
							
								<div class="row div_bkgr"><!--brands_products-->
									<h4><spring:message code="provider"/></h4>
									<div class="div_Fitem" style="overflow-y:scroll; overflow-x: none; height:300px;">
										<c:forEach items="${providers}" var="provider" varStatus="loop"> 
											<div class="div_Fitem">
												<em><label class="checkbox"><input type="checkbox" name="pn" id="pn_${provider.id}" value="${provider.id}"/>${provider.name}</label></em>
											</div>
										</c:forEach>
									</div>
								</div><!--/brands_products-->
		
								<!-- Категория -->
								<div class="brands_products div_bkgr">
									<div class="row" >
										<div class="col-md-8"><h3><spring:message code="category"/></h3></div>
										<div class="col-md-4"><spring:message code="reset"/></div>
									</div> 
									<div class="brands-name" id="accordian" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<ul class="nav nav-pills nav-stacked">
											<c:forEach items="${categories}" var="category" varStatus="loop"> 
													<li class="checkbox"><a><label><input type="checkbox" name="cat" id="cat_${category.id}" value="${category.id}"/>${category.name}</label></a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
								<!--/Категория-->

								<div class="brands_products div_bkgr">
									<div class="row" >
										<div class="col-md-4"><spring:message code="reset"/></div>
										<div class="col-md-8"><h5><spring:message code="category"/></h5></div>
									</div> 
									<div style="overflow-y:scroll; overflow-x: none; height:200px;">
											<c:forEach items="${categories}" var="category" varStatus="loop"> 
												<div class="brands-name">
													<label><input type="checkbox" name="cat" id="cat_${category.id}" value="${category.id}"/>${category.name}</label>
												</div>
											</c:forEach>
									</div>
								</div>

							
								<!--price-range-->
								<!-- div class="price-range">
									<h2><spring:message code="price.range"/></h2>
									<div class="well text-center">
										 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="200" data-slider-step="5" data-slider-value="[0,45]" id="sl2"><br />
										 <b class="pull-left">BYN 0</b> <b class="pull-right">BYN 60</b>
									</div>
								</div --><!--/price-range-->
								
								<div class="well text-center">
								
									<button type="submit" class="btn"><spring:message code="show"/></button>
									<button type="reset" class="btn btn-fefault"><spring:message code="reset"/></button>
								</div>
						</form>
						
						<!--shipping-->
						<!-- div class="shipping text-center">
							<img src="resources/images/home/shipping.jpg" alt="" />
						</div --> 
					
					</div>
</article>