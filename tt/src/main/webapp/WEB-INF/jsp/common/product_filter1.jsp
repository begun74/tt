<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<article>

<!-- Small modal -->
<!-- button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-sm">Small modal</button -->

					<div class="modal " id="productFilterWait" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
					    <div class="modal-content text-center" style="margin: 0 auto; opacity: 1;">
					    	<img alt="" src="${pageContext.request.contextPath}/resources/images/WAITING3.gif">
					    </div>
					</div>		
					<div class="left-sidebar">
						<form  method="GET" name="product_filter" action="${pageContext.request.contextPath}/search">
						
								<!--type-->
								<div class="row div_bkgr border1px">
										<div class="div_name"><spring:message code="nomencl.group2"/></div>
										<c:forEach items="${types}" var="type" varStatus="loop"> 
	
											<c:set var="type_name" value="${type.name}"/>
											<c:if test="${not empty type.diff_name}" >
												<c:set var="type_name" value="${type.diff_name}"/>
											</c:if>
	
											<div class="div_Fitem">
												<em><label class="checkbox cursor countType" data-toggle="tooltip" data-placement="left" lookup_id="${type.id}" title="${type_name}"><input class="ch_type" type="checkbox" name="type" id="type_${type.id}" value="${type.id}"/>${type_name}</label></em>
											</div>
										</c:forEach>
									<!-- >div class="row div_Fitem"><button type="button" id="ch_type_btn" class="btn btn-default btn-xs waiting"><spring:message code="checkbox.off"/></button></div -->
										<div class="row div_Fitem">
											<div class="col-sm-12">
												<div class="col-sm-4 col-xs-7 text-left">
													<button type="button" id="ch_type_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button>
												</div>
												<div class="col-sm-8 col-xs-5 text-right">
													<button type="submit" class="btn btn-warning btn-xs  waiting border1px"><spring:message code="show"/></button>
												</div>
											</div>
										</div>

								</div><!--/type-->

								<div class="row div_bkgr border1px"><!-- genders -->
									<div class="div_name"><spring:message code="sex.attribute"/></div>
									<c:forEach items="${genders}" var="gender" varStatus="loop"> 
										<div class="div_Fitem">
											<em><label class="checkbox cursor countGender" data-toggle="tooltip" data-placement="left" lookup_id="${gender.id}" title="${gender.name}"><input class="ch_gender" type="checkbox" name="gndr" id="gndr_${gender.id}" value="${gender.id}" />${gender.name}</label></em>
										</div>
									</c:forEach>
									<!-- div class="row div_Fitem"><button type="button" id="ch_gender_btn" class="btn btn-default btn-xs waiting"><spring:message code="checkbox.off"/></button></div -->
									<div class="row div_Fitem">
										<div class="col-sm-12">
											<div class="col-sm-4 text-left">
												<button type="button" id="ch_gender_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button>
											</div>
											<div class="col-sm-8 text-right">
												<button type="submit" class="btn btn-warning btn-xs  waiting"><spring:message code="show"/></button>
											</div>
										</div>
									</div>
								</div><!--/genders-->
							
								<div class="row div_bkgr border1px"><!--brands_products-->
									<div class="div_name "><spring:message code="provider"/></div>
									<div class="row div_Fitem" style="overflow-y:scroll; overflow-x: none; height:200px;">
										<c:forEach items="${providers}" var="provider" varStatus="loop"> 
											<div class="div_Fitem">
												<em><label class="checkbox cursor countProvider" data-toggle="tooltip" data-placement="left" lookup_id="${provider.id}"><input class="ch_provider" type="checkbox" name="pn" id="pn_${provider.id}" value="${provider.id}"/>${provider.name}</label></em>
											</div>
										</c:forEach>
									</div>
									<!-- div class="row div_Fitem">
										<button type="button" id="ch_provider_btn" class="btn btn-default btn-xs waiting"><spring:message code="checkbox.off"/></button>
										<button type="submit" class="btn btn-default btn-xs"><spring:message code="show"/></button>
									</div -->
									<div class="row div_Fitem">
										<div class="col-sm-12">
											<div class="col-sm-4 text-left">
												<button type="button" id="ch_provider_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button>
											</div>
											<div class="col-sm-8 text-right">
												<button type="submit" class="btn btn-warning btn-xs  waiting"><spring:message code="show"/></button>
											</div>
										</div>
									</div>
								</div><!--/brands_products-->
		
								<!-- Категория -->
								<div class="row div_bkgr border1px">
									<div class="div_name"><spring:message code="category"/></div>
									<div class="row div_Fitem" style="overflow-y:scroll; overflow-x: hidden; height:200px;">
										<c:forEach items="${categories}" var="category" varStatus="loop"> 
											<div class="div_Fitem">
													<em><label class="checkbox cursor countCategory" data-toggle="tooltip" data-placement="left" lookup_id="${category.id}"><input type="checkbox" class="ch_category" name="cat" id="cat_${category.id}" value="${category.id}"/>${category.name}</label></em>
											</div>
										</c:forEach>
									</div>
									<div class="row div_Fitem">
										<div class="col-sm-12">
											<div class="col-sm-4 text-left">
												<button type="button" id="ch_category_btn" class="btn btn-default btn-xs"><spring:message code="checkbox.off"/></button>
											</div>
											<div class="col-sm-8 text-right">
												<button type="submit" class="btn btn-warning btn-xs  waiting"><spring:message code="show"/></button>
											</div>
										</div>
									</div>
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
								
								<!-- div class="well text-center">
									<button type="submit" class="btn btn-default"><spring:message code="show"/></button>
								</div -->
								
							<input type="hidden" name="sortby" id="sortby" value='${mA_search.sortby}'/>
							<input type="hidden" name="asc" id="asc" value='${mA_search.asc}'/>
	
						</form>
						
						<!--shipping-->
						<!-- div class="shipping text-center">
							<img src="resources/images/home/shipping.jpg" alt="" />
						</div --> 
					
					</div>
</article>