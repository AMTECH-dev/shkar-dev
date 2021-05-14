<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: shkar-1
  Date: 12.05.2021
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<h2>Please, enter your name</h2>
<form action="/showDetails" method="get">
    <input type="text" name ="Name"
           placeholder="write your name">
    <input type="submit">

</form>--%>


<form:form action="showDetails" modelAttribute="human">
    Name <form:input path="name"/>
    <br>
    <br>
    LastName <form:input path="lastname"/>
    <br>
    <br>
    Age <form:input path="age"/>
    <br>
    <br>

   Profession <form:select path="selectProfession">
    <form:option value="ThisIT" label="IT"/>
    <form:option value="ThisHR" label="HR"/>
    <form:option value="ThisMainBoss" label="MainBoss"/>
</form:select>

    <br>
    <br>
    How a You?
     Loss<form:radiobutton path="radioButton" value="L "/>
     Middle<form:radiobutton path="radioButton" value="M "/>
     Boss<form:radiobutton path="radioButton" value="B "/>


    <br>
    <br>
    Yes <form:checkbox path="choiceCheckbox" value="Yes"/>
   No <form:checkbox path="choiceCheckbox" value="NO"/>
    <input type="submit" value="OK">


</form:form>
</body>
</html>
