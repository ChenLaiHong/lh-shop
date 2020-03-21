/**

 @Name: 商城模板
 @Author: star1029
 @Copyright: layui.com
 */


layui.define(['element', 'carousel', 'table', 'util'], function (exports) {
    var $ = layui.$
        , element = layui.element
        , form = layui.form
        , carousel = layui.carousel
        , laypage = layui.laypage
        , util = layui.util
        , table = layui.table;

    //初始化
    var houseNav = $(".house-header").find(".layui-nav");

    $(function () {
        //详情页——选中
        var ddDetail = $(".house-detail").find(".shopChoose").find("dl").children("dd");
        ddDetail.each(function () {
            if ($(this).hasClass("active")) {
                $(this).append('<i class="layui-icon layui-icon-ok active"></i>');
            }
            ;
        });
        //详情页——数量
        $(".house-detail").find(".shopChoose").find(".btn-input").children("input").val("1");
    });

    //轮播
    var elemBanner = $('#house-carousel'), ins1 = carousel.render({
        elem: elemBanner
        , width: '100%'
        , height: elemBanner.height() + 'px'
        , arrow: 'none'
        , interval: 5000
    });
    $(window).on('resize', function () {
        var width = $(this).prop('innerWidth');
        ins1.reload({
            height: (width > 768 ? 500 : 150) + 'px'
        });
    });

    //首页——搜索
    $(".house-header").find("#search").on('click', function () {
        layer.open({
            type: 1
            ,
            title: false
            ,
            shadeClose: true
            ,
            area: '300px'
            ,
            content: '<div id="house-search" class="layui-form"><input type="text" placeholder="搜索好物" class="layui-input"></div>'
            ,
            success: function (layero, index) {
                $("#house-search").find("input").on('keydown', function (e) {
                    if (e.keyCode === 13) {
                        e.preventDefault();
                        layer.close(index);
                    }
                    ;
                });
            }
        });
    });

    //首页——点击切换
    $(".house-header").find("#switch").on('click', function () {
        if (houseNav.hasClass("close")) {
            $(".house-header").children(".layui-container")[0].style.height = 60 + houseNav[0].offsetHeight + 'px';
            houseNav.removeClass("close");
        } else {
            $(".house-header").children(".layui-container")[0].style.height = 50 + 'px';
            houseNav.addClass("close");
        }
    });

    //登入页——弹框
    $("#house-login").find(".getCode").children("button").on('click', function () {
        layer.msg('验证码已发送');
    });
    form.on('submit(user-login)', function (data) {
        window.location.href = "index1.html";
    });

    //列表页——点击切换
    $(".house-list").children(".filter").find("ul").each(function () {
        $(this).children("li").on('click', function () {
            $(this).addClass("active").siblings().removeClass("active");
        });
    });


    //详情页——图片选择
    var imgDetail = $(".house-detail").find(".intro-img").children("img")[0]
        , srcDetail = $(imgDetail).attr("src")
        , ulDetail = $(".house-detail").find(".thumb");
    ulDetail.children("li").each(function () {
        $(this).on('mouseenter', function () {
            imgDetail.src = $(this).children("img")[0].src;
        }).on("mouseleave", function () {
            imgDetail.src = srcDetail;
        });
    });
    $('.layui-icon-house-find').on('click', function () {
        var searchParam = $('.searchParam').val();
        location.href = '/front/search.do?searchParam=' + searchParam

    });
    //详情页——点击切换
    $(".house-detail").find(".shopChoose").find("dl").each(function () {
        $(this).children("dd").on('click', function () {
            $(this).addClass("active").siblings().removeClass("active");
            $(this).append('<i class="layui-icon layui-icon-ok active"></i>');
            $(this).siblings().children("i").replaceWith("");
        });
    });

    //详情页——数量选择
    var btnDetail = $(".house-detail").find(".shopChoose").find(".btn-input").children("button")
        , inpDetail = $(".house-detail").find(".shopChoose").find(".btn-input").children("input")
        , tipDetail = $(".house-detail").find(".shopChoose").find(".number").children(".inputTips")
        , change = function () {
        var inventoryData = $('#inventoryData').val();
        if (inpDetail[0].value > inventoryData - 0) {
            //超出库存数量
            tipDetail.css("display", "inline-block");
            $('.sale').hide();
            $('.shop').hide();
        } else {
            //未超出
            tipDetail.css("display", "none");
            $('.sale').show();
            $('.shop').show();
        }
        ;
    };
    //详情页--加入购物车
    $(".house-detail").find(".shopChoose").find(".shopBtn").children(".shop").on('click', function () {
        var productId = $(this).attr("id");
        var number = $(".house-detail").find(".shopChoose").find(".btn-input").children("input")[0].value
        $.ajax({
            url: '/front/inTrolley.do',
            type: 'post',
            data: {"productId": productId, "number": number},
            dataType: 'json',
            async: false,
            success: function (result) {
                if (!result.status) {
                    parent.layer.msg(result.msg, {time: 2000});
                    return false;
                } else {
                    layer.msg('加入购物车成功!');
                }
            }
        });
    });
    btnDetail.each(function (index) {
        $(this).on('click', function () {
            if (index == "1") {
                inpDetail.val(Number(inpDetail.val()) + 1);
            } else {
                inpDetail[0].value = inpDetail[0].value > 1 ? inpDetail[0].value - 1 : 1;
            }
            ;
            change();
        })
    });
    inpDetail.on('keydown', function (e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            this.value = isNaN(this.value) ? 1 : (this.value > 1 ? this.value : 1);
            change();
        }
        ;
    });

    //详情页——分页
    laypage.render({
        elem: 'detailList'
        , count: 50
        , theme: '#daba91'
        , layout: ['page', 'next']
    });

    //详情页——收藏
    $(".house-detail").find(".shopChoose").find(".collectBtn").on('click', function () {
        if ($(this).hasClass("collectBtn") == true) {
            $(this).find("#collect").addClass("layui-icon-rate-solid").removeClass("layui-icon-rate");
            $(this).find("#collect")[0].style.color = '#dbbb92';
            $(this).removeClass("collectBtn");
            var productId = $(this).attr("id");
            $.ajax({
                url: '/front/collect.do',
                type: 'post',
                data: {"productId": productId},
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (!result.status) {
                        parent.layer.msg(result.msg, {time: 2000});
                        return false;
                    } else {
                        layer.msg('已收藏');
                    }
                }
            });

        }
    });

    //我的收藏——点击切换
    $(".house-usercol").find(".user-list").children("li").each(function () {
        $(this).on('click', function () {
            $(this).addClass("active").siblings().removeClass("active");
        });
    });


    //我的收藏——删除
    $(".house-usercol").find(".layui-tab-content").find(".goods").each(function () {
        $(this).children(".del").on('click', function () {
            var id = $(this).attr("id");
            $.ajax({
                url: '/front/collect.do',
                type: 'post',
                data: {_method: "DELETE", "id": id},
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (!result.status) {
                        parent.layer.msg(result.msg, {time: 2000});
                        return false;
                    } else {
                        layer.alert("删除成功!", {
                            time: 1000,
                            end: function () {
                                location.href = '/front/collect.do?page=1&limit=12'
                            }
                        })
                    }
                }
            });
        });
    });

    //地址管理——表格
    table.render({
        elem: '#user-address'
        , url: '/address/addressTable.do'
        , id: 'tableAddress'
        , skin: 'line'
        , cols: [[
            {field: 'username', title: '收货人', align: 'center', width: 90}
            , {field: 'address', title: '地址', align: 'center'}
            , {field: 'tel', title: '联系方式', align: 'center', width: 120}
            , {field: 'isDefault', title: '是否默认', align: 'center', width: 120}
            , {title: '操作', align: 'center', templet: '#addressTpl', width: 120}
        ]]
    });

    //地址管理——监听工具条
    table.on('tool(user-address)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: '/address/address.do',
                    type: 'post',
                    data: {_method: "DELETE", "id": data.id},
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        if (!result.status) {
                            parent.layer.msg(result.msg, {time: 2000});
                            return false;
                        } else {
                            parent.layer.msg("删除成功!", {time: 1000});
                            layui.table.reload('tableAddress', {});
                        }
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.confirm('确定将此地址设为默认?', function (index) {
                $.ajax({
                    url: '/address/address.do',
                    type: 'put',
                    data: {"id": data.id},
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        if (!result.status) {
                            parent.layer.msg(result.msg, {time: 2000});
                            return false;
                        } else {
                            layer.msg("设置成功!", {time: 1000});
                            layui.table.reload('tableAddress', {});
                        }
                    }
                });
                layer.close(index);
            });
            // layer.open({
            //     type: 2
            //     , title: '编辑地址'
            //     , content: 'iframe.html'
            //     , area: ['730px', '420px']
            //     , shade: 0.8
            //     , skin: 'address-class'
            //     , btn: '确定'
            //     , yes: function (index, layero) {
            //         window['layui-layer-iframe' + index].layui.form.on('submit(useradd-submit)', function (data) {
            //             layer.close(index);
            //         });
            //         layero.find('iframe').contents().find("#useradd-submit").trigger('click');
            //     }
            // });
        }
    });
    $(".useradd").find(".address-add").on('click', function () {
        console.log("新增地址")
        layer.open({
            type: 2
            , title: '新建地址'
            , content: '/front/insertAddress.do'
            , area: ['730px', '420px']
            , shade: 0.8
            , skin: 'address-class'
            , btn: '确定'
            , yes: function (index, layero) {
                window['layui-layer-iframe' + index].layui.form.on('submit(useradd-submit)', function (data) {
                    console.log(JSON.stringify(data.field))
                    $.ajax({
                        url: '/address/address.do',
                        type: 'post',
                        data: data.field,
                        dataType: 'json',
                        async: false,
                        success: function (result) {
                            if (!result.status) {
                                parent.layer.msg(result.msg, {time: 2000});
                                return false;
                            } else {
                                parent.layer.msg("新增成功!", {time: 1000});
                                parent.layui.table.reload('tableAddress', {});
                                layer.close(index);

                            }
                        }
                    });
                });
                layero.find('iframe').contents().find("#useradd-submit").trigger('click');
            }
        });
    });
    //未完成订单
    $('.unfinished').on('click', function () {
        active.reload("未付款")
    })
    //等待发货
    $('.drop-shipping').on('click', function () {
        console.log("等待发货")
        active.reload("待发货")
    })
    //待收货
    $('.receiving').on('click', function () {
        active.reload("待收货")
    })
    //交易完成
    $('.complete').on('click', function () {
        active.reload("交易完成")
    })
    //退货中
    $('.salesReturn').on('click', function () {
        active.reload("退货中")
    })
    //个人中心——订单
    table.render({
        elem: '#house-user-order'
        , url: '/cart/orderTable.do'
        , id: 'house-user-order'
        , where: {'status': "未付款"}
        , skin: 'line'
        , cols: [[
            {title: '订单信息', align: 'center', templet: '#orderTpl', width: 220}
            , {field: 'price', title: '总价￥', align: 'center'}
            , {field: 'address', title: '收货信息', align: 'center', width: 450}
            , {title: '订单状态', align: 'center', templet: '#stateTpl', width: 100}
            , {title: '订单操作', align: 'center', templet: '#handleTpl', width: 120}
        ]]
    });
    active = {
        reload: function (status) {
            //执行重载
            console.log(status)
            table.reload('house-user-order', {
                url: '/front/orderTable.do'
                , method: 'get'
                , where: {
                    status: status,
                }
            });
        }
    };
    table.on('tool(house-user-order)', function (obj) {
        var data = obj.data;
        if (obj.event === 'check') {
            layer.open({
                type: 2,
                title: "查看物流",
                shadeClose: false, //点击遮罩关闭层
                area: ['700px', '400px'],
                content: "/order/lookLogistics.do?orderId=" + data.id
            });
        } else if (obj.event === 'pay') {
            layer.open({
                type: 2
                , title: "付款"
                , shadeClose: false //点击遮罩关闭层
                , area: ['700px', '400px']
                , content: "/front/toPayPage.do?id=" + data.id + "&price=" + data.price
            });
        } else if (obj.event === 'remind') {
            $.ajax({
                url: "/front/remind.do",
                type: "post",
                data: {"orderId": data.id},
                dataType: "json",
                success: function (result) {
                    if (!result.status) {
                        layer.msg(result.msg, {time: 2000});
                        return false;
                    }
                    layer.msg("提醒发货成功!", {time: 1000});
                }
            });
        } else if (obj.event === 'evaluate') {
            layer.open({
                type: 2,
                title: "评价",
                shadeClose: false, //点击遮罩关闭层
                area: ['700px', '600px'],
                content: "/front/evaluatePage.do?orderId=" + data.id
            });
        } else if (obj.event === 'salesReturn') {
            layer.open({
                type: 2,
                title: "退款",
                shadeClose: false, //点击遮罩关闭层
                area: ['700px', '600px'],
                content: "/front/salesReturnPage.do?orderId=" + data.id
            });
        }
    });

    //购物车——表格
    table.render({
        elem: '#house-usershop-table'
        , url: '/cart/list'
        , skin: 'line'
        , id: 'house-usershop-table'
        , cols: [[
            {type: 'checkbox', width: 50}
            , {title: '商品', align: 'center', minWidth: 120, templet: '#goodsTpl'}
            , {title: '单价', align: 'center', minWidth: 80, templet: '#priceTpl'}
            , {title: '数量', align: 'center', width: 150, templet: '#numTpl'}
            , {title: '小计', align: 'center', width: 100, templet: '#totalTpl'}
            , {title: '操作', align: 'center', width: 90, templet: '#shopTpl'}
        ]]
        , done: function (res, curr, count) {
            console.log(res.data)
            //数字框
            $(".numVal").each(function () {
                //获得小计 单价
                var totalTd = $(this).parents("td").siblings().find(".total")[0]
                    , totalPrice = $(this).parents("td").siblings().find("span").filter(".price")[0].innerHTML;
                $(this).children("button").each(function (index) {
                    //获得数量
                    var numVal = $(this).parent("div").children("input");
                    $(this).on('click', function () {
                        console.log(index);
                        if (index == "1") {
                            numVal.val(Number(numVal.val()) + 1);
                        } else {
                            numVal[0].value = numVal[0].value > 1 ? numVal[0].value - 1 : 1;
                        }
                        totalTd.innerHTML = '￥' + (numVal.val() * totalPrice.slice(1)).toFixed(2)
                        Calculation_of_total()
                    });
                });
                //按下回车
                $(this).children("input").on('keydown', function (e) {
                    if (e.keyCode === 13) {
                        e.preventDefault();
                        this.value = isNaN(this.value) ? 1 : (this.value > 1 ? this.value : 1);
                        totalTd.innerHTML = '￥' + (this.value * totalPrice.slice(1)).toFixed(2)
                    }
                    ;
                    Calculation_of_total()
                });
                //失去焦点
                $(this).children("input").on('blur', function (e) {
                    e.preventDefault();
                    this.value = isNaN(this.value) ? 1 : (this.value > 1 ? this.value : 1);
                    totalTd.innerHTML = '￥' + (this.value * totalPrice.slice(1)).toFixed(2);
                    Calculation_of_total()
                });
            });
            //合计
            // totalVal();
            if ($("#house-usershop-table").next("div").find(".layui-none").length != 0) {
                $(".house-usershop-table-num").css("display", "none");
            }
            ;
        }
        , text: {
            none: '<div class="house-usershop-table-none"><div><img src="/imgages/shopnone.png"></div><p>购物车空空如也</p></div>'
        }
        , id: 'house-usershop-table'
    });

    //合计的计算方法
    function Calculation_of_total() {
        var goodsVal = $(".house-usershop").find("#total").children("span")
            , copyWith = $(".house-usershop").find("#toCope").children("p").children("big")
            , copyTips = $(".house-usershop").find("#toCope").children("span");
        goodsVal[0].innerHTML = 0;
        var all_num = parseInt($("input[name='layTableCheckbox']").length); //记录总数
        var checked_num = parseInt($("input[name='layTableCheckbox']:checked").length); //选中的数量
        $("input[name='layTableCheckbox']:checked").each(function () {
            //判断是否被全选
            if (all_num == checked_num) {
                //如果全选
                var zum = 0;
                $("table tr").each(function () {
                    var res = $(this).find('td').eq(4).text();
                    if (res != '' && res != null) {
                        zum = zum + parseFloat(res.match(/\d+\.\d+/g)[0]);
                    }
                })
                goodsVal[0].innerHTML = zum
                return false;
            } else {
                var val = $(this).parents("td").siblings().find(".total")[0].innerHTML.slice(1)
                goodsVal[0].innerHTML = parseFloat(val) + Number(goodsVal[0].innerHTML);
            }
        })
        copyWith[0].innerHTML = '￥' + parseFloat(goodsVal[0].innerHTML).toFixed(2);
    }

    //合计
    var goodsVal = $(".house-usershop").find("#total").children("span")
        , copyWith = $(".house-usershop").find("#toCope").children("p").children("big")
        , copyTips = $(".house-usershop").find("#toCope").children("span");
    //监听复选框选择 获得总数
    table.on('checkbox(house-usershop-table)', function (obj) {
        //遍历这个列中的所有内容
        goodsVal[0].innerHTML = 0;
        var all_num = parseInt($("input[name='layTableCheckbox']").length); //记录总数
        var checked_num = parseInt($("input[name='layTableCheckbox']:checked").length); //选中的数量
        $("input[name='layTableCheckbox']:checked").each(function () {
            //判断是否被全选
            if (all_num == checked_num) {
                //如果全选
                var zum = 0;
                $("table tr").each(function () {
                    var res = $(this).find('td').eq(4).text();
                    if (res != '' && res != null) {
                        zum = zum + parseFloat(res.match(/\d+\.\d+/g)[0]);
                    }
                })
                goodsVal[0].innerHTML = zum
                return false;
            } else {
                var val = $(this).parents("td").siblings().find(".total")[0].innerHTML.slice(1)
                goodsVal[0].innerHTML = parseFloat(val) + Number(goodsVal[0].innerHTML);
            }
        })
        copyWith[0].innerHTML = '￥' + parseFloat(goodsVal[0].innerHTML).toFixed(2);
        var checkStatus = table.checkStatus('house-usershop-table');

        //转换格式
        goodsVal[0].innerHTML = parseFloat(goodsVal[0].innerHTML).toFixed(2);
        if (checkStatus.data.length != 0) {
            $(".house-usershop-table-num").children("input")[0].checked = true;
            form.render('checkbox');
        } else {
            $(".house-usershop-table-num").children("input")[0].checked = false;
            form.render('checkbox');
        }
        ;
        $(".house-usershop-table-num").children(".numal").html('已选 ' + checkStatus.data.length + ' 件');
    });
    //结算
    $('.settleAccounts').on('click', function () {
        var checkStatus = table.checkStatus('house-usershop-table');
        var ids = [];
        var nums = [];
        $(checkStatus.data).each(function () {
            ids.push(this.id);
        });
        console.log(ids)
        $("input[name='layTableCheckbox']:checked").each(function () {
            var val = $(this).parents("td").siblings().find("input").val();
            if (val != '' && val != null && val != 'undefined') {
                nums.push(val);
            }
        });
        goodsVal = $(".house-usershop").find("#total").children("span")
        location.href = '/front/subSettle.do?ids=' + ids.toString() + '&nums=' + nums.toString() + '&zumPrice=' + goodsVal[0].innerHTML
        // $.ajax({
        //     url: '/front/settleAccounts.do',
        //     type: 'post',
        //     data: {"ids": ids.toString(), "nums": nums.toString(), 'price': goodsVal[0].innerHTML},
        //     dataType: 'json',
        //     async: false,
        //     success: function (result) {
        //         if (result.status) {
        //             layer.alert("成功!", {
        //                 time: 2000,
        //                 end: function () {
        //                     location.href = '/front/order.do'
        //                 }
        //             })
        //         } else {
        //             layer.msg(result.msg)
        //         }
        //     }
        // });
    })
    table.on('tool(house-usershop-table)', function (obj) {
        var data = obj.data.productSpecs;
        if (obj.event === 'del') {
            layer.confirm('确定删除此物品？', function (index) {
                $.ajax({
                    url: '/cart/del',
                    type: 'post',
                    data: {_method: "DELETE", "productId": data.specsId},
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        if (result.statusCode != 200) {
                            parent.layer.msg(result.msg, {time: 2000});
                            return false;
                        } else {
                            parent.layer.msg("删除成功!", {time: 1000});
                            layui.table.reload('house-usershop-table', {});
                        }
                    }
                });
                layer.close(index);
            });
        }
    });
    $(".house-usershop").find("#batchDel").on('click', function () {
        var checkStatus = table.checkStatus('house-usershop-table')
            , checkData = checkStatus.data;
        if (checkData.length === 0) {
            layer.msg('请选择数据');
        } else {
            //执行 Ajax 操作之后再重载
            table.reload('house-usershop-table');
            $(".house-usershop-table-num").children("input")[0].checked = false;
            form.render('checkbox');
            $(".house-usershop-table-num").children(".numal").html('已选 0 件')
            copyWith[0].innerHTML = goodsVal[0].innerHTML = '￥0.00';
            copyTips.css("display", "none");
            layer.msg('已删除');
        }
    });


    //固定 bar
    util.fixbar({
        click: function (type) {
            if (type === 'bar1') {
                //
            }
        }
    });

    exports('house', {});
})