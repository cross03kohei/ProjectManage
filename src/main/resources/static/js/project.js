jQuery(function ($) {

    $(".clientTr").empty();
    $("#search_button").click(function () {
    $.ajax({
                url:"json",
                type: "POST",
                data: $("form").serialize(),
                dataType: "json",
                timespan:1000,
            })
            .done(function(data, textStatus, jqXHR){
                for(var i = 0; i < data.length; i++){
                   $obj = $(".clientTr").clone();
                   $obj.find(".clientName").text(data[i].name);
                   $obj.find(".btn").val(data[i].id)
                   $("#clientTbody").appendTo($obj);
                }
            }).fail(function(jqXHR, textStatus, errorThrown ) {
                          alert("エラーが発生し、データが取得できませんでした");
            });
    });
});