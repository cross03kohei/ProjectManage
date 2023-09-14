jQuery(function ($) {
    $("#search_button").click(function () {
    $.ajax({
                url:"json",
                type: "POST",
                data: $("form").serialize(),
                dataType: "json",
                timespan:1000,
            })
            .done(function(data, textStatus, jqXHR){
                console.log(data[0].name);
                for(var i = 0; i < data.length; i++){
                $("<tr><td>" + data[i].name +
                "</td><td><button value=" + data[i].id + "決定</button></td></tr>")
                .appendTo("#search_client");
                }
            }).fail(function(jqXHR, textStatus, errorThrown ) {
                          alert("エラーが発生し、データが取得できませんでした");
            });
    });
});