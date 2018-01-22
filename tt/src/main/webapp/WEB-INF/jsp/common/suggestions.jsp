<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



	<c:if test="${fn:length(advCamps_slider) gt 0}">
			<div class="suggestions cursor ">
			
				<div class="panelz ">
					<div class="closeDiv border1px">X</div>
				</div>
			
				<div id="logo"  class="" >
					<a href="/action"></a>
				</div>

			    
			    
			    <!-- marquee behavior="scroll" direction="down"  border1px -->
			    <!-- /marquee -->
			    
			    <div>
				<div class="slide-down">
			        		<c:forEach items="${advCamps_slider}" var="advCamp" varStatus="loop" >
				    			<p>${advCamp.text_to_slider}</p>
				    		</c:forEach>
			    </div>
			    	
			    </div>
			    
			
		   </div>
	</c:if>

