jQuery(function ($) {
    $("#search_button").click(function () {
    $("#clientTable tbody tr").not(":first").empty(); //最初の要素以外を削除する
    $.ajax({
                url:"json",
                type: "POST",
                data: $("form").serialize(),
                dataType: "json",
                timespan:1000,
            })
            .done(function(data, textStatus, jqXHR){
                for(var i = 0; i < data.length; i++){
                   $tr = $("#clientTable tbody tr:first-child").clone(true)
                   $tr.find(".clientName").text(data[i].name);
                   $tr.find(".btn").val(data[i].id);
                   $tr.appendTo("#clientTable tbody");
                   $("#clientTable tbody tr:last-child").css("display", "table-row");
                }
            }).fail(function(jqXHR, textStatus, errorThrown ) {
                          alert("エラーが発生し、データが取得できませんでした");
            });
    });
    $(".decision").click(function () {
        $("#clientId").val($(this).val());
        var name = $(this).parent().prev().text();
        $("#clientName").text("顧客名：" + name);
        $("#cancel").trigger("click");
        $(".newClient").text("");
    })
    $(".plusClient").click(function () {
        if($("#clientId").val() == ""){
            $("#clientNewName").get(0).type = "text";
            $("#-btn").css("display","block");
        }else{
            alert("顧客は選択済みだよ");
        }
    });
    $("#searchButton").click(function () {
        if($("#clientNewName").val() != ""){
            alert("顧客名を入力してるよ");
        }
    })
    $("#-btn").click(function () {
        $("#clientNewName").get(0).type = "hidden";
        $("#clientNewName").val("");
        $("#-btn").css("display","none");
    })
});