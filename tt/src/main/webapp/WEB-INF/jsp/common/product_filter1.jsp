<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<article>
					<div class="left-sidebar">
						<form  method="GET" name="mA_search" action="${pageContext.request.contextPath}/search">
						
								<div class="row div_bkgr border1px"><!-- genders -->
									<div class="div_name"><h4><spring:message code="sex.attribute"/></h4></div>
									<c:forEach items="${genders}" var="gender" varStatus="loop"> 
										<div class="div_Fitem">
											<em><label class="checkbox cursor"><input class="ch_gender" type="checkbox" name="gndr" id="gndr_${gender.id}" value="${gender.id}"/>${gender.name}</label></em>
										</div>
									</c:forEach>
									<div class="row div_Fitem"><button type="button" id="ch_gender_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button></div>
								</div><!--/genders-->
							
								<div class="row div_bkgr border1px"><!--brands_products-->
									<div class="div_name "><h4><spring:message code="provider"/> <span class="badge">42</span></h4></div>
									<div class="row div_Fitem" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<c:forEach items="${providers}" var="provider" varStatus="loop"> 
											<div class="div_Fitem">
												<em><label class="checkbox cursor"><input class="ch_provider" type="checkbox" name="pn" id="pn_${provider.id}" value="${provider.id}"/>${provider.name}</label></em>
											</div>
										</c:forEach>
									</div>
									<div class="row div_Fitem"><button type="button" id="ch_provider_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button></div>
								</div><!--/brands_products-->
		
								<!-- Категория -->
								<div class="row div_bkgr border1px">
									<div class="div_name"><h4><spring:message code="category"/></h4></div>
									<div class="row div_Fitem" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<c:forEach items="${categories}" var="category" varStatus="loop"> 
											<div class="div_Fitem">
													<em><label class="checkbox cursor"><input type="checkbox" class="ch_category" name="cat" id="cat_${category.id}" value="${category.id}"/>${category.name}</label></em>
											</div>
										</c:forEach>
									</div>
									<div class="row div_Fitem"><button type="button" id="ch_category_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button></div>
								</div>
								<!--/Категория-->

								<!--price-range-->
								<!-- div class="price-range">
									<h2><spring:message code="price.range"/></h2>
									<div class="well text-center">
										 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="200" data-slider-step="5" data-slider-value="[0,45]" id="sl2"><br />
										 <b class="pull-left">BYN 0</b> <b class="pull-right">BYN 60</b>
									</div>
								</div --><!--/price-range-->
								
								<div class="well text-center">
									<button type="submit" class="btn btn-default"><spring:message code="show"/></button>
								</div>
						</form>
						
						<!--shipping-->
						<!-- div class="shipping text-center">
							<img src="resources/images/home/shipping.jpg" alt="" />
						</div --> 
					
					</div>
</article>