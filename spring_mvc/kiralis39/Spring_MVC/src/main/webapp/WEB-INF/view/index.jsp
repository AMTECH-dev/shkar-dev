<!DOCTYPE html>
<html>

    <head>
        <title>titleText</title>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" >
        <meta name="titleText" content="Index html page">
        <!-- <meta http-equiv="refresh" content="5; URL=http://www.htmlbook.ru"> -->

        <style>
            * {
                margin: 0;
                padding: 0;
            }

            div {
                background: teal;
                width: auto;
                height: auto;
                padding: 1%;
                alignment: center;
            }

            h2 {
                font-family: Georgia, Geneva, monospace;
                text-align: center;
                color: bisque;

            }

            #headerMenu {
                border: 1px double black;
                display: inline-block;
                margin-bottom: 1%;
            }

            #headerMenu ul {
                color: azure;
                position: relative;
                text-align: center;
            }

            #headerMenu li {
                list-style: none;
                float: left;
            }

            #headerMenu a {
                color: white;
                background: black;
                text-decoration: none;
                padding: 1em;
                margin: 0.1em;
                outline: none;
                display: block;
                border-left: 1px solid #336;
                border-top: 1px solid #336;
            }

            #headerMenu a:after{
                background: radial-gradient(white 0%, white 70%, rgba(255,255,255,0) 70%, rgba(255,255,255,0) 100%) 0 -10px;
            }

            #headerMenu a:hover{
                background: black;
                font-weight:bold;
            }
        </style>
    </head>


    <body bgcolor="#bbf">

        <div>

            <div id="headerMenu">
                <ul class="headerMenuUL">
                    <li><a href="/MVC/">Home</a></li>
                    <li><a href="/MVC/card">Card</a></li>
                    <li><a href="#">Empty</a></li>
                    <li><a href="#">Empty</a></li>
                    <li><a href="/MVC/errPage">Error page</a></li>
                    <li><a href="#">Empty</a></li>
                </ul>
            </div>

            <H2>INDEX PAGE HERE</H2>

        </div>

    </body>

</html>