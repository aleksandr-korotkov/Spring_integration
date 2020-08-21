<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Пользователи</title>
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        function crunchifyAjax() {
            $.ajax({
                url : 'customersData',
                success : function(data) {
                    $('#result').html(data);
                }
            });
        }
    </script>

    <script type="text/javascript">
        var intervalId = 0;
        intervalId = setInterval(crunchifyAjax, 3000);
    </script>
</head>
<body>
    <div id="result"></div>
</div>
</body>
</html>