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
		            <li><a href="#tabs-1"><spring:message code="admin.addNomencl" /></a></li>
		           	<li><a href="#tabs-2"><spring:message code="admin.load.info.from.file" /></a></li>
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
            
              	<div class="form-cols">
				    		<div class="col1">
							  	<form:form id="addNomenclForm" class="formBox" role="form"
							  			commandName="loadNomenclrForm"
							  			enctype="multipart/form-data" 
							  			action="${pageContext.request.contextPath}/admin/addNomencl?${_csrf.parameterName}=${_csrf.token}" 
							  			method="POST"
							  			modelAttribute="dirNomencl">
							  			
							  			<div class="clearfix">
										         <div class="lab">
						                   			<label><spring:message code="code"/></label>
										         </div>
										         <div class="con">
						                   			<input name="code" id="code" type="text" class="input" value="" > 
										         </div>
										</div>
							  			<div class="clearfix">
										         <div class="lab">
								                    <label><spring:message code="name2"/></label> 
										         </div>
										         <div class="con">
													<input name="name" id="name" type="text" class="input" value="" >										         
										         </div>
										</div>
							  			<div class="clearfix">
										         <div class="lab">
						                   			<label><spring:message code="article"/></label>
										         </div>
										         <div class="con">
						                   			<input name="article" id="article" type="text" class="input"> 
										         </div>
										</div>
								         
						         <button type="submit" class=""><spring:message code="label.button.add"/></button>
						         <input type="hidden" name ="id_dir_provider" id ="id_dir_provider" value="-1"/>
						    	</form:form>
							</div>					    
				    		<div class="col2">
					    		<div class="div_photo_panel">
					    			<table>
					    				<tr>
						    				<td>
								    			<div id="div_pn0" class="div_pn">
								    			</div>
							    			</td>
						    				<td>
								    			<div id="div_pn1" class="div_pn">
								    			</div>
							    			</td>
						    				<td>
								    			<div id="div_pn2" class="div_pn">
								    			</div>
							    			</td>
						    				<td>
								    			<div id="div_pn3" class="div_pn">
								    			</div>
							    			</td>
						    			
						    			<!-- td>
						    			<div class="div_pn">
											<div id="btn-delete1" class="btn-delete">
											    <a><img title="Удалить" src="resources/admin/images/ico/delete.png"></a>
											</div>
						    				<img id="photoNomenclature1" src="resources/images/products/blank.jpg" class="share img-responsive"  onerror="this.onerror=null;this.src='resources/images/products/blank.jpg';" alt="" />
						    			</div>
						    			</td>
						    			<!-- td>
						    			<div class="div_pn">
											<div id="btn-delete2" class="btn-delete">
											    <a><img title="Удалить" src="resources/admin/images/ico/delete.png"></a>
											</div>
						    				<img id="photoNomenclature2" src="resources/images/products/blank.jpg" class="share img-responsive"  onerror="this.onerror=null;this.src='resources/images/products/blank.jpg';" alt="" />
						    			</div>
						    			</td>
						    			<td>
						    			<div class="div_pn">
											<div id="btn-delete3" class="btn-delete">
											    <a><img title="Удалить" src="resources/admin/images/ico/delete.png"></a>
											</div>
						    				<img id="photoNomenclature3" src="resources/images/products/blank.jpg" class="share img-responsive"  onerror="this.onerror=null;this.src='resources/images/products/blank.jpg';" alt="" />
						    			</div>
						    			</td -->
						    			</tr>
					    			</table>
				    			</div>
				    			<form id="addPhotoNomenclForm" class="formBox" role="form"
							  			enctype="multipart/form-data" 
							  			action="${pageContext.request.contextPath}/admin/addPhotoNomencl?${_csrf.parameterName}=${_csrf.token}" 
							  			method="POST">
										       
							      	<div class="clearfix file">
									         <div class="lab">Файл </div>
									         <div class="con"><input type="file" name="photoFile" class="upload-file" id="photoFile"/></div>
									</div>
			        				<div class="clearfix">
					        				<div class="lab">	
						        					<button type="submit" class="" id="submitPhoto" onclick="if(photoFile.value.length == 0) {alert('Выберите файл!'); return false};" ><spring:message code="load"/></button>
					        				</div>
			        				</div>
									<input type="hidden" name ="codeNomencl" id ="codeNomencl" />
				    			</form>
				    		
							</div>
					    </div>
            </div>

            <div id="tabs-2">
   		            <!-- Error form message -->            
   		            <c:if test="${not empty error}">
		            <div class="form-message error"  onclick="clearErrors()">
		              <p>Ошибка :</p>
		              <ul>
		                <li>"${error}"</li>
		              </ul>
		            </div>
		            </c:if>
            		
					<div class="form-cols">
	   				<div class="col1">            

						<form:form id="addFileNomencl" class="formBox" role="form"  
							  			enctype="multipart/form-data" 
							  			action="${pageContext.request.contextPath}/admin/addFileNomencl?${_csrf.parameterName}=${_csrf.token}#tabs-2" 
							  			method="POST">
						
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label><spring:message code="code"/></label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_code" id="col_code" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_code}"> 
													         </div>
													</div>
										  			<div class="clearfix">
													         <div class="lab">
											                    <label><spring:message code="name2"/></label> 
													         </div>
													         <div  style="width: 25px" class="conleft" >
																<input name="col_name" id="col_name" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_name}">										         
													         </div>
													 </div>
										  			<div class="clearfix">
													         <div class="lab">
											                    <label>Модель</label> 
													         </div>
													         <div  style="width: 25px" class="conleft" >
																<input name="col_model" id="col_model" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_model}">										         
													         </div>
													 </div>
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label>Код НомГруппы</label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_codeNomenclGroup" id="col_codeNomenclGroup" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_codeNomenclGroup}"> 
													         </div>
													</div>
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label><spring:message code="article"/></label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_article" id="col_article" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_article}"> 
													         </div>
													</div>
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label>Пол</label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_gender" id="col_gender" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_gender}"> 
													         </div>
													</div>
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label>Код Поставщика</label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_codeProvider" id="col_codeProvider" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_codeProvider}"> 
													         </div>
													</div>
										  			<div class="clearfix">
													         <div class="lab">
									                   			<label>Путь к картинкам</label>
													         </div>
													         <div style="width: 25px" class="conleft">
									                   			<input name="col_pathToImage" id="col_pathToImage" type="text" class="input" value="${sessionBean.mA_loadNomencl.col_pathToImage}"> 
													         </div>
													</div>
													<div class="clearfix">									        
															        <div class="lab">
															        	<label>Начальная строка</label>
															        </div>
															        <div style="width: 25px" class="conleft">
															        	<input name="row" id="row" type="text" class="input" value="${sessionBean.mA_loadNomencl.row}" >
															        </div>
													</div>
							         
													 <div class="clearfix file">
										              <div class="lab"><label for="file"><spring:message code="load"/> </label></div>
										              <div class="con"><input type="file" name="file" class="upload-file" id="file"/></div>
										            </div>

													<div class="clearfix">									        
														<div class="lab">
														   	<label><input name="save" id="save" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadNomencl.save == true}">checked="checked" </c:if>  >Сохранить</label>
														</div>
													</div>

													<div class="clearfix">									        
														<div class="lab">
														   	<label><input name="autoload" id="autoload" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadNomencl.autoload == true}">checked="checked"</c:if>  >Автозагрузка</label>
														</div>
													</div>
						
							        				<div class="clearfix">
								        				<div class="lab">	
								        					<button type="submit" class="" onclick="if(file.value.length == 0 ) {alert('Выберите файл!'); return false};" ><spring:message code="load"/></button>
								        				</div>
							        				</div>
													        				
							         <input type="hidden" name ="act" id ="act" value="2"/>
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
					    				<th width="54%"><spring:message code="name2"/></th>
					    				<th width="23%"><spring:message code="code"/></th>
					    				<th width="23%">Action</th>
				    				</tr>
				    			</table>

									<div align="center" style="overflow-y:scroll; overflow-x: none; height:400px; width:100%;">
										<table class="tab tab-drag">
											<c:forEach items="${dirNomencls}" var="dirNomencl" varStatus="loop">
												<tr>
													<td class="dragHandle">&nbsp;</td>
													<td>${loop.count}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirNomencl.name}'); $('#code').val('${dirNomencl.code}'); $('#article').val('${dirNomencl.article}'); viewPhotoNomencl('${dirNomencl.code}');">${dirNomencl.name}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirNomencl.name}'); $('#code').val('${dirNomencl.code}'); $('#article').val('${dirNomencl.article}'); viewPhotoNomencl('${dirNomencl.code}');">${dirNomencl.code}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirNomencl.name}'); $('#code').val('${dirNomencl.code}'); $('#article').val('${dirNomencl.article}'); viewPhotoNomencl('${dirNomencl.code}');">${dirNomencl.model}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirNomencl.name}'); $('#code').val('${dirNomencl.code}'); $('#article').val('${dirNomencl.article}'); viewPhotoNomencl('${dirNomencl.code}');">${dirNomencl.article}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirNomencl.name}'); $('#code').val('${dirNomencl.code}'); $('#article').val('${dirNomencl.article}'); viewPhotoNomencl('${dirNomencl.code}');">${dirNomencl.dirNomenclGroup.name}(${dirNomencl.dirNomenclGroup.code})</td>
							         				<td>
							         					<!-- a href="javascript:editBrand(${dirProvider.id});" class="ico ico-edit" onclick=""></a -->
							         					<a href="javascript:delObject('DirNomenclature', ${dirNomencl.id},'2');" class="ico ico-delete" onclick=""></a>
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
			$('#submitPhoto').attr('disabled','disabled');
	    });
  		
  		function viewPhotoNomencl(code) {
  			
  			$('#codeNomencl').val(code);
			$('#submitPhoto').attr('disabled','');
  			var error_load_photo = false;
			for(var i=0; i<4 ;i++) {
				//---- Clear div ---
				$('#div_pn'+i).html("");
				//$('#div_pn'+i).prepend("<div id='btn-delete"+i+"' class='btn-delete'><a href='javascript:delPhotoFile("+code+","+i+")''><img src='resources/admin/images/ico/delete.png' /></a></div>");
				
				//---- Add photo ---
				$('<img/>', {
					
					id:  'photoNomenclature'+i,
					src: '/pics/products/'+code+'/S/'+code+'_S_'+i+'.jpg',
					alt: '',
					error:  function(e){
						  //$( this ).attr( "src", 'resources/images/products/blank.jpg' );
						error_load_photo = true;
					} 
					  
				}).appendTo($('#div_pn'+i));
				
				if(!error_load_photo) 
				{
					//alert(error_load_photo);
					$('#div_pn'+i).append("<div id='btn-delete"+i+"' class='btn-delete'><a href='javascript:delPhotoFile("+code+","+i+")''><img src='resources/admin/images/ico/delete.png' /></a></div>");
				}

				
			}
  						
  		}
  		
  		
  		
  		function f1() {
  			$('#photoNomenclature0').error(function() {
  			    $( this ).attr( "src", 'resources/images/products/nopicture.jpg' );
  			    
  		    }).attr('src','/pics/products/'+code+'/S/'+code+'_S_0.jpg');

  			$('#photoNomenclature1').error(function() {
  			    $( this ).attr( "src", 'resources/images/products/blank.jpg' );
  		    }).attr('src','/pics/products/'+code+'/S/'+code+'_S_1.jpg');
			
  			$('#photoNomenclature2').error(function() {
  			    $( this ).attr( "src", 'resources/images/products/blank.jpg' );
  		    }).attr('src','/pics/products/'+code+'/S/'+code+'_S_2.jpg');

  			$('#photoNomenclature3').error(function() {
  			    $( this ).attr( "src", 'resources/images/products/blank.jpg' );
  		    }).attr('src','/pics/products/'+code+'/S/'+code+'_S_3.jpg');
  			
  			$('#btn-delete0 a').attr('href','javascript:delPhotoFile('+code+',0)');
  			$('#btn-delete1 a').attr('href','javascript:delPhotoFile('+code+',1)');
  			$('#btn-delete2 a').attr('href','javascript:delPhotoFile('+code+',2)');
  			$('#btn-delete3 a').attr('href','javascript:delPhotoFile('+code+',3)');

  		}

    </script>
</div>
</body>

</html>