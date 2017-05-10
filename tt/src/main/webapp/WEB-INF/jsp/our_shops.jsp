<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

	<!-- header_top -->
	<%@include file="common/header_top.jsp" %>
	<!-- header_top -->
		
	<!-- advert -->
	<%@include file="advert/advert_main.jsp" %>
	<!-- advert -->

	<!-- slider_main -->
	<!-- %@include file="common/slider_main.jsp" % -->
	<!-- slider_main -->
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 padding-center">
					<div style="text-align:center;"><h3>Наши магазины</h3></div>
				</div>
				<div class="col-sm-12 padding-right">
					<div>
						<div class="col-sm-2 "></div>
						<div class="col-sm-6 ">
							<p><b>Магазин № 1 «Трикотаж»</b>
							<p>Адрес: г. Минск, ул. Платонова, д. 30/2</p>
							<p>Тел.: (017) 292-39-11</p>
							<p>Режим работы:</p>
							<p>понедельник - пятница: с 10.00 до 19.00</p>
							<p>суббота: с 10.00 до 18.00</p>
							<p>воскресенье — выходной день</p>
						</div>
						<div class="col-sm-4 padding-right">
							<img alt="" class="div_h200_w282" src="resources/images/shop/shop1_1.jpg" onerror="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';"> 
						</div>
					</div>
					<h3>&nbsp</h3>
					<div>
						<div class="col-sm-2 "></div>
						<div class="col-sm-6 padding-right">
							<p><b>Магазин № 2 «Трикотаж»</b>
							<p>Адрес:222160,  г. Жодино, ул. Сырокваша, д. 4-21</p>
							<p>Тел.: (01775)  3-37-52</p>
							<p>Режим работы:</p>
							<p>понедельник - пятница: с 10.00 до 19.00</p>
							<p>суббота: с 10.00 до 18.00</p>
							<p>воскресенье — выходной день</p>
						</div>
						<div class="col-sm-4 padding-right">
							<img alt="" class="div_h200_w282" src="resources/images/shop/shop2_2.jpg" onerror="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';"> 
						</div>
					</div>
					<h3>&nbsp</h3>
					<div>
						<div class="col-sm-2 "></div>
						<div class="col-sm-6 padding-right">
							<p><b>Магазин № 3 «Трикотаж»</b>
							<p>Адрес: г. Солигорск, ул. Ленина, д. 55</p>
							<p>Тел.: (210) 4-63-06</p>
							<p>Режим работы:</p>
							<p>понедельник - пятница: с 10.00 до 19.00</p>
							<p>суббота: с 10.00 до 18.00</p>
							<p>воскресенье: с 10.00 до 16.00</p>
						</div>
						<div class="col-sm-4 padding-right">
							<img alt="" class="div_h200_w282" src="resources/images/shop/shop3_3.jpg" onerror="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';"> 
						</div>
					</div>
					<h3>&nbsp</h3>
					<div>
						<div class="col-sm-2 "></div>
						<div class="col-sm-6 padding-right">
							<p><b>Магазин № 4 «Трикотаж»</b>
							<p>Адрес: г. Вилейка, ул. Красноармейская, д. 55</p>
							<p>Тел.: (271) 5-47-84</p>
							<p>Режим работы:</p>
							<p>понедельник - пятница: с 10.00 до 19.00</p>
							<p>суббота: с 10.00 до 18.00</p>
							<p>воскресенье: с 10.00 до 16.00</p>
						</div>
						<div class="col-sm-4 padding-right div_200_282">
							<img alt="" class="div_h200_w282" src="resources/images/shop/shop4_4.jpg" onerror="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';"> 
						</div>
					</div>
					<h3>&nbsp</h3>
					<div>
						<div class="col-sm-2 "></div>
						<div class="col-sm-6 padding-right">
							<p><b>Магазин № 5 «Трикотаж»</b>
							<p>Адрес: г. Гомель, ул. Интернациональная, д. 13</p>
							<p>Тел.: (232) 74-79-30</p>
							<p>Режим работы:</p>
							<p>понедельник - пятница: с 10.00 до 19.00</p>
							<p>суббота: с 10.00 до 18.00</p>
							<p>воскресенье: с 10.00 до 16.00</p>
						</div>
						<div class="col-sm-3 padding-right div_200_282">
							<img alt="" class="div_h200_w282" src="resources/images/shop/shop5_5.jpg" onerror="this.onerror=null;this.src='resources/images/products/nopicture2.jpg';"> 
						</div>
					</div>
					<h3>&nbsp</h3>
				</div>
				
				
			</div>
		</div>
	</section>

	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->

    <script src="resources/js/app.js"></script>
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/jquery.simplePagination.js"></script>
    <script src="resources/js/bootstrap-hover-dropdown.min.js"></script>

    <script>
	    $(document).ready(function(){
	        $(".search_text").keypress(function(event) {
	        	if ( event.which == 13 ) 
	        	{
	        		processSearchText($(this).val());
	        	}
	        });
	    });    
    </script>

</body>
</html>