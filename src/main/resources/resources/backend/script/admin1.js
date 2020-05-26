$(function () {

    //1.初始化Table
    let oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    let oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

$(".yangyi").click(function () {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
})

function operateFormatter(value, row, index) {
    return [
        '<button  class="btn btn-xs btn-success btn-edit-one" title="编辑"><i class="fa fa-pencil"></i></button> ',
        '<button  class="btn btn-xs btn-danger btn-del-one" title="删除"><i class="glyphicon glyphicon-trash"></i></button>'
    ].join('');
}

window.operateEvents = {
    'click .btn-edit-one': function (e, value, row, index) {
        layer.open({
            type: 2,
            title: '编辑',
            shadeClose: true,
            shade: 0.2,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '//fly.layui.com/'
        });
    },
    'click .btn-del-one': function (e, value, row, index) {
        layer.confirm('您确定要删除整条数据吗?', {
            btn: ['确定', '取消'], //按钮
        }, function () {
           alert("delete")
        });
    }
};

let TableInit = function () {
    let oTableInit = {};
    //初始化Table
    oTableInit.Init = function () {
        $('#table').bootstrapTable({
            url: '/admin/auth/admin/list',         //请求后台的URL（*）
            pk: "id",
            method: 'get',                      //请求方式（*）
            dataType: 'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            queryParamsType: '',                //如果要在oTableInit.queryParams方法获取pageNumber和pageSize的值，需要将此值设置为空字符串（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            columns: [
                {
                    checkbox: true
                },
                {field: 'id', title: "ID"},
                {
                    field: 'username',
                    title: '账号'
                },
                {
                    field: 'nick_name',
                    title: '名称'
                },
                {
                    field: 'create_time',
                    title: '创建时间'
                },
                {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        // 特别说明：
        //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        // 如果queryParamsType=limit,params包含{limit, offset, search, sort, order}
        // 如果queryParamsType!=limit,params包含{pageSize, pageNumber, searchText, sortName, sortOrder}
        let temp = {
            limit: params.pageSize,   //页面大小
            page: params.pageNumber,  //页码
        };
        return temp;
    };
    return oTableInit;
};

let ButtonInit = function () {
    let oInit = {};
    let postdata = {};

    oInit.Init = function () {

        $(".btn-add").click(function () {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.2,
                maxmin: true, //开启最大化最小化按钮
                area: ['900px', '600px'],
                content: '/admin/auth/admin/save'
            });

        });


        $(".btn-edit").click(function () {
            let arrselections = $("#table").bootstrapTable('getSelections');

            if (arrselections.length > 1) {

            }
            if (arrselections.length <= 0) {
                alert('请选择有效数据');
                return;
            }
            alert(arrselections[0]['id']);
            alert("edit")


        });

    };

    return oInit;
};