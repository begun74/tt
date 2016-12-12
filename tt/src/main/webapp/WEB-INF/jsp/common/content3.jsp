<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


						<c:set var="perPage" value="6"/>
						<c:set var="allItems" value="${fn:length(tails)}"/>
						<c:set var="p" value="${param.p}"/>
						<c:if test="${empty param.p}" >
							<c:set var="p" value="1"/>
							<c:set var="pN" value="0"/>
						</c:if>
						<c:set var="start" value="${p*perPage-perPage}"/>
						<c:set var="stop" value="${p*perPage-1}"/>
						
						<c:set var="startPage" value="0"/>
						<c:set var="stopPage" value="${allItems/perPage}"/>

						<div class="features_items"><!--features_items-->
							
							<c:forEach items="${tails}" var="tail" varStatus="loop"  begin="${start}" end="${stop}">
												<div class="col-sm-4">
													<div class="product-image-wrapper">
														<div class="single-products">
																<div class="productinfo text-center">
																	<!-- a href="product-details?id=${tail.key.id}"><img class="pic1" src="resources/images/product-details/ps_1.jpg" alt="" /></a -->
																	<a href="product-details?id=${tail.key.id}"><img class="pic1" src="resources/images/products/${tail.key.code}/M/${tail.key.code}_M_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture.jpg';"" /></a>
																	<h5>${tail.key.name}</h5>
																	<p>${tail.value.name}</p>
																	<p><spring:message code="article"/>  ${tail.key.article}</p>
																	<!-- p>(${tail.key.code})</p -->
																	<a href="" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i><spring:message code="add.to.cart"/></a>
																</div>
																<!-- div class="product-overlay">
																	<div class="overlay-content">
																		<h2>$56</h2>
																		<p>Easy Polo Black Edition</p>
																		<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
																	</div>
																</div -->
														</div>
														<div class="choose">
															<ul class="nav nav-pills nav-justified">
																<!-- li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li -->
																<li><a href="#"><i class="fa fa-plus-square"></i><spring:message code="compare"/></a></li>
															</ul>
														</div>
													</div>
												</div>
							</c:forEach>
						</div>

						<div class="col col-md-12">
 							<div id="light-pagination" class="pagination"></div>
						</div>						

