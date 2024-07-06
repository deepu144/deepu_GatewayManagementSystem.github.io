<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error page</title>
    <style>
        body {
            background-image: url("https://cdn.dribbble.com/users/1078347/screenshots/2799566/oops.png");
            background-color: #cccccc;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-blend-mode: darken;
            background-position: center;
        }
    </style>
</head>

<body>

	<p><%= exception %></p>
</body>

</html>