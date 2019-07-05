//自定义下拉框集成ztree
//机构树
/*传入参数tree:用于承接ztree的ul的id;treeDivId:用于承接ztree的div的id;inputId:接受显示选中文字的input的id;
	     inputHideId:接受选中文字对应的id的input的id（一个hidden的input）;treeDataList:形成树形需要的数据数组；
*/
function createTree(d) {
    var tree = [];
    for (var i=0; i< d.length; i++) {
        var node = createNode(d[i]);
        tree.push(node);
    }
    return tree;
}

function createNode(d) {
    var id = d['id'];
    var pId = d['pId'];
    var orgFullName = d['orgFullName'];
    var child = d['child'];
    var level = d['level'];
    var org_id = d['org_id'];

    var node = {
        open: true,
        id: id,
        name: orgFullName,
        pId: pId,
        leve: level,
        org_id: org_id
    };

    if (child != null) {
        var length = child.length;
        if (length > 0) {
            var children = [];
            for (var i = 0; i < length; i++) {
                children[i] = createNode(child[i]);
            }

            node.children = children;
        }

    }
    return node;
}

var setting = {
    showLine: true,
    view: {
        dblClickExpand: false,
        selectedMulti: false,
        autoCancelSelected: true
    },
    check: {
        enable: false,
        radioType: "all"
    },
    data: {
        simpleData: {
            idKey: "id",
            pIdKey: "pid",
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick,
        onDblClick: onDblClick
    }
};

//可以判断哪些节点不可选
function beforeClick(treeId, treeNode) {
    var check = true;
    /*if(treeNode.id.toString().substring(8).match(/^0{4}$/)){
        check = false;
        alert("只能选择第三级菜单。。。");
    }*/
    return check;
}

//点击节点动作
function onClick(e, treeId, treeNode) {
    /*var pNode = treeNode.getParentNode();
    var ppNode = pNode.getParentNode();

    $('#areaid').val(ppNode.id);
    $('#areaname').val(ppNode.name);

    $('#substationid').val(pNode.id);
    $('#substationname').val(pNode.name);*/
    chk_value = [];
    $('#store_id').val(treeNode.id);
    $('#areaSel').val(treeNode.name).change();
    var info_id = $('select[name="info_id"] option:selected').val();
    var level = treeNode.leve;
    if (level === '1') {
        level = "1,2"
    } else if (level === '2') {
        level = "3"
    } else if (level === '3') {
        level = "4,5"
    } else if (level === '0') {
        level = "99";
    }
    // var org_id = treeNode.org_id;
    $('#dataTable').initTableData({"url": "/sysTitleConfig/getLevelKey", "data": {"level": level, "size": 10}});
    // ajaxGet("/sysTitleConfig/getTitleKey", {"titleKeys": level}, function (data) {
    //     themeSelect2("editormd-theme-select2", data, "");
    // });
    if (undefined !== info_id && "undefined"!== info_id  && info_id !== "" && info_id !== null) {
        ajaxGet("/mediaConfig/getStoreId/" + treeNode.id + "/" + info_id, null, function (data) {
            for (var i=0; i< data.length; i++) {
                if (!chk_value.in_array(data[i].title_id)) {
                    if (undefined !== data[i].title_id){
                        chk_value.push(data[i].title_id);
                    }
                }
                $('table tbody tr').find('input[type="checkbox"]').each(function () {
                    let parse = JSON.parse($(this).val());
                    if (parse.id === data[i].title_id) {
                        $(this).attr("checked", "checked");
                    }
                });
            }

        });
    }
    hideMenu();

}

Array.prototype.in_array = function (e) {
    var r = new RegExp(',' + e + ',');
    return (r.test(',' + this.join(this.S) + ','));
};

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

//双击节点动作
function onDblClick(e, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("areaTree");
    $('#store_id').val("");
    $('#areaSel').val("");
    treeObj.cancelSelectedNode(treeNode);
}

//显示下拉树
function showMenu() {
    var areaObj = $("#areaSel");
    var areaOffset = $("#areaSel").offset();
    $("#menuContent").css({
        left: areaOffset.left + "px",
        top: areaOffset.top + areaObj.outerHeight() + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

//隐藏下拉树
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

//注册关闭下拉树的事件
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "areaSel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
        hideMenu();
    }
}

//下拉树搜索框搜索
function AutoMatch(txtObj) {
    var val = $(txtObj).val();
    if (val.length > 0) {
        // var treeObj = $.fn.zTree.getZTreeObj("areaTree");
        var treeObj = $.fn.zTree.getZTreeObj("seachezTree");
        var nodeList = treeObj.getNodesByParamFuzzy("name", val, null);
        //将找到的nodelist节点更新至Ztree内
        $.fn.zTree.init($("#areaTree"), setting, nodeList);
    } else {
        $.fn.zTree.init($("#areaTree"), setting, zNodes);
    }
}