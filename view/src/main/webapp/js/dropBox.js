$(document).ready(function () {

    var nameFile;

    $('.collection tr').each(function() {
        var that = $(this);
        $(this).dblclick(function() {
             var chosenTd = $(this).text();
             var s = chosenTd.trim();
             var nameTd = s.split("\n");
             //alert(nameTd[0]);
             nameFile = nameTd[0];

             $.ajax({
                 type: "POST",
                 dataType: "json",
                 url: "/user/downloadFile",
                 data: {nameFile: nameFile},
                 success: function (data) {
                 }
             });
            $('#dialog_window_1').dialog();
        });
    });


});