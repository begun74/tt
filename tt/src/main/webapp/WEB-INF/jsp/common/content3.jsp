<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


						<c:set var="perPage" value="${param.perPage}"/>
						<c:if test="${empty param.perPage}" >
							<c:set var="perPage" value="9"/>
						</c:if>

						<c:set var="p" value="${param.p}"/>
						<c:if test="${empty param.p}" >
							<c:set var="p" value="1"/>
						</c:if>
						
						<c:set var="allItems" value="${fn:length(tails)}"/>
						<c:set var="start" value="${p*perPage-perPage}"/>
						<c:set var="stop" value="${p*perPage-1}"/>
						
						
						
						<div class="features_items"><!--features_items-->
							<c:if test="${allItems eq 0}">
								<div class="col-xs-12">
									<h3><p class="text-center"><spring:message code="not.found"/></p></h3>
								</div>
							</c:if>
							<c:forEach items="${tails}" var="tail" varStatus="loop"  begin="${start}" end="${stop}">
												<div class="col-xs-4">
														<div class="single-products product-image-wrapper">
																<div class="productinfo text-center">
																	<a href="product-details?id=${tail.id}"><img class="" src="/pics/products/${tail.code}/M/${tail.code}_M_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';" alt=""/></a>
																	<c:set var="tail_name" value="${tail.name}" />
																	<c:set var="tn_length" value="${fn:length(tail_name)}" />
																	<h5 class="text-center_12">${fn:substring(tail_name, 0, 15)}</h5>
																	<h5 class="text-center_12">&#160;${fn:substring(tail_name, 15, tn_length) }</h5>
																	<p>
																		<c:set var="price" value="${tail.rozn_price}" />
																		<c:set var="title_price" value="Цена для розницы" />
																		<c:if test="${isShowPrices}">
																			<c:set var="price" value="${tail.opt_price}" />
																			<c:set var="title_price" value="Цена оптовая" />
																		</c:if>
																		
																		<h3 title="${title_price}">${price} руб.</h3>(${tail.tempPrice})
																		
																	</p>
																	<c:set var="prov_name" value=" ${tail.dirProvider.name}" />
																	<p  class="text-center_12">${tail.dirProvider.name}</p>
																	<p><spring:message code="article"/>&#160;${tail.article}</p>
																	<a href="product-details?id=${tail.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i><spring:message code="to.order"/></a>
																</div>
														</div>
												</div>
							</c:forEach>
						</div>

						<!-- div class="col-md-14">
							<div class="col-md-3"></div>
 							<div id="light-pagination" class="pagination col-md-8"></div>
 							<div class="col-md-3"></div>
						</div -->						

						<div id="light_pagination" class="col-md-14" >
						
							<div id="light-pagination" class="col-md-12" ></div>
						
						</div>
						

						<input type="hidden" name="itemPerPage" value="${perPage}"/>
						<input type="hidden" name="itemStart" value="${start}"/>