jQuery(function ($) {
    $("#search_button").click(function () {
    console.log("hello");
    $.ajax({
        url:"json",
        type: "POST",
        data: $("form").serialize(),
        dataType: "json",
        timespan:1000,
    });
    });
});