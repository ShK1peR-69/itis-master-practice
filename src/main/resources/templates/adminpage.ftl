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
    <div class="profile-header">
        Пользователь "<a href="user_${cur_user.getId()}_docs"><i>${cur_user.getFio()}</i></a>"
    </div>

    <#if user_files?? && user_files?has_content>
        <table>
            <thead>
            <tr>
                <th>№</th>
                <th>Имя файла</th>
                <th>Посмотреть</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody>
            <#list user_files as file>
                <tr>
                    <td>${file_index + 1}</td>
                    <td>${file.getName()}</td>
                    <td><a href="${file.getPath()}" target="_blank">посмотреть</a></td>
                    <td><a href="/delete-${cur_user.getId()}-filename-${file.getName()}"
                           class="delete-doc-btn">
                            <img src="images/delete.png" alt="Удалить">
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        Список файлов данного пользователя пуст
    </#if>
</div>

</body>
</html>