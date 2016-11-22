<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        
    <!-- #sidebar -->
    <div id="sidebar">

        <!-- mainmenu -->
        <ul id="floatMenu" class="mainmenu">
          <li class="first"><a href="#"><spring:message code="dictionary"/></a>
            <ul class="submenu">
              <li><a href="admin?act=1" ><spring:message code="provider"/></li>          
              <li><a href="admin?act=2" ><spring:message code="nomenclature"/></li>          
            </ul>
          </li>
          <li><a href="#"><spring:message code="admin.load.info.from.file"/></a>
            <ul class="submenu">
              <li><a href="admin?act=3" ><spring:message code="load.tails"/></a></li>          
            </ul>
          </li>
          <li><a href="#" ><spring:message code="admin.view.orders"/></a>
          	<ul class="submenu">
          		<li><a href="admin?act=6" ><spring:message code="all.orders"/></a></li>
          	</ul>
          </li>
          <!-- li><a href="">Settings</a></li>
          <li class="last"><a href="#" class="link">ThemeForest</a></li -->
        </ul>
        <!-- /.mainmenu -->

    </div>
    <!-- /#sidebar -->
