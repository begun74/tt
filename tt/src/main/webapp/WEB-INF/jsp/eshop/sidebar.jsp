<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        
    <!-- #sidebar -->
    <div id="sidebar">

        <!-- mainmenu -->
        <ul id="floatMenu" class="mainmenu">
          <li><a href="#" ><spring:message code="admin.view.orders"/></a>
          	<ul class="submenu">
          		<li><a href="eshop?act=1" >Новые</a></li>
          		<li><a href="eshop?act=2" >Архив заказов</a></li>
          	</ul>
          </li>
        </ul>
        <!-- /.mainmenu -->

    </div>
    <!-- /#sidebar -->
