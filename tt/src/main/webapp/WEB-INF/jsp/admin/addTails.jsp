<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="ru">

<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name='robots' content='all, follow' />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    
    <title>Great admin</title>   
    <link href="resources/admin/css/default.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/blue.css" rel="stylesheet" type="text/css" media="screen" /> <!-- color skin: blue / red / green / dark -->
    <link href="resources/admin/css/datePicker.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/wysiwyg.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/fancybox-1.3.1.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/visualize.css" rel="stylesheet" type="text/css" media="screen" />

	<script type="text/javascript" src="resources/admin/js/jquery-1.4.2.min.js"></script>   
    <script type="text/javascript" src="resources/admin/js/jquery.dimensions.min.js"></script>
    
    <!-- // Tabs // -->
    <script type="text/javascript" src="resources/admin/js/ui.core.js"></script>
    <script type="text/javascript" src="resources/admin/js/jquery.ui.tabs.min.js"></script>

    <!-- // Table drag and drop rows // -->
    <script type="text/javascript" src="resources/admin/js/tablednd.js"></script>

    <!-- // Date Picker // -->
    <script type="text/javascript" src="resources/admin/js/date.js"></script>
    <!--[if IE]><script type="text/javascript" src="admin/js/jquery.bgiframe.js"></script><![endif]-->
    <script type="text/javascript" src="resources/admin/js/jquery.datePicker.js"></script>

    <!-- // Wysiwyg // -->
    <script type="text/javascript" src="resources/admin/js/jquery.wysiwyg.js"></script>

    <!-- // Graphs // -->
    <script type="text/javascript" src="resources/admin/js/excanvas.js"></script>
    <script type="text/javascript" src="resources/admin/js/jquery.visualize.js"></script>

    <!-- // Fancybox // -->
  	<script type="text/javascript" src="resources/admin/js/jquery.fancybox-1.3.1.js"></script>

    <!-- // File upload // --> 
    <script type="text/javascript" src="resources/admin/js/jquery.filestyle.js"></script>
    
    <script type="text/javascript" src="resources/admin/js/init.js"></script>
    
    <script type="text/javascript" src="resources/admin/js/app.js"></script>
    
    <title><spring:message code="admin.title"/></title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div id="main">
    <!-- #header -->
    	<!-- footer -->
			<%@include file="header_top.jsp" %>
		<!-- footer -->

    <!-- /header -->
    <!-- #content -->
    <div id="content">
				<!-- breadcrumbs -->
		        <div class="breadcrumbs">
		          <ul>
		            <li class="home"><a href="index">Homepage</a></li>
		          </ul>
		        </div>
		        <!-- /breadcrumbs -->

      	<!-- box>
       	<div id="tabs-statistic" class="box">
       	</div>
       	<!-- /box -->



		<div id="tabs" class="box">
            <ul class="bookmarks">
		           	<li><a href="#tabs-1"><spring:message code="admin.load.info.from.file" /></a></li>
		            <li><a href="#tabs-2"><spring:message code="tails" /></a></li>
            </ul>
          	<div class="box-content">    
          

            <div id="tabs-1">
   		            <!-- Error form message -->            
   		            <c:if test="${not empty error}">
		            <div class="form-message error"  onclick="clearErrors()">
		              <p>Ошибка :</p>
		              <ul>
		                <li>"${error}"</li>
		              </ul>
		            </div>
		            </c:if>
            		
					<div class="form-cols cols2v1">
		   				<div class="col1">
		   						<form id="addTempFileTail" class="formBox" role="form"  
									  			enctype="multipart/form-data" 
									  			action="${pageContext.request.contextPath}/admin/addFileTail?${_csrf.parameterName}=${_csrf.token}#tabs-1" 
									  			method="POST">
						 				    			<table class="tab" border="0">
										    				<tr align="center">
											    				<th width="5%">№п/п</th>
											    				<th width="30%">Наименование</th>
											    				<th width="23%">Поставщик</th>
											    				<th width="5%">Кол-во</th>
											    				<th width="5%">Первая цена</th>
											    				<th width="12%">Размер</th>
											    				<th width="22%">Дата загрузки</th>
											    				<th width="4%" class="checkbox"><input type="checkbox" name="" class="check-all" onclick='checkboxAny($(this).attr("checked"),"tailIndex");'/></th>
											    				<th width="2%">Action</th>
										    				</tr>
										    			</table>
						
															<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
																<table class="tab tab-drag">
																	<c:forEach items="${tempTails}" var="tail" varStatus="loop">
																		<tr>
																			<td >${loop.count}</td>
																			<td style="cursor:pointer;">${tail.dirNomenclature.name}</td>
																			<td style="cursor:pointer;">${tail.amountTail}</td>
																			<td style="cursor:pointer;">${tail.firstPrice}</td>
																			<td style="cursor:pointer;">${tail.size}</td>
																			<td style="cursor:pointer;">${tail.create_date}</td>
																			<td class="checkbox"><input name="tailIndex" class="tailIndex" value="${tail.index}" type="checkbox"  style="cursor:pointer;" class="checkbox"/></td>
													         				<td >
													         					<!-- a href="javascript:editBrand(${dirProvider.id});" class="ico ico-edit" onclick=""></a -->
													         					<a href="" class="ico ico-delete" onclick=""></a>
													         				</td>
													         				
																		</tr>
																	</c:forEach>
																</table>
															</div>
	
															<div class="tab-action">
										        				<div class="clearfix">
											        					<button type="submit" class="" onclick="" >Обработать</button>
											        					<label><input name="update" id="update" type="checkbox" class="checkbox" checked="checked" />удалить старые остатки</label>
										        				</div>
									        				</div>
									      <input type="hidden" name ="act" id ="act" value="3"/>
		   				       </form>
						</div>

		   				<div class="col2">            
							<form:form id="addTempFileTail" class="formBox" role="form"  
								  			enctype="multipart/form-data" 
								  			action="${pageContext.request.contextPath}/admin/addTempFileTail?${_csrf.parameterName}=${_csrf.token}#tabs-1" 
								  			method="POST">
							
											  			<div class="clearfix">
														         <div class="lab">
												                    <label>Количество</label> 
														         </div>
														         <div  style="width: 25px" class="conleft" >
																	<input name="col_amountTail" id="col_amountTail" type="text" class="input" value="${sessionBean.mA_loadTail.col_amountTail}">										         
														         </div>
														 </div>
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Первая Цена</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_firstPrice" id="col_firstPrice" type="text" class="input" value="${sessionBean.mA_loadTail.col_firstPrice}"> 
														         </div>
														</div>
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Код Номенкл.</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_codeNomencl" id="col_codeNomencl" type="text" class="input" value="${sessionBean.mA_loadTail.col_codeNomencl}"> 
														         </div>
														</div>
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Размер</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_size" id="col_size" type="text" class="input" value="${sessionBean.mA_loadTail.col_size}"> 
														         </div>
														</div>
														
														<div class="clearfix">									        
																        <div class="lab">
																        	<label>Начальная строка</label>
																        </div>
																        <div style="width: 25px" class="conleft">
																        	<input name="row" id="row" type="text" class="input" value="${sessionBean.mA_loadTail.row}" >
																        </div>
														</div>
								         
														 <div class="clearfix file">
											              <div class="lab"><label for="file"><spring:message code="load"/> </label></div>
											              <div class="con"><input type="file" name="file" class="upload-file" id="file" /></div>
											            </div>
	
														<div class="clearfix">									        
															<div class="lab">
															   	<label><input name="save" id="save" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadTail.save == true}">checked="checked" </c:if>  >Сохранить</label>
															</div>
														</div>
	
														<div class="clearfix">									        
															<div class="lab">
															   	<label><input name="autoload" id="autoload" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadTail.autoload == true}">checked="checked"</c:if>  >Автозагрузка</label>
															</div>
														</div>
														</br>
								        				<div class="clearfix">
									        				<div class="lab">	
									        					<button type="submit" style="width:130px;" onclick="if(file.value.length == 0) {alert('Выберите файл!'); return false};" ><spring:message code="admin.load.info.from.file"/></button>
									        				</div>
								        				</div>
								        				
								         <input type="hidden" name ="act" id ="act" value="3"/>
							</form:form>
					</div>

				</div>


					
            </div>

            <div id="tabs-2">  
              	<div class="form-cols">
				    		<div class="col1">
							  	<form:form id="addTailForm" class="formBox" role="form"
							  			commandName="loadTailForm"
							  			enctype="multipart/form-data" 
							  			action="${pageContext.request.contextPath}/admin/addTail?${_csrf.parameterName}=${_csrf.token}" 
							  			method="POST"
							  			modelAttribute="Tail">
							  			
								         
						         <!-- button type="submit" class=""><spring:message code="label.button.add"/></button -->
						         <input type="hidden" name ="id_tail" id ="id_tail" value="-1"/>
						    	</form:form>
							</div>					    
					    </div>
            </div>
            
          </div><!-- /box-content -->  
        </div>        
        <!-- /box -->
        
        <!-- /box -->
        <div class="box">
	 				    			<table class="tab" border="0">
						    				<tr align="center">
								    				<th width="5%">№п/п</th>
								    				<th width="18%">Наименование</th>
								    				<th width="23%">Поставщик</th>
								    				<th width="5%">Кол-во</th>
								    				<th width="7%">Первая цена</th>
								    				<th width="12%">Размер</th>
								    				<th width="22%">Дата загрузки</th>
								    				<th width="5%">Action</th>
						    				</tr>
					    			</table>

									<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
										<table class="tab tab-drag">
											<c:forEach items="${tails}" var="tail" varStatus="loop">
												<tr>
													<td >${loop.count}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.dirNomenclature.name}</td>
													<td style="cursor:pointer;" onclick="$('#amountTail').val('${tail.amountTail}'); $('#firstPrice').val('${tail.firstPrice}');">${tail.amountTail}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.firstPrice}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.size}</td>
													<td style="cursor:pointer;">${tail.create_date}</td>
							         				<td>
							         					<!-- a href="javascript:editBrand(${dirProvider.id});" class="ico ico-edit" onclick=""></a -->
							         					<a href="javascript:delObject('Tail',${tail.id},'3');" class="ico ico-delete" onclick=""></a>
							         				</td>
												</tr>
											</c:forEach>
										</table>
									</div>
        </div>
        <!-- /box -->
    

    </div>
    <!-- /#content -->
	<!-- Sidebar -->
	        <%@include file="sidebar.jsp" %>
	<!-- /#sidebar-wrapper -->    
	        
	<!-- #footer -->
    <div id="footer">
      <p>© 2010 Great Admin | <a href="#main">Top</a></p>
    </div>
    <!-- /#footer -->
	

  <!-- /#main --> 
  <script>
  		$(document).ready(function(){
				
  			
			/*$("#save").attr("checked","checked");*/
	    });
	    
    </script>
</body>

</html>