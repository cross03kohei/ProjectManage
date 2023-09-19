jQuery(function ($) {
    $("#csv_btn").click(function () {
        var d = []
            var c = []
            $('table tr').each(function(i, e) {
              var dd = []
              var cc = []
              if(i === 0) {
                $(this).find('th').each(function(j, el) {
                  cc.push($(this).text())
                })
                c.push(cc)
              } else {
                $(this).find('td').each(function(j, el) {
                  dd.push($(this).text())
                })
                d.push(dd)
              }
            })
            var m = $.merge(c, d)
            console.log(m);
    })

})