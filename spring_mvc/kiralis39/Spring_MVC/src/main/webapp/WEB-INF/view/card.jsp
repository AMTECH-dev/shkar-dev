<!DOCTYPE HTML>
<html lang="ru">

    <head>
        <%@ page pageEncoding="utf-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <meta charset="UTF-8"/>
        <link rel="icon" href="./res/media/favicon.ico">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <link href="./res/css/cardStyle.css" rel="stylesheet" type="text/css">

        <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script defer src="https://www.gstatic.com/charts/loader.js"></script>
        <script defer src="./res/js/debugger.js"></script>

        <title>Ввод данных карточки</title>
    </head>


    <body class="milestone__color">
        <header>
            <div class="upLabelDiv milestone__color mb-4">
                <label><h1> &lt;Task card #N (decription, date, soretomo)&gt; </h1></label>
            </div>
        </header>

        <div class="baseDiv">

            <div class="dataFormDiv">

                <form:form name="passportForm" class="passportForm" id="passportForm"
                      action="${pageContext.request.contextPath}/cardReady" method="post" modelAttribute="cardForm">

                    <!-- северная панель блоков: -->
                    <div class="northBlockDiv">

                        <div class="upLeftFormDiv">
                            <label>&lt;Data&gt;</label>

                            <!-- строка ФИО: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Создатель задачи или типа того:</label>
                                </div>

                                <div class="fieldInput">
                                    <form:input path="fio" class="fioField" placeholder="ФИО" pattern="[А-Яа-я]+ [А-Яа-я]+ [А-Яа-я]+" />
                                    <form:errors path="fio" />
                                </div>
                            </div>

                            <!-- строка паспорт: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Какой-то номер (трекер):</label>
                                </div>

                                <div class="fieldInput">
                                    <INPUT TYPE="tel" name="passField" class="passField" placeholder="НОМЕР" maxlength="6"
                                           value="1234567"
                                           required>
                                </div>
                            </div>

                            <!-- Дата рождения: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Дата чего-либо (создания?):</label>
                                </div>

                                <div class="fieldInput">
                                    <INPUT TYPE="date" name="birthField" class="birthField" required>
                                </div>
                            </div>

                            <!-- Адрес проживания: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Любой текст:</label>
                                </div>

                                <div class="fieldInput">
                                    <INPUT TYPE="text" name="addrField" class="addrField" placeholder="Город, Улица, дом/корп/кв"
                                           value="Москва, Красная, 1"
                                           required>
                                </div>
                            </div>

                            <!-- строка Phone mobile: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Номер телефона (моб):</label>
                                </div>

                                <div class="fieldInput">
                                    <INPUT TYPE="tel" placeholder="+7 (000) 00-00-00" name="phoneFieldBody" class="phoneFieldBody"
                                           pattern="(\+7)\([0-9]{3}\)(\s?)[0-9]{3}-?[0-9]{2}-?[0-9]{2}" maxlength="15"
                                           value="+7(999)0000000"
                                           required>
                                </div>
                            </div>

                            <!-- строка Mail: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>E-mail:</label>
                                </div>

                                <div class="fieldInput">
                                    <INPUT TYPE="email" name="mailFieldBody" class="mailFieldBody" placeholder="@">
                                </div>
                            </div>

                            <!-- строка Mail sends type: -->
                            <div class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Тип рассылки оповещений:</label>
                                </div>

                                <div class="fieldInput">
                                    <form:radiobutton path="notifyType" value="to lead" class="phoneHFieldBody" label=" to lead." title="tooltip" />
                                    <form:radiobutton path="notifyType" value="to slave" class="phoneHFieldBody"  label=" to slave." title="tooltip" />
                                    <form:radiobutton path="notifyType" value="to all" class="phoneHFieldBody"  label=" to all." title="tooltip" />
                                </div>
                            </div>

                            <!-- выбор пола: -->
                            <DIV class="data">
                                <div class="fieldLabel">
                                    <img src="" width="32" height="32" alt="nameIcon" align="top" name="dataLineIcon">
                                    <label>Выбор из любого списка:</label>
                                </div>

                                <div class="fieldInput">
                                    <form:select path="chBoxResult" class="sexFieldBody">
                                        <form:options items="${cardForm.chBoxResults}" />
                                    </form:select>
                                </div>
                            </DIV>
                        </div>

                        <div class="upRightFormDiv">
                            <label>&lt;Comment&gt;</label>

                            <textarea class="commentArea" name="commentArea" placeholder="Comment placed here..."></textarea>
                        </div>
                    </div>

                    <!-- центральная панель фильтров: -->
                    <div class="midFilterPane">

                        <div class="checkDiv01">
                            <input type="checkbox" class="filterOption01" id="filterOption01" name="filterOption01" value="Option_01" checked> Gant`s sort mode
                        </div>

                        <div class="checkDiv02">
                            <input type="checkbox" class="filterOption02" id="filterOption02" name="filterOption02" value="Option_02" > Option_02
                        </div>

                        <div class="checkDiv03">
                            <input type="checkbox" class="filterOption03" id="filterOption03" name="filterOption03" value="Option_03" > Option_03
                        </div>

                        <div class="checkDiv04">
                            <input type="checkbox" class="filterOption04" id="filterOption04" name="filterOption04" value="Option_04" > Option_04
                        </div>

                        <div id="debug"><label>deb:</label></div>
                    </div>

                    <!-- центральная панель вехов: -->
                    <div class="milesLine">

                        <!-- вехи: -->
                        <div class="milestoneDiv"></div>

                        <%-- ресайзер--%>
                        <div class="resizeSpan" id="resizeSpan"></div>

                        <!-- история: -->
                        <textarea class="historyArea" name="historyArea" placeholder="The card history..."></textarea>

                    </div>

                    <!-- южная панель кнопок: -->
                    <div class="buttonsLineDiv">
                        <input type="reset" value="Сбросить" id="btnReset">
                        <input type="submit" value="Отправить" id="btnSend">
                        <input type="button" value="Отменить" id="btnCancel">
                    </div>

                </form:form>
            </div>

        </div>

        <footer>

        </footer>

        <script defer src="./res/js/vars.js"></script>
        <script defer src="./res/js/gantMile.js"></script>
        <script defer src="./res/js/listeners.js"></script>
    </body>
</html>