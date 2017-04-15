<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<section>
					<div class="col-sm-12 category-tab shop-details-tab"><!--reviews-tab-->
							<div class="col-sm-12">
								<ul class="nav nav-tabs">
									<!-- li><a href="#details" data-toggle="tab">Details</a></li>
									<li><a href="#companyprofile" data-toggle="tab">Company Profile</a></li>
									<li><a href="#tag" data-toggle="tab">Tag</a></li -->
									<li class="active"><a href="#reviews" data-toggle="tab"><spring:message code="reviews"/></a></li>
								</ul>
							</div>
							
							<div class="tab-pane fade active in" id="reviews" >
								<div class="col-sm-12">
									<ul>
										<li><a href=""><i class="fa fa-user"></i>EUGEN</a></li>
										<li><a href=""><i class="fa fa-clock-o"></i>12:41</a></li>
										<li><a href=""><i class="fa fa-calendar-o"></i>31.12.2016</a></li>
									</ul>
									<p></p>
									<p><b><spring:message code="write.your.review"/></b></p>
									
									<form action="#">
										<span>
											<input type="text" placeholder='<spring:message code="name"/>'/>
											<input type="email" placeholder='<spring:message code="email"/>'/>
										</span>
										<textarea name="" ></textarea>
										<!-- b>Rating: </b> <img src="resources/images/product-details/rating.png" alt="" / -->
										<button type="button" class="btn btn-default pull-right">
											<spring:message code="submit"/>
										</button>
									</form>
								</div>
							</div>
							
						</div>
					</div><!--/reviews-tab-->

</section>