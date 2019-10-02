<#--<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>-->

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

<form action="/login" method="post" class="main-form">
    <#if msg?? && msg?has_content>
        <div class="error-msg">Пароли не совпадают</div>
    </#if>
    <#if RequestParameters.error??>
        <div class="error-msg">Неправильный e-mail или пароль</div>
    </#if>
    <div class="main-container">
        <div class="form-group">
            <label for="login-email">Email:</label>
            <input type="text" class="form-control" id="login-email"
                   name="login-email" maxlength="70" placeholder="example@mail.ru" required>
            <small id="login-email-error" class="form-text text-muted"></small>
        </div>
        <div class="form-group">
            <label for="login-pass">Пароль:</label>
            <input type="password" class="form-control" id="login-pass"
                   name="login-pass" placeholder="Введите пароль" required>
            <small id="login-pass-error" class="form-text text-muted"></small>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-success login-btn-submit" id="submit-button">Войти</button>
    </div>
    <div class="to-main-href">
        <a href="/">Главная</a>
    </div>
</form>

<script type="text/javascript" src="js/checkData.js"></script>
<script type="text/javascript" src="js/checkTwoPasswords.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

</body>
</html>