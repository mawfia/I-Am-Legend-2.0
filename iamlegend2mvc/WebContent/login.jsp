<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css.map" />
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body style = "background-image: url('/iamlegend2/images/background1.jpg')" class ="background">
<!-- <img src="images/background.jpg" /> -->
	<div class="container-fluid">
		<div class="row login-row1">
			<div class="col-md-4 left"></div>
			<div class="col-md-3">
				<form:form action="login.do" method="POST" modelAttribute="customer" class="form-horizontal">
					<div class="form-group" >
					<form:label path="email" class="control-label col-sm-offset-0">Email:</form:label>
					<form:input path="email" placeholder="Login with registered email address" size="40" />
					<form:errors path="email" />
					</div>
					<div class="form-group">
					<form:label path="password" class="control-label col-sm-offset-0">Password:</form:label>
					<form:input path="password" />
					<form:errors path="password" />
					<input type="submit" value="Login"  class="btn btn-default"/>
					<a href="register.do" class="btn btn-default">Register</a>
					</div>
				</form:form>
			</div>
			<div class="col-md-4 right"></div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="login row2">
			<div class="col-lg-4 left"></div>
			<div class="col-lg-4 center"></div>
			<div class="col-lg-4 right"></div>
		</div>
	</div>
</body>
</html>