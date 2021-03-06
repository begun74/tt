<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <!-- meta name="viewport" content="width=device-width, initial-scale=1.0" -->
    <meta name="description" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ">
    <meta name="keywords" content="одежда весна 2017 | Продукция ведущих трикотажных фабрик | Верхний трикотаж | Бельевой трикотаж | Чулочно-носочные изделия | трикотаж | трикотаж в РБ" />
    <meta name="robots" content="all" />
    <meta name="author" content="">
    <title>ОАО «Трикотажторг»</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
	<link href="resources/css/simplePagination.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<div class="modal " id="waiting" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	    <div class="modal-content text-center" >
	    	<img alt="" src="${pageContext.request.contextPath}/resources/images/WAITING3.gif">
	    </div>
	</div>		

	<!-- header_top -->
	<%@include file="common/header_top.jsp" %>
	<!-- header_top -->
		
	
	<section>
		<div class="container">
				<div class="col-sm-12 find_panel">
					<h3><spring:message code="find.text"/>(${allItems}):&#160;&#160;'${findText}' </h3>
				</div>			
				<div class="col-sm-12"><p/></div>
				<div class="row">
					<div class="col-sm-12">
						<!-- content -->
						<%@include file="common/content_find.jsp" %>
						<!-- content -->
					</div>
				</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->
	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/jquery.simplePagination.js"></script>
    <script src="resources/js/bootstrap-hover-dropdown.min.js"></script>
    <script src="resources/js/app.js"></script>
    
    <script>
    $(function() {
	    var p_p_get = '&p_p='+$('#selectperp').val();
	    
		//var sortby_ = 0; 
		//var	sortby_get = '&sortby='+sortby_;

		//var	asc_get = '&asc='+${mA_search.asc};

    
    	//var findText = '&text=${findText}';
		
        $('#light-pagination').pagination({
            items: ${allItems},
            itemsOnPage: ${p_p},
            cssStyle: 'light-theme',
            prevText:"<<",
            nextText:">>",
            hrefTextPrefix: "?p=",
            hrefTextSuffix: '&text=${findText}' + p_p_get,
            currentPage: ${p}
        });


        $(".search_text").keypress(function(event) {
        	if ( event.which == 13 ) 
        	{
        		processSearchText($(this).val());
        	}
        });
        
        
        $("#selectperp").change(function() {
        	$('#waiting').modal('show');
        	window.location.href = 'find?text=${findText}&p_p='+ $('#selectperp').val();
        });
        
    	$("#sortbyName").click(function() {
        	$('#waiting').modal('show');
    	});

    	$("#sortbyPrice").click(function() {
        	$('#waiting').modal('show');
    	});

        
	});


    </script>
</body>
</html>