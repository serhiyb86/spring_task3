<%@ page import="com.servlet.test.model.Courses" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Serh
  Date: 10/30/2021
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add student</title>
</head>
<body>
<h2>Enter the new student data and press "ADD"</h2>
<p><a href="/">HOME</a> </p>
<p></p>
<form:form action="/add" method="post" modelAttribute="student">
<label for="firstName"> First name: </label>
<form:input type="text" id="firstName" path="firstName"/>
<p><font size="3" color="red"><form:errors path="firstName"/></font></p>
<p></p><label for="lastName">Last name:</label>
<form:input type="text" id="lastName" path="lastName"/>
<p><font size="3" color="red"><form:errors path="lastName"/></font></p>
<p><label for="age">Age:</label>
        <form:input type="text" id="age" value="" path="age"/>
<p><font size="3" color="red"><form:errors path="age"/></font></p>
<p><label for="course">Choose course:</label>
<div id="course">

    <c:forEach items="${courses}" var="course">
        <p><label for id="${course}">${course}</label>
            <form:radiobutton id="${course}" value="${course}" path="course"/></p>

    </c:forEach>
    <p><font size="3" color="red"><form:errors path="course"/></font></p>

</div>
<p><input type="submit" value="ADD">


    </form:form>
<p></p>
<p><a href="/allStudents">List students</a></p>
</body>
</html>
