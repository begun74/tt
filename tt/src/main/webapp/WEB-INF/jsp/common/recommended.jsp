<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


			

<section>
	<c:if test="${fn:length(popNomencl) gt 0}">
			<div class="col-sm-12 recommended_items">
			<h3 class="title text-center"><spring:message code="recommended.items"/></h3>
			<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">

			<c:forEach items="${popNomencl}" var="pop_dn" varStatus="loop" >
			<div class="item active">	
			<div class="col-sm-3">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<a href="product-details?id=${pop_dn.id}"><img class="" src="/pics/products/${pop_dn.code}/S/${pop_dn.code}_S_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';" alt=""/></a>
										</div>
									</div>
								</div>
			</div>
			</div>
			</c:forEach>

				</div>
			</div>
			</div>
	</c:if>
</section>