<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


			

<section>
	<div style="height: 150px"></div>
	<c:if test="${fn:length(popNomencl) gt 0}">
			<div class="row">
			<h3 class="title text-center"><spring:message code="other.models"/></h3>
			<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
		
					<c:forEach  var="i"  begin="0" end="3" >
							<div class=" col-md-3 col-sm-3" >
								<div class="productinfo">
									<a href="product-details?id=${popNomencl[i].id}">
										<img class="" src="/pics/products/${popNomencl[i].code}/S/${popNomencl[i].code}_S_0.jpg" onError="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';" alt=""/>
									</a>
								</div>
							</div>
					</c:forEach>

				</div>
			</div>
			</div>
	</c:if>
	
	<div style="height: 50px"></div>
</section>