<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<section id="slider"><!--slider-->
		<div class="content " style="height: 200px;">
			<div class="row ">
				<div class="col-sm-12">

					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<c:if test="${fn:length(advCamps_slider) gt 0}">
									<ol class="carousel-indicators">
														<c:forEach var="i" begin="1" end="${fn:length(advCamps_slider)}">
																<li data-target="#slider-carousel" data-slide-to="${i}"></li>
														</c:forEach>	
									</ol>
												
			
									<div class="carousel-inner">
										<c:forEach items="${advCamps_slider}" var="advCamp" varStatus="loop" >
											<div <c:if test="${loop.count eq 1}">class="item active"</c:if> <c:if test="${loop.count gt 1}">class="item"</c:if>>
												<div class="col-sm-10 slider_reklama" >
														
														<div class="col-sm-3 ">
															<img src="resources/images/akciya.png" class="" alt="" />
														</div>
														<div class="col-sm-6 text-center ">
															<h2>${advCamp.text_to_slider}</h2>
															<p class="text-left"><a href="action#${loop.count}" class="btn btn-default get"><spring:message code="read.more"/></a></p>
														</div>
														<div class="col-sm-3 ">
															<img src="resources/images/akciya.png" class="" alt="" />
														</div>
												</div>
											</div>
										</c:forEach>
									</div>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</section>