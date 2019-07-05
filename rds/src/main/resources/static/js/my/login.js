initMenu();

function initMenu() {
    $.ajax({
        type: "get",
        url: "/initMenu",
        async: false,
        success: function (data) {
            if (data == null || data == undefined) {
                return
            }
            var menu = $('#menu')
            var li = "";
            $.each(data, function (e, t) {
                if (t.pId == 0) {
                    li += "<li class='treeview' id='" + t.id + "'>" +
                        "<a href='javascript:void(0)'><i class='"+t.icon+"'></i>" +
                        "<span>" + t.resName + "</span> " +
                        "<span class='pull-right-container'>" +
                        "<i class='fa fa-angle-left pull-right'></i>" +
                        "</span></a>"

                }
               var childrenUl = setChildren(t,data);
                li+=childrenUl+"</li>";
            })
            menu.append(li)
        }
    })
}

function setChildren(t,data) {
    var ul ="<ul class='treeview-menu'>";
    $.each(data, function (e, t1) {
        if (t.id==t1.pId) {
            var li ="<li>" +
                "<a class='myLeftMenu' target='menuFrame' data='" + t1.url + "'   resName='"+t1.resName+"' href='javascript:void(0)'>" +
                "<i class='fa fa-circle-o'></i>" + t1.resName + "</a>" +
                "</li>"
            ul += li;
        }

    });
    ul+="</ul>";
    return ul;
}