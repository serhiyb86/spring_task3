<%@ page import="com.servlet.test.model.Courses" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Serh
  Date: 10/30/2021
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add student</title>
</head>
<body>
<h2>Enter the new student data and press "ADD"</h2>
<form action="/add" method="post">
    <label for="firstName"> First name: </label>
    <input name="first" type="text" id="firstName">
    <p></p><label for="lastName">Last name:</label>
    <input type="text" name="last" id="lastName">
    <p><label for="age">Age:</label>
        <input type="text" name="age" id="age">
<p><label for="course">Choose course:</label>
    <div id="course">

    <%
        String radioTags = "";

        for (Courses value : Courses.values()) {
            String name = value.name();
            radioTags = radioTags.concat("   <p><label for id=\""+name+"\"\n" +">"+name+"</label>"+
                    "    <input type=\"radio\" name=\"course\" id=\""+name+"\" value=\""+name+"\"></p>");
        }

    %>
    <%=
    radioTags
    %>



</div>
    <p><input type="submit" value="ADD">


</form>
<p></p>
<p><a href="/allStudents">List students</a> </p>
</body>
</html>
