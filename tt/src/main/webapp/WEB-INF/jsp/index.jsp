<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <!-- meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" -->
    <!--  meta name="viewport" content="width=device-width, initial-scale=1.0" -->
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
	<link href="resources/css/bootstrap-select.min.css" rel="stylesheet">
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
	<%@include file="advert/advert_main2.jsp" %>
	<!-- advert -->

	<!-- slider_main -->
	<!-- %@include file="advert/slider_main.jsp" % -->
	<!-- slider_main -->
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-md-3">
					<!-- product_filter -->
					<%@include file="common/product_filter1.jsp" %>
					<!-- product_filter -->
				</div>
				<div class="col-sm-8 col-md-9">
					<!-- content -->
					<%@include file="common/content4.jsp" %>
					<!-- content -->
				</div>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->
	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-select.min.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/jquery.simplePagination.js"></script>
    <script src="resources/js/bootstrap-hover-dropdown.min.js"></script>
    <script src="resources/js/app.js"></script>
    
    <script>
	    $(function() {
	    	
				var asc_inv = ${mA_search.asc};
				
			    $(".sortby").click(function() {
			    	$("#sortby").val($(this).attr('id'));
			    	asc_inv = !asc_inv;
			    	$("#asc").val(asc_inv);
			    	
	    		});
				
				
			    var p_p = ${mA_search.p_p};
			    var p_p_get = '&p_p='+p_p;
			    
				var sortby_ = ${mA_search.sortby}; 
				var	sortby_get = '&sortby='+sortby_;

				var	asc_get = '&asc='+${mA_search.asc};
				

				var pns = ${mA_search.pn};
				var pns_get ='';
				
				var gndrs = ${mA_search.gndr};
				var gndrs_get ='';
				
				var cats = ${mA_search.cat};
				var cats_get ='';
							
				var types = ${mA_search.type};
				var types_get ='';

				for (var item in pns) {
	                $('#pn_'+pns[item]).attr("checked","checked");
	                pns_get += '&pn='+pns[item];
	            }
	
				for (var item in gndrs) {
	                $('#gndr_'+gndrs[item]).attr("checked","checked");
	                gndrs_get += '&gndr='+gndrs[item];
	            }

				for(var item in cats) {
					$('#cat_'+cats[item]).attr("checked","checked");
					cats_get += '&cat='+cats[item];
				}
				
				for(var item in types) {
					$('#type_'+types[item]).attr("checked","checked");
					types_get += '&type='+types[item];
				}

				$('#light-pagination').pagination({
		            items: ${allItems},
		            itemsOnPage: ${perPage},
		            cssStyle: 'light-theme',
		            prevText:"<<",
		            nextText:">>",
		            hrefTextPrefix: "?p=",
		            hrefTextSuffix: pns_get + gndrs_get + cats_get + types_get + sortby_get + asc_get + p_p_get,
		            currentPage: ${p}
		        });

		        
	    });

        $(document).ready(function(){
	        $(".search_text").keypress(function(event) {
	        	if ( event.which == 13 ) 
	        	{
	        		processSearchText($(this).val());
	        	}
	        });
			
	    	
	        $("#ch_provider_btn").click(function() {
	        	$('.ch_provider').attr("checked",null);
	        });

	        $("#ch_category_btn").click(function() {
	        	$('.ch_category').attr("checked",null);
	        });

	        $("#ch_gender_btn").click(function() {
	        	$('.ch_gender').attr("checked",null);
	        });
	        
	        $("#ch_type_btn").click(function() {
	        	$('.ch_type').attr("checked",null);
	        });

	        
	        $( ".countType" ).tooltip(
	                {
	                    html: true,
	                    trigger: "manual"
	                }
	                ).on(
	                {
	                	click: function() {
	                		countsPerProdustsFilter('countType',$(this));                            
	                	},
	                    mouseleave: function() {
	                            $(this).tooltip( "hide" );
	                    }

	                });

	        $( ".countProvider" ).tooltip(
	                {
	                    html: true,
	                    trigger: "manual"
	                }
	                ).on(
	                {
	                	click: function() {
	                		countsPerProdustsFilter('countProvider',$(this));                            
	                	},
	                    mouseleave: function() {
	                            $(this).tooltip( "hide" );
	                    }

	                });
	        
	        
	        $( ".countCategory" ).tooltip(
	                {
	                    html: true,
	                    trigger: "manual"
	                }
	                ).on(
	                {
	                	click: function() {
	                		countsPerProdustsFilter('countCategory',$(this));
	                	},
	                    mouseleave: function() {
	                            $(this).tooltip( "hide" );
	                    }

	                });
	        
	       		$( ".countGender" ).tooltip(
	                {
	                    html: true,
	                    trigger: "manual"
	                }
	                ).on(
	                {
	                    click:
	                        function() {
	                			countsPerProdustsFilter('countGender',$(this));
	                        },
	                    mouseleave:
	                        function() {
	                            $(this).tooltip( "hide" );
	                        }
	                }
	                );
        });
        
        
	</script>
	
</body>
</html>