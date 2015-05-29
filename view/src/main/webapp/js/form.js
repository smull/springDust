$(document).ready(function () {

    $("#registerForm").validate({

        rules: {
            email: {
                required: true,
                email: true

            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 16
            },
            confirmPassword: {
                required: true,
                equalTo: "#passwordRegister"
            },
            name: {
                required: true,
                minlength: 4,
                maxlength: 16
            }
        },

        messages: {
            email: {
                required: "Это поле обязательно для заполнения",
                email: "Это не email"
            },
            password: {
                required: "Это поле обязательно для заполнения",
                minlength: "Пароль должен быть минимум 6 символа",
                maxlength: "Пароль должен быть максимум 16 символов"
            },
            confirmPassword: {
                required: "Это поле обязательно для заполнения",
                equalTo: "Не совпадает с паролем"
            },
            name: {
                required: "Это поле обязательно для заполнения",
                minlength: "Имя должно быть минимум 4 символа",
                maxlength: "Имя должно быть не более 16 символов"
            }
        }
    });
    $("#loginForm").validate({

        rules: {
            email: {
                required: true,
                email: true,
                maxlength: 35
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 16
            }
        },

        messages: {
            email: {
                required: "Это поле обязательно для заполнения",
                maxlength: "Максимальное число символо - 35",
                email: "Введите корректный E-mail адрес"
            },

            password: {
                required: "Это поле обязательно для заполнения",
                minlength: "Пароль должен быть минимум 6 символа",
                maxlength: "Пароль должен быть максимум 16 символов"
            }
        }


    });
    $('a.flipper').click(function () {
        $('.flip').toggleClass('flipped');
    });

    $("#emailRegister").focusout(function () {
            //  $("#emailRegister").focusout(function () {
            if (($("#emailRegister").valid()) == true) {
                $.ajax({
                    type: "POST",
                    url: "/user/checkEmail",
                    data: {
                        email: $("#emailRegister").val()
                    },

                    success: function (data) {
                        $("#spanRegister").html(data);
                    }
                })
            }
            //  });
        }
    )

    $("#buttonRegister").click(function () {
        if (($("#registerForm").valid()) == true) {
            $.ajax({
                type: "POST",
                url: "/user/create",
                data: {
                    email: $("#emailRegister").val(),
                    password: $("#passwordRegister").val(),
                    name: $("#name").val()
                },


                success: function (data) {
                    if (data == "") {
                        window.location.href = "/diagram/pieChart";
                    } else {
                        $("#spanRegister").html(data);
                    }
                }

            })
        }
    });

    $("#buttonLogin").click(function () {
        if (($("#loginForm").valid()) == true) {
            $.ajax({
                type: "POST",
                url: "/user/signIn",
                data: {email: $("#emailLogin").val(), password: $("#passwordLogin").val()},
                success: function (data) {
                    if (data == "") {
                        window.location.href = "/diagram/pieChart";
                    } else {
                        $("#spanLogin").html(data);
                    }
                }
            })
        }
    });

    $(".input").focus(function () {
        $("#spanLogin").html("");
        $("#spanRegister").html("");
    });


})
;
