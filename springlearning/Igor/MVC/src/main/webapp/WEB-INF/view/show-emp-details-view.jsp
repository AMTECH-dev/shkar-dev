<%--
  Created by IntelliJ IDEA.
  User: shkar-1
  Date: 12.05.2021
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Your name:${human.name}</h2>
<h2> Your lastname:${human.lastname}</h2>
<h2> Your age:${human.age}</h2>
<h2> Please check your profession:${human.selectProfession}</h2>
<br>
you level: ${human.radioButton}
<br>
Your Chose: ${human.choiceCheckbox}

</body>
</html>
