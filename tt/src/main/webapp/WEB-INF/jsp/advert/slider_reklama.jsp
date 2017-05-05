<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
	<section id="slider"><!--slider-->
		<div class="content " style="height: 200px;">
			<div class="row ">
				<div class="col-sm-12">

					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-10 slider_reklama text-center">
									<h2>Акция "День скидок" во всех магазинах ОАО "Трикотажторг"!</h2>
									
									<p class="text-left"><a href="action#1" class="btn btn-default get"><spring:message code="read.more"/></a></p>
								</div>
							</div>
							<div class="item">
								<div class="col-sm-10 slider_reklama text-center">
									<h2>С 02 мая  по 30 июня 2017г. Акция ОАО"Купалинка". Скидка -50% с розничной цены на товары, участвующие в акции.</h2>
									<p class="text-left"><a href="action#1" class="btn btn-default get"><spring:message code="read.more"/></a></p>
								</div>
							</div>
							<div class="item">
								<div class="col-sm-10 slider_reklama text-center">
									<h2>С 03 мая 2017г. Акция ЗАО"Калинка". Распродажа женского ассортимента. Скидка -35%, -50%, -70% от розничной цены на модели, участвующие в акции.</h2>
									<p class="text-left"><a href="action#1" class="btn btn-default get"><spring:message code="read.more"/></a></p>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>