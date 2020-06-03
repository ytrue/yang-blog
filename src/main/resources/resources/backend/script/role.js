window.operateEvents = {
    'click .btn-edit-one': function (e, value, row, index) {
        let id = row.id;
        layer.open({
            type: 2,
            title: '编辑',
            shadeClose: true,
            shade: 0.2,
            maxmin: true, //开启最大化最小化按钮
            area: ['900px', '600px'],
            content: '/admin/auth/role/update?id=' + id,
        });
    },
    'click .btn-del-one': function (e, value, row, index) {
        let id = row.id;
        deleteData("/admin/auth/role/delete", [id]);
    },
    'click .btn-find-one': function (e, value, row, index) {
        let id = row.id;
        layer.open({
            type: 2,
            title: '权限分配',
            shadeClose: true,
            shade: 0.2,
            maxmin: true, //开启最大化最小化按钮
            area: ['300px', '600px'],
            content: '/admin/auth/role/assign?id=' + id,
        });
    }
};

function reset() {
    $("#table").bootstrapTable('refresh');
}


axios.interceptors.request.use(function (config) {
    layer.load(1, {
        shade: [0.2, '#fff'] //0.1透明度的白色背景
    });
    return config;
}, function (err) {
    layer.alert('未知错误，请刷新重试！');
});

function deleteData(url, ids) {
    layer.confirm(
        '您确定要删除选择的数据吗?',
        {btn: ['确定', '取消']},
        function () {
            axios({
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                },
                url: url,
                method: "delete",
                data: JSON.stringify(ids)
            }).then(function (response) {
                let data = response.data;
                if (data.code === 0) {
                    layer.closeAll('loading'); //关闭加载层
                    //初体验
                    layer.alert('数据交互成功！', {
                        icon: 1,
                    }, function () {
                        reset();
                        layer.closeAll();
                    });
                } else {
                    layer.closeAll('loading'); //关闭加载层
                    layer.alert(data.msg, {
                        icon: 2,
                    })
                }
            }).catch(function (error) {
                layer.closeAll('loading'); //关闭加载层
                layer.alert(error.response.data.msg,
                    {
                        icon: 2,
                    }
                )
            })
        }
    );
}

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
            {field: 'id', title: "ID"},
            {
                field: 'code',
                title: '角色编码'
            },
            {
                field: 'name',
                title: '角色名称'
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
                formatter: function (value, row, index) {
                    return [
                        '<button  class="btn btn-xs btn-info btn-find-one" title="权限分配"><i class="glyphicon glyphicon-lock"></i></button> ',
                        '<button  class="btn btn-xs btn-success btn-edit-one" title="编辑"><i class="glyphicon glyphicon-pencil"></i></button> ',
                        '<button  class="btn btn-xs btn-danger btn-del-one" title="删除"><i class="glyphicon glyphicon-trash"></i></button>'
                    ].join('');
                }
            }
        ],
        data: [],
        options: {
            url: '/admin/auth/role/index',         //请求后台的URL（*）
            pk: "id",
            method: 'post',                      //请求方式（*）
            dataType: 'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            queryParams: function (params) {
                // 特别说明：
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                // 如果queryParamsType=limit,params包含{limit, offset, search, sort, order}
                // 如果queryParamsType!=limit,params包含{pageSize, pageNumber, searchText, sortName, sortOrder}
                return {
                    limit: params.pageSize,   //页面大小
                    page: params.pageNumber,  //页码
                };
            },
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
                content: '/admin/auth/role/add'
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
                content: '/admin/auth/role/update?id=' + id,
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
            deleteData("/admin/auth/role/delete", delArr);
        },
        btnRest: function () {
            $("#table").bootstrapTable('refresh');
        },

    },
    mounted: function () {
    }
})