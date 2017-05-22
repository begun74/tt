<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



	<div class="basket">
            
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <div class="basket-item-count">
                    <span class="count">${backetBean.countItems}</span>
                    <img src="resources/assets/images/icon-cart.png" alt="" />
                </div>

                <div class="total-price-basket"> 
                    <span class="lbl">your cart:</span>
                    <span class="total-price">
                        <span class="sign">$${backetBean.allPrice}</span><span class="value"></span>
                    </span>
                </div>
            </a>

            <ul class="dropdown-menu">
                <c:forEach items="${backetBean.itemsTM}" var="particleboard" varStatus="vs">
                    <div class="basket-item">
                        <div class="row">
                            <div class="col-xs-4 col-sm-4 no-margin text-center">
                                <div class="thumb">
                                    <img src="resources/pics/${particleboard.key.id}.jpeg" width="73" height="73"  onerror="this.onerror=null;this.src='resources/assets/images/products/nopicture.jpg';" />
                                </div>
                            </div>
                            <div class="col-xs-8 col-sm-8 no-margin">
                                <div class="title">${particleboard.key.dirBrand.name}</div>
                                <div class="title">${particleboard.key.thickness}x${particleboard.key.length}x${particleboard.key.weight}  (${particleboard.value})</div>
                                <div class="price">$${particleboard.key.price}</div>
                            </div>
                        </div>
                        <a class="close-btn" href="del-from-backet?id=${particleboard.key.id}"></a>
                    </div>
                </c:forEach>

                <li class="checkout">
                    <div class="basket-item">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6">
                                <!--<a href="cart.html.html" class="le-button inverse">View cart</a>-->
                                <a href="#" class="le-button inverse">View cart</a>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <a href="createOrder" class="le-button">Checkout</a>
                                <!--<a href="checkout.html" class="le-button">Checkout</a>-->
                            </div>
                        </div>
                    </div>
                </li>

            </ul>
        </div> 