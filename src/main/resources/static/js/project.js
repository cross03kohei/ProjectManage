jQuery(function ($) {
    $("#search_button").click(function () {
    ajax();
    });
    $("#client_text").keypress(function($) {
      if ($.code == Enter) {
        $('#search_button').trigger("click");
      }
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
             $('#client_table').remove();
            for(var i = 0; i < data.length; i++){
                $("<td>" + data[i].name +"</td>").appendTo("#client_tr");
                $("<td> <button value=" + data[i].id +">決定</button></td>").appendTo("#client_tr");
                $("</tr>").appendTo("#client_table");
            }
        }).fail(function(jqXHR, textStatus, errorThrown ) {
                      alert("エラーが発生し、データが取得できませんでした");
        });
}