jQuery(function () {
    $("#edit_button").click(function (){
        $.ajax({
            url:"check",
            type:"POST",
            data: $("#check_form").serialize(),
            dataType: "json",
            timespan:1000,
        })
        .done(function (data) {
            alert(data);
        })
    })
})