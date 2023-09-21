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
                    $("#name").text("顧客名：" + data.name);
                    $("#item").val(data.item);
                    $("#manager").val(data.manager);
                    $("#quantity").val(data.quantity);
                    $("#amount").val(data.amount);
                    $("#progress").val(data.progress);

                    $("#endId").val(data.id);
                    $("endProgress").val(data.progress);
                })

    });
});