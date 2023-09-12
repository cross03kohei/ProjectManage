jQuery(function ($) {
    $.ajax({
    url:"json",
    type: "GET",
    dataType: "json",
    success : function(data,status,xhr) {
        $("#chktest").val(data);
    },
    })
});