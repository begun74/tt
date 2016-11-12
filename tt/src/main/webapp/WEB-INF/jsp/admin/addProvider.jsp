<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

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
		            <li><a href="#tabs-1"><spring:message code="admin.addProvider" /></a></li>
		           	<li><a href="#tabs-2"><spring:message code="admin.load.info.from.file" /></a></li>
            </ul>
          	<div class="box-content">    
          
            <div id="tabs-1">  
              	<div class="form-cols">
				    		<div class="col1">
							  	<form:form id="addProviderForm" class="formBox" role="form"
							  			commandName="addProviderForm"
							  			enctype="multipart/form-data" 
							  			action="${pageContext.request.contextPath}/admin/addProvider?${_csrf.parameterName}=${_csrf.token}" 
							  			method="POST"
							  			modelAttribute="dirProvider">
							  			
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
						                   			<label><spring:message code="code"/></label>
										         </div>
										         <div class="con">
						                   			<input name="code" id="code" type="text" class="input" value="" > 
										         </div>
										 </div>
								         
						         <button type="submit" class=""><spring:message code="label.button.add"/></button>
						         <input type="hidden" name ="id_dir_provider" id ="id_dir_provider" value="-1"/>
						    	</form:form>
							</div>					    
				    		<div class="col2">
							</div>
					    </div>
            </div>

            <div id="tabs-2">
					<%@include file="load_file_form.jsp" %>
            </div>
            
          </div><!-- /box-content -->  
        </div>        
        <!-- /box -->
        
        <!-- /box -->
        <div class="box">
 				    			<table class="tab" border="0">
				    				<tr align="center">
					    				<th width="45%"><spring:message code="name2"/></th>
					    				<th width="28%"><spring:message code="code"/></th>
					    				<th width="27%">Action</th>
				    				</tr>
				    			</table>

									<div align="center" style="overflow-y:scroll; overflow-x: none; height:400px; width:100%;">
										<table class="tab tab-drag">
											<c:forEach items="${dirProviders}" var="dirProvider">
												<tr>
													<td class="dragHandle">&nbsp;</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirProvider.name}'); $('#code').val('${dirProvider.code}');">(${dirProvider.id}) ${dirProvider.name}</td>
													<td style="cursor:pointer;" onclick="$('#name').val('${dirProvider.name}'); $('#code').val('${dirProvider.code}');">${dirProvider.code}</td>
							         				<td>
							         					<!-- a href="javascript:editBrand(${dirProvider.id});" class="ico ico-edit" onclick=""></a -->
							         					<a href="javascript:delObject(${dirProvider.id},'DirProvider','1');" class="ico ico-delete" onclick=""></a>
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
				
  			checkAllCols(${allCount});
	
	    });
	    
    </script>
</body>

</html>