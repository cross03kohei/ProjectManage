jQuery(function ($) {
    $("#search_button").click(function () {
        ajax();
    });
    });
});

function ajax(){
    $.ajax({
            url:"json",
            type: "POST",
            data: $("form").serialize(),
            dataType: "json",
            timespan:1000,
        })
        .done(function(data, textStatus, jqXHR){
            if(!data){
                alert("該当するデータはありませんでした");
                return;
            }
            for(var i = 0; i < data.length; i++){

            }
        }).fail(function(jqXHR, textStatus, errorThrown ) {
                      alert("エラーが発生し、データが取得できませんでした");
        });
}