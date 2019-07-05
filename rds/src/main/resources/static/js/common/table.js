$.fn.initTableData = function (options) {
    defaultOptions = {
        resp: {},
        url: "",
        data: {
            page: 0,
            size: 10
        }
    }
    defaultOptions = $.extend(defaultOptions, options || {});
    var _t;
    if (defaultOptions.content==undefined) {
        //if (defaultOptions.resp)
        _t = initQuery(defaultOptions)
    }else {
        _t=options;
    }
    var _ths = $(this).find("th");
    var tbody = $(this).find("tbody");
    if (_t != null || _t != undefined) {
        if (_t.content == null || _t.content.length == 0) {
            $(tbody).html("<tr><td colspan='" + _ths.length + "'>暂无查询数据。。。</td></tr>")
            return
        }
        var tr = "";
        $.each(_t.content, function (i, e) {
            tr += "<tr>"
            $.each(_ths, function (l, th) {
                var td = "";
                var thFiled = $(th).attr("field");
                var child = $(th).attr("child");
                var _field = thFiled.match(/\{\w*.*\d*\}/g);
                if (_field != null || _field != undefined) {
                    $.each(_field, function (i, d) {
                        var _field1 = d.substr(1, d.length - 2);
                        var a = _field1.match(/\(\w*.*\d*\)/g)
                        var a1 = a[0].substr(1, a[0].length - 2)
                        var fn = _field1.substr(0, _field1.indexOf("("))
                        var func;
                       if(typeof e[a1] === "object"  && child  !== null && child !== undefined){
                           func= fn + "(" + e[a1][child] + ")";
                       }else {
                           func= fn + "(" + e[a1] + ")";
                       }

                        var _v = eval(func);
                        if (_v != null && _v != undefined) {
                            td = "<td>" + _v + "</td>"
                        }
                    })
                } else if (thFiled == 'checkbox') {
                    td = "<td><div><input type='checkbox' value='" + JSON.stringify(e) + "'/></div></td>";
                } else {
                    if(typeof e[thFiled] === "object" && child  !== null && child !== undefined){
                        td =  "<td>" + e[thFiled][child] + "</td>";
                    }else {
                        td = "<td>" + e[thFiled] + "</td>"
                    }
                }
                tr += td;
            })
            tr += "</tr>"
        })
        tbody.html(tr);
        if (defaultOptions.checkId != undefined) {
            var trs = $('#dataTable tbody').find("tr").find("input");
            $.each(trs, function (i, t) {
                var _v = $(this).val()
                _v = JSON.parse(_v)
                if (_v.id == defaultOptions.checkId) {
                    $(this).prop('checked', 'checked')
                }
            })
        }
        $(tbody).find("tr").click(function () {
            var check = $(this).find("input[type='checkbox']");
            if(check){
                var flag = check[0].checked;
                if(flag){
                    check[0].checked = false;
                }else{
                    check[0].checked = true;
                }
            }
        })
        $("input[type='checkbox']").click(function(e){
            e.stopPropagation();
        });
        var ch = $(this).find("thead").find("input")
        ch.click(function () {
            if ($(this).prop('checked')) {
                $(tbody).find('input').prop('checked', true);
            } else {
                $(tbody).find('input').prop('checked', false);
            }
        })
    }
    if (_t.totalPages<2){
        $('#pageDiv').html('')
        return
    }
    var html = "<div class='col-sm-5'>" +
        "<div class='dataTables_info'>" +
        "</div>" +
        "</div>" +
        "<div class='col-sm-7'>" +
        "<div class='dataTables_paginate paging_simple_numbers'>" +
        "<ul class='pagination'>";

    html += "<li class='paginate_button'>" +
        "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' onclick='goPage(0,defaultOptions)'>首 页</a></li>";

    if (_t.number == 0) {
        var num = _t.page - 1;
        html += "<li class='paginate_button disabled'>" +
            "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0'>上一页</a>"
    } else {
        html += "<li class='paginate_button '>" +
            "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' onclick='goPage(" + (_t.page - 1) +","+ JSON.stringify(defaultOptions)+")'>上一页</a>"
    }

    html += "</li>";
    if (_t.number < 4) {
        for (var i = 1; i <= 5 && i<=_t.totalPages; i++) {
            if (i - 1 == _t.number) {
                html += "<li class='paginate_button active'>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            } else {
                html += "<li class='paginate_button '>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            }
        }
    } else {
        for (var i = _t.number - 2; i < _t.number; i++) {
            if (i == _t.number + 1) {
                html += "<li class='paginate_button active'>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            } else {
                html += "<li class='paginate_button '>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            }

        }
        for (var i = _t.number; i < _t.number + 5 && i <= _t.totalPages; i++) {
            if (i == _t.number + 1) {
                html += "<li class='paginate_button active'>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            } else {
                html += "<li class='paginate_button '>" +
                    "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' " +
                    "onclick='goPage(" + (i - 1) + "," + JSON.stringify(defaultOptions) + ")'>" + (i) + "</a></li>";
            }
        }
    }
    if (_t.number == _t.totalPages-1) {
        html += "<li class='paginate_button disabled'>" +
            "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' >下一页</a></li>";

    } else {
        html += "<li class='paginate_button '>" +
            "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' onclick='goPage(" + (_t.number + 1)  + "," +  JSON.stringify(defaultOptions) + ")'>下一页</a></li>";
    }
    html += "<li class='paginate_button '>" +
        "<a href='javascript:void(0)' data-dt-idx='0' tabindex='0' onclick='goPage(" + (_t.totalPages - 1) + "," + JSON.stringify(defaultOptions) + ")'>末 页</a></li>";
    html +=
        "</ul>" +
        "</div>" +
        "</div>";
    $('#pageDiv').html(html)

}

function initQuery(option) {
    var a;
    $.ajax({
      type:"get",
        dataType:"json",
        data:option.data,
        url :option.url,
        async:false,
        success:function(resp){
          a =resp;
        }
})
    return a
}

function goPage(number, option) {
    // var data =$.extend(option.data,{"page":number,"url":option.url,"size":option.size,"checkId":option.checkId});
    var data =$.extend(option.data,{"page":number,"size":option.size,"checkId":option.checkId});
    // console.log("data",data);
    $('#dataTable').initTableData({"url": option.url, "data": data});
    // $.ajax({
    //     type:"get",
    //     dataType:"json",
    //     data:data,
    //     url:option.url,
    //     async:false,
    //     success:function (res) {
    //         var resp = $.extend(res,data);
    //         console.log("resp",resp);
    //         $('#dataTable').initTableData(resp);
    //
    //     }
    // })


}