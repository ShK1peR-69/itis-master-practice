/*!
  * @author Igor Astafyev
  * Check data for correctness
  */

var submit_button = $('#submit-button');
var email = $('#email');
var fio = $('#full_name');
var first_password = $('#pass_one');
var second_password = $('#pass_two');

var download_btn = $('#download-btn');
var file_error = $('.ident-file');

email.change(function () {
    // Проверка e-mail пользователя
    var regEx = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/i;
    if (regEx.test(email.val().toString())) {
        event.stopPropagation();
        email.css('border', 'none');
        $("#email-error").html("");
        submit_button.prop('disabled', false);
        first_password.prop('disabled', false);
        second_password.prop('disabled', false);
        $.ajax({
            type: "POST",
            url: "/check-mail",
            data: {"email": email.val()},
            success: function (data) {
                if (data === 'ok') {
                    event.stopPropagation();
                    email.css('border', 'none');
                    $("#email-error").html("");
                    submit_button.prop('disabled', false);
                    first_password.prop('disabled', false);
                    second_password.prop('disabled', false);
                }
                if (data === 'error') {
                    event.preventDefault();
                    email.css('border', '0.15rem solid red');
                    $("#email-error").html("E-mail занят другим пользователем")
                        .css("display", "flex")
                        .css("color", "red");
                    submit_button.prop('disabled', true);
                    first_password.prop('disabled', true);
                    second_password.prop('disabled', true);
                }
            },
            error: function () {
                alert("На сервере произошла ошибка. Повторите попытку позже");
            }
        });
    } else {
        event.preventDefault();
        email.css('border', '0.15rem solid red');
        $("#email-error").html("Некорректный e-mail")
            .css("display", "flex")
            .css("color", "red");
        submit_button.prop('disabled', true);
        first_password.prop('disabled', true);
        second_password.prop('disabled', true);
    }
});

submit_button.on("click", function () {
        // Проверка длины ФИО
        console.log(fio.val())
        if (fio.val().length > 70) {
            event.preventDefault();
            fio.css('border', '0.15rem solid red');
            $("#name-error").html("Слишком длинное имя")
                .css("display", "flex")
                .css("color", "red");
            submit_button.prop('disabled', true);
        } else {
            event.stopPropagation();
            fio.css('border', 'none');
            $("#name-error").html("");
            submit_button.prop('disabled', false);
        }
        if (fio.val().length < 1) {
            event.preventDefault();
            fio.css('border', '0.15rem solid red');
            $("#name-error").html("Поле не заполнено")
                .css("display", "flex")
                .css("color", "red");
            submit_button.prop('disabled', true);
        } else {
            event.stopPropagation();
            fio.css('border', 'none');
            $("#name-error").html("");
            submit_button.prop('disabled', false);
        }
    }
);

$('#file-input').on('change', function () {
    var fileNamePathParts = $(this).val().split("\\");
    var fileName = fileNamePathParts[fileNamePathParts.length - 1];
    console.log(fileName);

    $.ajax({
        type: "POST",
        url: "/check-file",
        data: {
            "filename": fileName,
            "user_id": $("#check-file__user-id").attr('user_id')
        },
        success: function (data) {
            if (data === 'ok') {
                event.stopPropagation();
                file_error.css('display', 'none');
                download_btn.prop('disabled', false);
            }
            if (data === 'error') {
                event.preventDefault();
                file_error.css('display', 'flex');
                download_btn.prop('disabled', true);
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Повторите попытку позже");
        }
    });
});