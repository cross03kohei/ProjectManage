var name;
jQuery(function ($) {
    $("td .btn").click(function () {
        var btn = $(this).closest('tr').children("td").children(".btn").val();
        $.ajax({
                   url:"edit",
                   type: "POST",
                   data: {
                        id : btn
                   },
                   dataType: "json",
                   timespan:1000,
                })
                .done(function(data) {
                    $("#id").val(data.id);
                    $("#name").text(data.name);
                    $("#item").val(data.item);
                    $("#manager").val(data.manager);
                    $("#quantity").val(data.quantity);
                    $("#amount").val(data.amount);
                    $("#progress").val(data.progress);

                    $("#endId").val(data.id);
                    $("#endProgress").val(data.progress);
                    $("#deleteId").val(data.id);
                    $("#endDeliveryDate").val(data.deliveryDate);
                })
    $("#endBtn").submit(function () {
        if($("endProgress").val() != "2" && $("#endCheck") == "true"){
            return false;
            alert("進行状態が完了じゃないと納品済みにできないよ");
        }
    });
    });


});