<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="images/icon.jpg" type="image/x-icon">
    <title>Manager</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<#include "temps/header.ftl"/>

<div class="main-form">
    <form action="/registration" method="post">
        <#if msg?? && msg?has_content>
            <div class="error-msg">Пароли не совпадают</div>
        </#if>
        <div class="main-container">
            <div class="form-group">
                <label for="full_name">Полное имя:</label>
                <input type="text" class="form-control" id="full_name"
                       name="fio" maxlength="70" placeholder="ФИО" required>
                <small id="name-error" class="form-text text-muted"></small>
            </div>
            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                       name="email" placeholder="example@mail.ru" required>
                <small id="email-error" class="form-text text-muted"></small>
            </div>
            <div class="form-group">
                <label for="pass_one">Пароль:</label>
                <input type="password" class="form-control" id="pass_one"
                       name="pass_one" placeholder="Введите пароль" required>
                <small id="pass1-error" class="form-text text-muted"></small>
            </div>
            <div class="form-group">
                <label for="pass_two">Подтвердите пароль:</label>
                <input type="password" class="form-control" id="pass_two"
                       name="pass_two" placeholder="Введите пароль" required>
                <small id="pass2-error" class="form-text text-muted"></small>
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-primary" id="submit-button">Зарегистрироваться</button>
        </div>
    </form>
    <a href="/login">
        <div class="btn btn-primary login-btn">Вход</div>
    </a>
</div>

<script type="text/javascript" src="js/checkData.js"></script>
<script type="text/javascript" src="js/checkTwoPasswords.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

</body>
</html>