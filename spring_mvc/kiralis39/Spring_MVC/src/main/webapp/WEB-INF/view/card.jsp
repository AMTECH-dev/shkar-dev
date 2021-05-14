<html lang="ru">

<head>
    <%@ page pageEncoding="utf-8" %>
    <meta charset="UTF-8"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link href="./res/cardStyle.css" rel="stylesheet" type="text/css">
    <title>Ввод паспортных данных</title>
</head>


<body class="milestone__color">
<div class="baseDiv">

    <div class="dataFormDiv">

        <div class="upLabelDiv milestone__color mb-4">
            <label><h1> &lt;Task card #N (decription, date, soretomo)&gt; </h1></label>
        </div>

        <form name="passportForm" class="passportForm" id="passportForm" action="/passport" method="post" accept-charset="UTF-8">

            <!-- северная панель блоков: -->
            <div class="northBlockDiv">
                <div class="upLeftFormDiv">
                    <div class="dataLabel">
                        <label>&lt;Data&gt;</label>
                    </div>

                    <!-- строка ФИО: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Фамилия Имя Отчество:</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="text" name="fioField" class="fioField" placeholder="ФИО" pattern="[А-Яа-я]+ [А-Яа-я]+ [А-Яа-я]+" required>
                        </div>
                    </div>

                    <!-- строка паспорт: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Серия и номер паспорта:</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="tel" name="passField" class="passField" placeholder="НОМЕР" maxlength="6" required>
                        </div>
                    </div>

                    <!-- Дата рождения: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Дата вашего рождения:</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="date" name="birthField" class="birthField" required>
                        </div>
                    </div>

                    <!-- Адрес проживания: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Адрес проживания:</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="text" name="addrField" class="addrField" placeholder="Город, Улица, дом/корп/кв" required>
                        </div>
                    </div>

                    <!-- строка Phone mobile: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Номер телефона (моб):</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="tel" name="phoneFieldBody" class="phoneFieldBody" pattern="\([0-9]{3}\)(\s?)[0-9]{3}-?[0-9]{2}-?[0-9]{2}" maxlength="15" value="()" required>
                        </div>
                    </div>

                    <!-- строка Phone home: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Номер телефона (дом):</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="tel" name="phoneHFieldBody" class="phoneHFieldBody" minlength="5" maxlength="8" placeholder="##-##-##">
                        </div>
                    </div>

                    <!-- строка Mail: -->
                    <div class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Адрес эл. почты:</label>
                        </div>

                        <div class="fieldInput">
                            <INPUT TYPE="email" name="mailFieldBody" class="mailFieldBody" value="@">
                        </div>
                    </div>

                    <!-- выбор пола: -->
                    <DIV class="data">
                        <div class="fieldLabel">
                            <img src="./res/nameIcon.png" width="32" height="32" alt="nameIcon" align="top">
                            <label>Укажите свой пол:</label>
                        </div>

                        <div class="fieldInput">
                            <select name="sexComboBox" class="sexFieldBody" required>
                                <option value="male">Мужской</option>
                                <option value="female">Женский</option>
                            </select>
                        </div>
                    </DIV>
                </div>

                <div class="upRightFormDiv">
                    <div class="areaLabel">
                        <label>&lt;Comment&gt;</label>
                    </div>

                    <textarea class="commentArea" name="commentArea" placeholder="Comment placed here..." required></textarea>
                </div>
            </div>

            <!-- центральная панель вехов: -->
            <div class="milestonesHistoryLine">
                <!-- вехи: -->
                <div class="milestones">
                    <label>&lt;MileStones&gt;</label>
                </div>

                <!-- история: -->
                <div class="history">
                    <label>&lt;History&gt;</label>
                </div>
            </div>

            <!-- южная панель кнопок: -->
            <div class="buttonsLineDiv">
                <input type="reset" value="Сбросить" id="btnReset">
                <input type="submit" value="Отправить" id="btnSend">
                <input type="button" value="Отменить" id="btnCancel">
            </div>

        </form>
    </div>

</div>
</body>

</html>