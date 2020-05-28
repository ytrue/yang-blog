new Vue({
    el: '#app',
    components: {
        'BootstrapTable': BootstrapTable
    },
    data: {
        columns: [
            {
                checkbox: true
            },
            {
                field: 'id',
                title: "ID"
            },
            {
                field: 'name',
                title: '名称',
                align: "left",
                cellStyle: function () {
                    return {
                        css: {
                            "text-align": "left"
                        }
                    }
                }
            },
            {
                field: 'url',
                title: '规则'
            },
            {
                field: 'icon',
                title: '图标'
            },
            {
                field: 'ismenu',
                title: '菜单'
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                // events: operateEvents,
                formatter: function (value, row, index) {
                    return [
                        '<button  class="btn btn-xs btn-success btn-edit-one" title="编辑"><i class="glyphicon glyphicon-pencil"></i></button> ',
                        '<button  class="btn btn-xs btn-danger btn-del-one" title="删除"><i class="glyphicon glyphicon-trash"></i></button>'
                    ].join('');
                }
            }
        ],
        data: [],
        options: {
            url: '/admin/auth/menu/all',         //请求后台的URL（*）
            pk: "id",
            method: 'get',                      //请求方式（*）
            dataType: 'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        }
    },
    methods: {
        btnAdd: function () {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.2,
                maxmin: true, //开启最大化最小化按钮
                area: ['900px', '600px'],
                content: '/admin/auth/admin/save'
            });
        },
        btnEdit: function () {
            let arrselections = $("#table").bootstrapTable('getSelections');

            if (arrselections.length > 1) {
                layer.alert("只能选择一行进行编辑！", {
                    icon: 2,
                });
                return;
            }
            if (arrselections.length <= 0) {
                layer.alert("请选择有效数据！", {
                    icon: 2,
                });
                return;
            }
            let id = arrselections[0]['id'];
            layer.open({
                type: 2,
                title: '编辑',
                shadeClose: true,
                shade: 0.2,
                maxmin: true, //开启最大化最小化按钮
                area: ['900px', '600px'],
                content: '/admin/auth/admin/edit?id=' + id,
            });
        },
        btnDel: function () {
            let arrselections = $("#table").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                layer.alert("请选择有效数据！", {
                    icon: 2,
                });
                return;
            }
            let delArr;
            delArr = [];
            for (let i = 0; i < arrselections.length; i++) {
                delArr.push(arrselections[i]['id'])
            }
            deleteData("/admin/auth/admin/delete", delArr);
        },
        btnRest: function () {
            $("#table").bootstrapTable('refresh');
        },

    },
});