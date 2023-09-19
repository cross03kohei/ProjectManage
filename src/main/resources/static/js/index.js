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
                    $("#name").val(data.client.name);
                    $("#clientId").val(data.client.id);
                    $("#progress").val(data.progress);
                    $("#item").val(data.itemCategory);
                    $("#manager").val(data.manager);

                    $("#endId").val(data.id);
                    $("endProgress").val(data.progress);
                })

    });
});