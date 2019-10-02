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

<div class="main">
    <div class="profile-header">Список пользователей</div>

    <div class="users-list">
        <#if all_users?? && all_users?has_content>
            <ol>
                <#list all_users as user>
                    <li>
                        <a href="user_${user.getId()}_docs">${user.getFio()}</a>
                    </li>
                </#list>
            </ol>
        <#else>
            <div class="empty-list">
                Список пользователей пока пуст.
            </div>
        </#if>
    </div>
</div>

</body>
</html>