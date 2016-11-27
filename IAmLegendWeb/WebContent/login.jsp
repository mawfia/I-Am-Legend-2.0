<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	
	<c:choose>
		<c:when test="${ not empty email }">
       		<p>${email}</p>
    	</c:when>
    	<c:otherwise>    
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${ not empty password }">
       		<p>${password}</p>
    	</c:when>
    	<c:otherwise>   
		</c:otherwise>
	</c:choose>
	
	<form:form action="login.do" method="POST" modelAttribute="user" class="container">
		<form:input path="email" placeholder="Login with registered email address" size="30"/>
		<form:errors path="email" /><br />
		<form:input path="password" />
		<form:errors path="password" /><br />
		<input type="submit" value="Login" />
	</form:form><br />
	
	<form action="register.do" method="GET" class="container">
		<input type="submit" value="Register" />
	</form>

</body>
</html>