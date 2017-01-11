<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


						<c:set var="perPage" value="9"/>
						<c:set var="allItems" value="${fn:length(tails)}"/>
						<c:set var="p" value="${param.p}"/>
						<c:if test="${empty param.p}" >
							<c:set var="p" value="1"/>
						</c:if>
						<c:set var="start" value="${p*perPage-perPage}"/>
						<c:set var="stop" value="${p*perPage-1}"/>
						
						
						
						<div class="features_items"><!--features_items-->
							
							<div class="row">
							<c:forEach items="${tails}" var="tail" varStatus="loop"  begin="${start}" end="${stop}">
												<div class="col-xs-4">
														<div class="single-products product-image-wrapper">
																<div class="productinfo text-center">
																	<a href="product-details?id=${tail.key.id}"><img class="" src="/pics/products/${tail.key.code}/M/${tail.key.code}_M_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';" /></a>
																	<c:set var="tail_name" value="${tail.key.name}" />
																	<c:set var="tn_length" value="${fn:length(tail_name)}" />
																	<h5>${fn:substring(tail_name, 0, 15)}</h5>
																	<h5>&#160;${fn:substring(tail_name, 15, tn_length) }</h5>
																	<!-- h5>${tail.key.name}</h5 -->
																	<c:set var="prov_name" value=" ${tail.value.name}" />
																	<!-- p>${fn:substring(prov_name, 0, 15)}</p -->
																	<p>${tail.value.name}</p>
																	<p><spring:message code="article"/>  ${tail.key.article}</p>
																	<!-- p>(${tail.key.code})</p -->
																	<a href="product-details?id=${tail.key.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i><spring:message code="to.order"/></a>
																</div>
														</div>
												</div>
							</c:forEach>
							</div>
						</div>

						<div class="col-md-14">
							<div class="col-md-4"></div>
 							<div id="light-pagination" class="pagination col-md-6"></div>
 							<div class="col-md-4"></div>
						</div>						

