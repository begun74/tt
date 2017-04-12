<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
	<section id="slider"><!--slider-->
		<div class="content">
			<div class="row">
				<div class="col-sm-12">

					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-10 slider_reklama">
									<!-- <span>E</span>-SHOPPER  </h1><h3>ver. ${version}</h3 -->
									<h2>Внимание! Акция во всех магазинах ОАО "Трикотажторг"! Скидка от 15% до 25% !</h2>
									<a href="action" class="btn btn-default get"><spring:message code="read.more"/></a>
								</div>
							</div>
							<div class="item">
								<div class="col-sm-10 slider_reklama">
									<h2> Акция "Расширенная распродажа"* во всех магазинах ОАО "Трикотажторг"! С 4 по 30 апреля!</h2>
									<a href="action#2" class="btn btn-default get"><spring:message code="read.more"/></a>
									<!-- h2>&nbsp</h2 -->
								</div>
							</div>
						
						
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>