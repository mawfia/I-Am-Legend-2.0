<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>I Am Legend II: Confirmation</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/shop-homepage.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="LandingPage.do">Home</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="refresh.do">Login</a></li>
					<li><a href="profile.jsp">My Account</a></li>
					<li><a href="ViewCart.do">View Cart</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<p class="lead">IAL2: Confirmation</p>
				<div class="list-group">
					<a href="ViewWeapons.do" class="list-group-item">Weapons</a> <a
						href="ViewAmmo.do" class="list-group-item">Ammo</a> <a
						href="ViewOptics.do" class="list-group-item">Sights & Scopes</a> <a
						href="ViewNutrition.do" class="list-group-item">Food</a> <a
						href="ViewEquipment.do" class="list-group-item">Equipment</a>
				</div>
				<p class="lead">Search</p>
				<div class="list-group">
					<form:form method="POST" action="ItemSearch.do"
						modelAttribute="inventoryItem">
						<form:input path="name" />
						<div class="list-group-search-button">
							<form:label path="category">Category:</form:label>
						</div>
						<div class="list-group-radio">
							<form:radiobutton path="category" value="weapon" />
							Weapon
						</div>
						<div class="list-group-radio">
							<form:radiobutton path="category" value="ammo" />
							Ammo
						</div>
						<div class="list-group-radio">
							<form:radiobutton path="category" value="optic" />
							Sights & Scopes
						</div>
						<div class="list-group-radio">
							<form:radiobutton path="category" value="nutrition" />
							Food
						</div>
						<div class="list-group-radio">
							<form:radiobutton path="category" value="equipment" />
							Equipment
						</div>
						<div class="list-group-search-button">
							<input type="submit" value="Submit" />
						</div>
					</form:form>
				</div>
			</div>

			<div class="col-md-9">

				<div class="col-md-12">
					<div class="row">
						<div class="col-sm-5 col-lg-5 col-md-5">
							<a href="GetItemInfo.do?id=${inventoryItem.id}">
								<div class="thumbnail">
									<div class="image-box">
										<img src="${inventoryItem.imageUrl}" alt="">
									</div>
									<div class="caption">
										<h4>${inventoryItem.name}</h4>

									</div>
								</div>
							</a>
						</div>
						<div class="col-sm-7 col-lg-7 col-md-7">
							<div class="confirmation-title">
								<div>
									<h4>Confirmation</h4>
								</div>
								<div class="confirmation">
									<p>${confirmation}</p>
									<p>
										<fmt:formatNumber value="${inventoryItem.weight}" maxIntegerDigits="3"
											type="number" />
										lbs.
									</p>
									<p>
										<fmt:formatNumber value="${inventoryItem.price}" type="currency" />
									</p>
								</div>
								<div class="right-align">
									<div class="confirmation-footer">
										<span class="right-align">
											<div class="left-align">
												<form:form method="GET" action="LandingPage.do"
													modelAttribute="inventoryItem">
													<input type="submit" value="Continue Shopping" />
												</form:form>
											</div>
											<div class="right-align">
												<form:form method="GET" action="ViewCart.do">
													<input type="submit" value="View Cart" />
												</form:form>
											</div>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row carousel-holder">

						<div class="col-md-12">
							<div id="carousel-example-generic" class="carousel slide"
								data-ride="carousel">
								<div class="carousel-inner">

									<div class="item active">
										<div class="row">

											<c:forEach items="${inventoryItems}" var="item"
												varStatus="itemLoop">
												<form:form method="GET" action="GetItemInfo.do"
													modelAttribute="inventoryItem">
													<a href="GetItemInfo.do?id=${item.id}"> <input
														name="id" type="hidden" value="${item.id}" />
														<div class="col-sm-4 col-lg-4 col-md-4">
															<div class="thumbnail">
																<div class="image-box">
																	<img src="${item.imageUrl}" alt="">
																</div>
																<div class="caption">
																	<h4>${item.name}</h4>
																	<div style="float: left">
																		<h5>
																			<fmt:formatNumber value="${item.price}"
																				type="currency" />
																		</h5>
																	</div>
																	<div style="float: right">
																		<h5>
																			<fmt:formatNumber value="${item.weight}"
																				maxIntegerDigits="3" type="number" />
																			lbs.
																		</h5>
																	</div>
																</div>
															</div>
														</div></a>
												</form:form>
												<c:if test="${(itemLoop.index+1) % 3 == 0}">
										</div>
									</div>
									<div class="item">
										<div class="row">
											</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="center-nav">
									<a href="#carousel-example-generic" data-slide="prev"> <span
										class="glyphicon glyphicon-chevron-left"></span>
									</a> <a href="#carousel-example-generic" data-slide="next"> <span
										class="glyphicon glyphicon-chevron-right"></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container -->

			<div class="container">

				<hr>

				<!-- Footer -->
				<footer>
					<div class="row">
						<div class="col-lg-12">
							<p>Copyright &copy; Your Website 2014</p>
						</div>
					</div>
				</footer>

			</div>
			<!-- /.container -->

			<!-- jQuery -->
			<script src="js/jquery.js"></script>

			<!-- Bootstrap Core JavaScript -->
			<script src="js/bootstrap.min.js"></script>
</body>

</html>
