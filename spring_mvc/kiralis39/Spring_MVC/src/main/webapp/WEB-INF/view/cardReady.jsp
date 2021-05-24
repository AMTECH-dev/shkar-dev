<!DOCTYPE HTML>
<html lang="ru">

<html>

    <head>
        <%@ page pageEncoding="utf-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <meta charset="UTF-8" />
        <link rel="icon" href="./res/media/favicon.ico">

        <title>Вывод данных</title>
    </head>


    <body>

        <label>${cardForm.fio}</label>
        <br>

        <label>Sex: ${cardForm.chBoxResult}</label>
        <br>

        <label>Notify type: ${cardForm.notifyType}</label>
        <br>

    </body>

</html>