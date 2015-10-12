<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>Bookshelf</title>

    <link rel="stylesheet" href="/resources/my.css" type="text/css"/>

    <!-- JQuery CSS -->
    <link rel="stylesheet" href="/resources/jquery-ui-1.11.4/jquery-ui.min.css" type="text/css"/>
    <!-- jqGrid CSS -->
    <link rel="stylesheet" href="/resources/grid/css/ui.jqgrid.css" type="text/css"/>

    <!-- The actual JQuery code -->
    <script type="text/javascript" src="/resources/jquery-1.11.3.min.js"></script>
    <!-- The JQuery UI code -->
    <script type="text/javascript" src="/resources/jquery-ui-1.11.4/jquery-ui.min.js"></script>
    <!-- The jqGrid language file code-->
    <script type="text/javascript" src="/resources/grid/js/i18n/grid.locale-en.js"></script>
    <!-- The atual jqGrid code -->
    <script type="text/javascript" src="/resources/grid/js/jquery.jqGrid.src.js"></script>

    <script type="text/javascript" src="/resources/my/main.js"></script>
</head>
<body>

<div id="rounded">

    <h2>Bookshelf</h2>

    <div id="main" class="container">
        <ul id="navigation">
            <li><a href="#books">Books</a></li>
            <li><a href="#users">Users</a></li>
            <li><a href="#rating">Rating</a></li>
            <li><a href="#login">Login</a></li>
        </ul>

        <div class="clear"></div>

        <div id="pageContent">
            Hello, this is the Bookshelf!
        </div>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
