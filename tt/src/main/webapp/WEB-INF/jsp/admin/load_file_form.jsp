<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="form-cols">
	   		<div class="col1">            

				<form:form id="addFileForm" class="formBox" role="form"  
					  			enctype="multipart/form-data" 
					  			action="${pageContext.request.contextPath}/admin/addFile?${_csrf.parameterName}=${_csrf.token}#tabs-2" 
					  			method="POST">
				
				  			<div class="clearfix checkbox">
									        <div class="lab">
									        	<label>Колонки</label>
									        </div>
									        <div class="con">
									        	<!-- select name="cols" id="cols" multiple="multiple">
											        <c:forEach begin="1" end="10" var="colN">
														<option>${colN}</option>		
													</c:forEach>
												</select -->
													<label><input type="checkbox" name="cols" id="allCols" />Все&nbsp;&nbsp;&nbsp;</label>
											        <c:forEach begin="1" end="10" var="colN">
														<label><input type="checkbox" name="cols" id="col${colN}" value="${colN}"/>${colN}</label>		
													</c:forEach>
																		                   		  
									        </div>
							</div>
							<div class="clearfix radio">									        
									        <div class="lab">
									        	<label>Начальная строка</label>
									        </div>
									        <div class="con">
											        <c:forEach begin="1" end="10" var="rowN">
														<label><input type="radio" name="rowN" id="rowN" value="${rowN}"/>${rowN}</label>		
													</c:forEach>
									        </div>
							 </div>
							 
							
					         <div style="margin-bottom: 15px" class="clearfix file">
							         	<p><spring:message code="load"/> <input type="file" name="file"></p>
							 </div>
				
					         <button type="submit" class=""><spring:message code="load"/></button>
					         <input type="hidden" name ="act" id ="act" value="1"/>
				</form:form>
			</div>
</div>

