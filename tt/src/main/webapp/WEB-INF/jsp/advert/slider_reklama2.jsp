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
						<c:if test="${advCamp_slider gt 0}">
							<c:forEach items="${advCamp_slider}" var="advCamp" varStatus="loop">
								<ol class="carousel-indicators">
									<li data-target="#slider-carousel" data-slide-to="${loop.count}" <c:if test="${loop.count eq 1}" >class='active'</c:if>></li>
								</ol>
							</c:forEach>						

							<div class="carousel-inner">
								<c:forEach items="${advCamp_slider}" var="advCamp" varStatus="loop">
									<div <c:if test="${loop.count eq 1}" >class='item active'</c:if> >
										<div class="col-sm-10 slider_reklama" >
												
												<div class="col-sm-3 ">
													<img src="resources/images/akciya.png" class="" alt="" />
												</div>
												<div class="col-sm-6 text-center ">
													<h2>${advCamp.text_to_slider}</h2>
													<p class="text-left"><a href="action#1" class="btn btn-default get"><spring:message code="read.more"/></a></p>
												</div>
												<div class="col-sm-3 ">
													<img src="resources/images/akciya.png" class="" alt="" />
												</div>
										</div>
									</div>
	
								</c:forEach>
							</div>
													
						</c:if>
						
						<!-- div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-10 slider_reklama" >
										
										<div class="col-sm-3 ">
											<img src="resources/images/akciya.png" class="" alt="" />
										</div>
										<div class="col-sm-6 text-center ">
											<h2>${advCamp.text_to_slider}</h2>
											<p class="text-left"><a href="action#1" class="btn btn-default get"><spring:message code="read.more"/></a></p>
										</div>
										<div class="col-sm-3 ">
											<img src="resources/images/akciya.png" class="" alt="" />
										</div>
								</div>
							</div>
							<div class="item">
								<div class="col-sm-3  text-right">
									<img src="resources/images/akciya.png" class="" alt="" />
								</div>
								<div class="col-sm-7 slider_reklama text-center">
									<h3>С 02 мая  по 30 июня 2017г. Акция ОАО"Купалинка". Скидка -50% с розничной цены на товары, участвующие в акции.</h3>
									<p class="text-left"><a href="action#2" class="btn btn-default get"><spring:message code="read.more"/></a></p>
								</div>
							</div>
							<div class="item">
								<div class="col-sm-3  text-right">
									<img src="resources/images/akciya.png" class="" alt="" />
								</div>
								<div class="col-sm-7 slider_reklama text-center">
									<h3>С 03 мая 2017г. Акция ЗАО"Калинка". Распродажа женского ассортимента. Скидка -35%, -50%, -70% от розничной цены на модели, участвующие в акции.</h3>
									<p class="text-left"><a href="action#3" class="btn btn-default get"><spring:message code="read.more"/></a></p>
								</div>
							</div>
						</div -->
						
					</div>
				</div>
			</div>
		</div>
	</section>