jQuery(function ($) {
    $("#edit_button").click(function () {
        $("#client_edit").css("display","block");

        $("#edit_cancel").click(function () {
            $("#client_edit").css("display","none");
        });
    });
    $("#delete_button").click(function () {
        $("#client_delete").css("display","block");

        $("#delete_cancel").click(function () {
            $("#client_delete").css("display","none");
        });
    });
});