<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


						<c:set var="perPage" value="6"/>
						<c:set var="allItems" value="${fn:length(tails)}"/>
						<c:set var="pageNumber" value="${param.pageNumber}"/>
						<c:if test="${empty param.pageNumber}" >
							<c:set var="pageNumber" value="1"/>
							<c:set var="pN" value="0"/>
						</c:if>
						<c:set var="start" value="${pageNumber*perPage-perPage}"/>
						<c:set var="stop" value="${pageNumber*perPage-1}"/>
						

						<div class="features_items"><!--features_items-->
							<c:forEach items="${tails}" var="tail" varStatus="loop"  begin="${start}" end="${stop}">
												<div class="col-sm-4">
													<div class="product-image-wrapper">
														<div class="single-products">
																<div class="productinfo text-center">
																	<a href="product-details.html"><img class="pic1" src="resources/images/product-details/ps_1.jpg" alt="" /></a>
																	<h2>${tail.key.name}</h2>
																	<p>${tail.value.name} ${tail.key.article}</p>
																	<p>(${tail.key.code})</p>
																	<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
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
																<li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
																<li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
															</ul>
														</div>
													</div>
												</div>
							</c:forEach>
						</div>

						<div class="col col-md-12">
							<div class="col col-md-5"></div>
							<div class="pagination col-md-4">
										<ul  class="pagination">
							              
											<c:forEach begin="0" end="${allItems/perPage}" var="pN">
												<li <c:if test="${param.pageNumber == pN+1}" >class="active"</c:if>><a href="index?pageNumber=${pN+1}">${pN+1}</a></li>
											</c:forEach>
							              
										</ul>
							</div>
							<div class="col col-md-3"></div>
						</div>						

