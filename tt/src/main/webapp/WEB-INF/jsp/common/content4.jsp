<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


						<c:set var="perPage" value="${param.p_p}"/>


						<c:set var="p" value="${param.p}"/>
						<c:if test="${empty param.p}" >
							<c:set var="p" value="1"/>
						</c:if>
						
						<c:set var="start" value="${0}"/>
						<c:set var="stop" value="${perPage-1}"/>
						
						
						
						<div class="features_items"><!--features_items-->
							<div class="row">
									<div class="col-sm-12 col-xs-12 text-right">
									</div> 
							</div>

							<div class="row">
								<c:if test="${allItems gt 0}">
									<div class="col-xs-6 text-left">
										<p><spring:message code="remains.of.goods.on"/> <fmt:formatDate pattern = "dd-MM-yyyy"  value="${tails.iterator().next().tails.iterator().next().create_date}" />.</p>
									</div>
									<div class="col-sm-6 text-right font20px"> 
										<c:if test="${mA_search.sortby eq 0 }">
											<a id="0" href=""  class="waiting sortby" ><span class="label label-success"><spring:message code="by.name"/></span></a>
											<a id="1" href=""  class="waiting sortby" ><span class="label label-default"><spring:message code="by.price"/></span></a>
										</c:if>
										<c:if test="${mA_search.sortby eq 1 }">
											<a id="0" href=""  class="waiting sortby" ><span class="label label-default"><spring:message code="by.name"/></span></a>
											<a id="1" href=""  class="waiting sortby" ><span class="label label-success"><spring:message code="by.price"/></span></a>
										</c:if>
										<select id="selectperp" class="selectpicker service-small" style="font: 10px" data-width="55px" name="${mA_search.p_p}" >
        										<option value="" ></option>
        										<option value="4" <c:if test="${mA_search.p_p eq 4 }">selected</c:if> >4</option>
        										<option value="6" <c:if test="${mA_search.p_p eq 6 }">selected</c:if> >6</option>
        										<option value="9" <c:if test="${mA_search.p_p eq 9 }">selected</c:if> >9</option>
										</select>
										
									</div>
								</c:if>
							</div>
								<hr/>

							<c:if test="${allItems eq 0}">
								<div class="col-xs-12">
									<h3><p class="text-center"><spring:message code="not.found"/></p></h3>
								</div>
							</c:if>
							
							<c:forEach items="${tails}" var="tail" varStatus="loop"  begin="${start}" end="${stop}">
												<c:set var="tail_name" value="${tail.name}" />
												<c:set var="tn_length" value="${fn:length(tail_name)}" />
												<c:set var="arr_tail_name" value="${fn:split(tail.name,' ')}"/>
												
												<div class="col-sm-4 col-xs-8">
														<div class="single-products product-image-wrapper ">
																<div class="productinfo text-center" title="${tail_name}" >
																	<a href="product-details?id=${tail.id}">
																		<img class="" src="/pics/products/${tail.code}/M/${tail.code}_M_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';" alt=""/>
																	</a>
																	<!-- h5 class="text-center_12">${fn:substring(tail_name, 0, 15)}</h5>
																	<h5 class="text-center_12">&#160;${fn:substring(tail_name, 15, tn_length) }</h5 -->
																	<h5 class="text-center_12">${fn:toUpperCase(arr_tail_name[0])}</h5>
																	<p><spring:message code="model"/>&#160;${tail.model}</p>
																	<p>
																		<c:set var="price" value="${tail.rozn_price}" />
																		<c:set var="title_price"><spring:message code="retail.price"/></c:set>
																		<c:if test="${isShowPrices}">
																			<c:set var="price" value="${tail.opt_price}" />
																			<c:set var="title_price"><spring:message code="trade.price"/></c:set>
																		</c:if>
																		<h4>${title_price}</h4>
																		<h4 title="${title_price}">${price} руб.</h4>
																		
																	</p>
																	<c:set var="prov_name" value=" ${tail.dirProvider.name}" />
																	<p  class="provider_name">${tail.dirProvider.name}</p>
																	<!-- p><spring:message code="article"/>&#160;${tail.article}</p -->
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
						

