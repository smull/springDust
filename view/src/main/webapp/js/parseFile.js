$(document).ready(function () {


    $('.collection tr').each(function() {
        var that = $(this);
        $(this).dblclick(function() {
            var nameFile = $(this).text();

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/user/dealCreate",
                data: {nameFile: nameFile.trim()},
                success: function (data) {
                }
            });
            $('#dialog_window_1').dialog();
        });
    });


});