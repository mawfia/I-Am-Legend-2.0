<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css.map" />
<link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
	<body style = "background-image: url('/iamlegend2/images/background2.jpg')" class ="background">
	
	
		
		<div class="container-fluid">
		<h1 class="col-sm-offset-5">Registration Page</h1>
		<div class="row login-row">
		<div class="col-sm-4 left"></div>
		<div class="col-sm-4 center" >
		<form:form action="${register}?user=${user}" method="POST" modelAttribute="customer" class="form-horizontal">
		<form:label path="accountNumber"></form:label>
		<form:input path="accountNumber"  type="hidden" />
		<form:errors path="accountNumber" /><br />
		<form:label path="accountBalance"></form:label>
		<form:input path="accountBalance" type="hidden" value="1000"/>
		<form:errors path="accountBalance" />
		<div class="form-group">
		<form:label path="email" class="control-label col-xs-offset-0">Email:</form:label>
		<form:input path="email" />
		<form:errors path="email" /></div>
		<div class="form-group">
		<form:label path="password" class="control-label col-xs-offset-0">Password:</form:label>
		<form:input path="password" placeholder="6-12 alpha numeric charaters" size="28"/>
		<form:errors path="password" /></div>
		<div class="form-group">
		<form:label path="firstName" class="control-label col-xs-offset-0">First Name:</form:label>
		<form:input path="firstName" />
		<form:errors path="firstName" /></div>
		<div class="form-group">
		<form:label path="middleName" class="control-label col-xs-offset-0">Middle Name:</form:label>
		<form:input path="middleName" />
		<form:errors path="middleName" /></div>
		<div class="form-group">
		<form:label path="lastName" class="control-label col-xs-offset-0">Last Name:</form:label>
		<form:input path="lastName" />
		<form:errors path="lastName" /></div>
		<div class="form-group">
		<form:label path="age" class="control-label col-xs-offset-0" >Age:</form:label>
		<form:input path="age" placeholder="Must be 18 or older to register" size="28"/>
		<form:errors path="age" /></div>
		<div class="form-group">
		<form:label path="height" class="control-label col-xs-offset-0">Height:</form:label>
		<form:input path="height" placeholder="Enter inches" />
		<form:errors path="height" /></div>
		<div class="form-group">
		<form:label path="weight" class="control-label col-xs-offset-0">Weight:</form:label>
		<form:input path="weight" placeholder="Enter pounds" />
		<form:errors path="weight" /></div>
		<div class="form-group">
		<form:label path="zipcode" class="control-label col-xs-offset-0">Zip code:</form:label>
		<form:input path="zipcode"  placeholder="Enter five or nine digits for zipcode" />
		<form:errors path="zipcode" /></div>
		<div class="form-group">
		<form:label path="accessLevel" class="control-label col-xs-offset-0"></form:label>
		<form:input path="accessLevel" type="hidden" value="GUEST" />
		<form:errors path="accessLevel" /></div>
		<input type="submit" value="Register" class="btn btn-default"/>
		<a href="shop.do" class="btn btn-default">Shop As Guest</a>
		<a href="login.jsp" class="btn btn-default">Login Page</a>
 		</form:form> 	
 		</div>
		<div class="col-sm-4 right"></div>
		</div>
		</div>
	
</body>
</html>