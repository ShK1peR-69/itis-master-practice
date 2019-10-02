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

<header>
    <#if user??>
        <a href="/profile"><h2>FILE MANAGER</h2></a>
    <#else>
        <a href="/"><h2>FILE MANAGER</h2></a>
    </#if>
</header>

<div class="main">
    <#if user??>
        <div class="profile-header">Привет, ${user.getFio()} !
            <#if user.getRole()==1>
                (<a href="/users_list" class="admin-link">admin</a>)
            </#if>
        </div>

        <#if files?? && files?has_content>
            <ol class="files-list">
                <#list files as file>
                    <li>${file.getName()} &nbsp;(<a href="${file.getPath()}"
                                                    class="view-href" target="_blank">открыть</a> |
                        <a href="/delete-${user.getId()}-file-${file.getName()}" class="delete-href">удалить</a>)
                    </li>
                </#list>
            </ol>
        <#else>
            <div class="files-list__empty">Список файлов пуст...</div>
        </#if>
        <form class="file-download" enctype="multipart/form-data" action="/download-file-${user.getId()}" method="post">
            <span class="download-msg">Добавить файл:</span>
            <input class="download-input" type="file" name="file" id="file-input"
                   required accept="application/pdf,
               application/msword,
               application/vnd.openxmlformats-officedocument.wordprocessingml.document,
               application/pdf,application/msword,
               application/vnd.ms-excel,
               application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
               .doc, .docx, .pdf, .exel">
            <span class="ident-file">Файл с таким именем уже существует</span>
            <button type="submit" class="btn btn-info" id="download-btn">Загрузить</button>
            <div class="d-none" id="check-file__user-id" user_id="${user.getId()}"></div>
        </form>
    <#else>
        <span>Пожалуйста, авторизуйтесь!</span><br/>
        <a href="/login">
            <div class="btn btn-primary login-btn">Вход</div>
        </a>
    </#if>
</div>

<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/checkData.js"></script>

</body>
</html>