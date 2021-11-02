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
<html>
<head>
    <title>List students</title>
</head>
<body>
<h2>List of students</h2>


<%--        <c:forEach items="${courses}" var="course" >--%>
<%--            <p><label for id="${course}" >${course}</label>--%>
<%--                <input type="radio" name="course" id="${course}" value="${course}"></p>--%>

<%--        </c:forEach>--%>
<table>
    <tr>
        <th>ID:</th>
        <th>First name:</th>
        <th>Last name:</th>
        <th>Age:</th>
        <th>Course:</th>
    </tr>
<c:forEach items="${students}" var="student">
    <tr>
        <td>${student.id}</td>
        <td>${student.firstName}</td>
        <td>${student.lastName}</td>
        <td>${student.age}</td>
        <td>${student.course}</td>

    </tr>

</c:forEach>


</table>






</form>
<p></p>
<p><a href="/add">ADD Student to a particular course.</a> </p>
</body>
</html>
