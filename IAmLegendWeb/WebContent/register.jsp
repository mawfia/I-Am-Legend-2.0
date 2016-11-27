<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
</head>
<body>
	
	<h1>Registration Page</h1>
	<form:form action="register.do" method="POST" modelAttribute="user">
		<form:label path="accountNumber"></form:label>
		<form:input path="accountNumber"  type="hidden" />
		<form:errors path="accountNumber" /><br />
		<form:label path="accountBalance"></form:label>
		<form:input path="accountBalance" type="hidden" value="1000"/>
		<form:errors path="accountBalance" /><br />
		<form:label path="email">Email:</form:label>
		<form:input path="email" />
		<form:errors path="email" /><br />
		<form:label path="password">Password:</form:label>
		<form:input path="password" placeholder="6-12 alpha numeric charaters" size="28"/>
		<form:errors path="password" /><br />
		<form:label path="firstName">First Name:</form:label>
		<form:input path="firstName" />
		<form:errors path="firstName" /><br />
		<form:label path="middleName">Middle Name:</form:label>
		<form:input path="middleName" />
		<form:errors path="middleName" /><br />
		<form:label path="lastName">Last Name:</form:label>
		<form:input path="lastName" />
		<form:errors path="lastName" /><br />
		<form:label path="age">Age:</form:label>
		<form:input path="age" placeholder="Must be 18 or older to register"/>
		<form:errors path="age" /><br />
		<form:label path="height">Height:</form:label>
		<form:input path="height" placeholder="Enter inches" />
		<form:errors path="height" /><br />
		<form:label path="weight">Weight:</form:label>
		<form:input path="weight" placeholder="Enter pounds" />
		<form:errors path="weight" /><br />
		<form:label path="zipcode">Zip code:</form:label>
		<form:input path="zipcode"  placeholder="Enter five or nine digits for zipcode" />
		<form:errors path="zipcode" /><br />
		<form:label path="accessLevel" ></form:label>
		<form:input path="accessLevel" type="hidden" value="GUEST" />
		<form:errors path="accessLevel" /><br />
		<input type="submit" value="Register" />
 	</form:form> 
 			
</body>
</html>