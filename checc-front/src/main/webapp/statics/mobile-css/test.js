/* 全局变量 */
var gCompanyCode = ""; // 公司编码
var gCompanyName = ""; // 公司名称
var gCompanyUrl = ""; // 官网url
var gKuaidiNumber = ""; // 快递单号
var gValiCode = ""; // 验证码
var gHasVali = ""; // 查询是否有验证码
var gCheckStr = ""; //检查单号正则表达式
var gCheckInfo = ""; //单号错误提示信息
var gTimeout = 30000; // ajax超时时间
var gAjaxGet; // 查询ajax请求
var gQueryResult; // 查询结果
var gIsCheck; // 查询结果是否签收
var gIsRss = 0; // rss是否订阅
var gRssId = 0; // rssId
var gQueryId = 0; //查询id
var queryurl = '';
var isavailable = 0;
var gLoading = 0;
var gResultJson;
var gResultData;
var gSortStatus = 0;
var queryHistoryFrame = $("<iframe width=\"0\" height=\"0\" frameborder=\"0\" scrolling=\"0\"></iframe>");
//页面载入后执行
$(function () {
    queryHistoryFrame.appendTo('body');
    var postid = $("#postid");
    postid.focus(function () {
        var postid = $("#postid");
        if (postid.val() == postid.attr("placeholder")) {
            postid.val("").css("color", "#323232");
        }
        $("#postid").select();
    }).blur(function () {
        var postid = $("#postid");
        if (postid.val() == postid.attr("placeholder")) {
            postid.val("").css("color", "#323232");
        }
    }).focus();
    postid.keydown(function (e) {
        $("#errorTips").hide();
        var keycode = e.keyCode ? e.keyCode : e.which;
        if (keycode == 13) {
            query();
        }
    });
    $("#valicode").keydown(function (e) {
        $("#errorTips").hide();
        var keycode = e.keyCode ? e.keyCode : e.which;
        if (keycode == 13) {
            query();
        }
    });
});

//选择快递公司
function selectCompanyByCode(companyCode) {
    for (var i = 0; i < jsoncom.company.length; i++) {
        if (companyCode == jsoncom.company[i].number) {
            gCompanyCode = companyCode; // 选择公司
            gCompanyName = jsoncom.company[i].name; // 公司名称
            var tel = jsoncom.company[i].contactTel; // 公司电话
            var url = jsoncom.company[i].shortNumber; //页面文件名
            var iseffect = jsoncom.company[i].available; // 公司网站是否能查询
            var gpromptinfo = jsoncom.company[i].haltMessage; // 查询通道故障提示
            var companyurl = jsoncom.company[i].siteUrl; // 通道官网url
            gCheckStr = jsoncom.company[i].checkReg;
            gCheckInfo = jsoncom.company[i].checkRule;
            gCompanyUrl = companyurl;
            queryurl = jsoncom.company[i].queryUrl;
            isavailable = jsoncom.company[i].available;
            if (iseffect != null && iseffect == "1" && queryurl == "") {
                if (gpromptinfo != null && gpromptinfo != "") {
                    $("#errorTips").show();
                    gpromptinfo = gpromptinfo.replace("官网试试", "<a href='" + companyurl + "' target='_blank'>官网试试</a>");
                    $("#errorMessage").html(gpromptinfo);
                } else {
                    $("#errorMessage").html(gCompanyName + "网站不稳定，请稍后尝试查询.");
                }
                $("#errorTips").show();
            } else {
                $("#errorTips").hide();
            }
            $("#selectComBtn img").attr("src", "https://cdn.kuaidi100.com/images/all/56/" + gCompanyCode + ".png");
            $("#companyName,#comName").html(gCompanyName).show();
			$("#companyNum").html(gKuaidiNumber);
            $("#companyTel").html("电话：" + tel).show();
            $("#companyUrl").attr("href", companyurl).show();
            if (companyurl != null && companyurl != "") {
				$("#notfindComapnyUrl").attr("href", companyurl);
            	$("#notFindRight2").hide();
            	$("#notFindRight1").show();
			}else{
				$("#notFindRight2").show();
            	$("#notFindRight1").hide();
			}
            $(".span-shortname").text(gCompanyName);
            break;
        }
    }
}

//官网查询
function queryFromUrl() {
    /*
     var form = $("<form></form>");
     form.attr('action', queryurl + gKuaidiNumber);
     form.attr('method', 'post');
     form.attr('target', '_blank');
     form.appendTo("body");
     form.css('display', 'none');
     form.submit();
     */
    gIsCheck = 0;
    $("#sendHistory").hide();
    $("#resultHeader").show();
    $("#notFindTip").show();
    $("#notFindRight").hide();
    $("#notFindUpdate").show();
    $(".span-shortname").text(gCompanyName);
    $("#notfindUpdateUrl").attr("href", queryurl + gKuaidiNumber);
    queryHistoryFrame.attr("src", "//cache.kuaidi100.com/index.html?option=add&gCompanyCode=" + gCompanyCode + "&gKuaidiNumber=" + gKuaidiNumber + "&gIsCheck=0");
}

//查询
function getResult(companyCode, kuaidiNumber) {
    //在线寄快递-广告
    if(companyCode=="usps"){
        $("#sentOrder").show();
    }else{
        $("#sentOrder").hide();
    }
    var url = "/query";
    if (gHasVali == "1" || gHasVali == "2") {
        url = "/queryvalid";
    }
    gCompanyCode = companyCode;
    gKuaidiNumber = kuaidiNumber;

    if (queryurl != "" && isavailable == 1) {
        queryFromUrl();
        return false;
    }

    var agrs = "type=" + gCompanyCode + "&postid=" + gKuaidiNumber + "&id=" + gQueryType + "&valicode=" + gValiCode + "&temp=" + Math.random();
    url = url + "?" + agrs;
    $("#queryWait").show();
	$("#companyNum").html(gKuaidiNumber);
    $(".logo-model").show();
    $("#resultHeader").hide();
    $("#comList").hide();
    $("#example").hide();

    gLoading = 1;
    gAjaxGet = $.ajax({
        type: "GET",
        url: url,
        timeout: gTimeout,
        success: function (responseText) {
            $("#postid").select();
            gLoading = 0;
            $("#queryWait").hide();
            $(".logo-model").hide();
            $("#sendHistory").hide();
            $("#resultHeader").show();
            gIsCheck = 0;
            if (responseText.length == 0) {
                $("#notFindTip").show();
                return;
            }
            var resultJson = eval("(" + responseText + ")");
            gResultJson = resultJson;
            gQueryResult = resultJson.status;
            if (resultJson.status == 200) { //成功
                //$(".merry-snow").hide();//主题-圣诞-snow
                var sortStatus = getcookie("sortStatus");
                var resultData = resultJson.data;
                gResultData = resultData;
                gIsCheck = resultJson.ischeck;
                if (sortStatus == 0) {
                    gSortStatus = 0;
                    sortToggle();
                } else if (sortStatus == 1) {
                    gSortStatus = 1;
                    sortToggle();
                }
                $("#example").hide();
                $("#queryContext").show();
                $("#shareBtn").show();
                $("#queryPs").show();
                //普通二维码转小程序
                var shareUrl=encodeURIComponent("https://m.kuaidi100.com/result.jsp?com="+gCompanyCode+"&nu="+gKuaidiNumber+"&ordersource=pc_result&w=190&h=190")
                var shareImg="https://www.kuaidi100.com/twoCode.do?method=global&&qrcodesize=240&&content="+shareUrl
                $("#shareCode").attr("src",shareImg );
              //  getTimecost();
                if($(".open-preview").length>0){
                    getQueryQr();
                }
                $("#inputTips").hide();
               //$("#postid").select();
            } else if (resultJson.status == 408) { // 验证码错误
                $("#errorTips").show();
                $("#shareBtn").hide();
                $("#resultHeader").hide();
                if (gQueryType == 2) {
                    $("#errorMessage").html("需要验证码，请到快递查询页面输入验证码查询！");
                } else {
                    $("#errorMessage").html("您输入的验证码错误，请重新输入！");
                }
                if ($("#valideBox").is(":visible")) {
                    $("#valicode").focus();
                }
            } else if (resultJson.status == 201) { // 单号没查到
                $("#notFindTip").show();
                $("#shareBtn").hide();
                $("#example").hide();
                $("#resultHeader").hide();
            } else if (resultJson.status == 700) {
                queryFromUrl();
            } else {
                $("#notFindTip").show();
                $("#shareBtn").hide();
                $("#example").hide();
                $("#resultHeader").hide();
            }
            if (gHasVali == "1") {
                refreshCode();
            }
            queryHistoryFrame.attr("src", "//cache.kuaidi100.com/index.html?option=add&gCompanyCode=" + gCompanyCode + "&gKuaidiNumber=" + gKuaidiNumber + "&gIsCheck" + gIsCheck);
        },
        error: function (xmlHttpRequest, error) {
            gLoading = 0;
            if (error == "timeout") {
                onTimeout();
            }
        }
    });
    if(window.location.href.indexOf("www.kuaidi100.com/")!=-1 &&window.location.href.indexOf("www.kuaidi100.com/all/")==-1 ){
        getReaddImg();
    }
}

//获取时效
function getTimecost() {
    if (gAjaxGet) {
        gAjaxGet.abort();
    }
    gAjaxGet = $.ajax({
        type: "post",
        url: "/mapinfo",
        data: "queryResult=" + encodeURIComponent(Obj2str(gResultJson)) + "&toAddr=&toAddrCode=&nu=" + gKuaidiNumber + "&com=" + gCompanyCode,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (resultJson) {
            if (resultJson != "") {
                var usedTime = resultJson.usedTime;
                var arrTime = resultJson.arrTime;

                if (usedTime == "") {
                    $("#timeCost").hide();
                } else {
                    $("#timeCost").text(time2str(usedTime)).show();
                }
                if (arrTime == "") {
                    $("#arrTime").hide();
                } else {
                    $("#arrTime").text(arrTime).show();
                }
            } else {
                $("#timeCost").hide();
                $("#arrTime").hide();
            }
            $("#cityId_input").val("");
            $("#cityName_input").text("");
        }
    });
}

//生成码
$(".clo-preCode").click(function () {
    setcookie("cloCodepre","1");
    $(".open-preview").hide();
})
function getQueryQr() {
    var cloCodepre=getcookie("cloCodepre");
    //扫描预览
    if(!$("#queryContext").is(":hidden")&& $(document).width()>1500&& cloCodepre!=1){
        $(".open-preview").show();
        setInterval(function (){
            if($(".open-preview img").attr("src").indexOf("down_app.png")!=-1){
                $(".open-preview img").attr("src","https://cdn.kuaidi100.com/images/index/open_app.png")
            }else{
                $(".open-preview img").attr("src","https://cdn.kuaidi100.com/images/index/down_app.png")
            }
        },3000);
    }
    //条形码
    $("#barCodeImg").attr("src", "https://www.kuaidi100.com/twoCode.do?code=" + gKuaidiNumber + "&type=barcode&from=pc_scan&w=250&h=50");
    $("#barCodeNum").text(gKuaidiNumber);
    if(gKuaidiNumber.length>13&&gKuaidiNumber.length<=16){
        $("#barCodeNum").css("letter-spacing","1px");
    }else  if(gKuaidiNumber.length>16){
        $("#barCodeNum").css("letter-spacing","0px");
    }
}

//排序-切换
function sortToggle() {
    if (gSortStatus == 0) {
        setcookie("sortStatus", 0);
        sortup();
        gSortStatus = 1;
        $("#sortSpan").addClass("col1-down").removeClass("col1");
        $("#queryResult").removeClass("result-info-down");
    } else {
        setcookie("sortStatus", 1);
        sortdown();
        gSortStatus = 0;
        $("#sortSpan").addClass("col1").removeClass("col1-down");
        $("#queryResult").addClass("result-info-down");
    }
}

//排序-顺序
function sortup() {
    var resultTable = $("#queryResult");
    var resultData = gResultData;
    resultTable.empty();
    /*
     for (var i = 0; i < resultData.length; i++) {
     if (i == 0) {
     resultTable.append("<tr class=\"row1\"><td>" + resultData[i].time + "</td><td>" + resultData[i].context + "<span class='lastTag'></span></td></tr>");
     } else {
     resultTable.append("<tr><td class=\"row\">" + resultData[i].time + "</td><td>" + resultData[i].context + "</td></tr>");
     }
     }
     */
    for (var i = 0; i < resultData.length; i++) {
        var className = "";
        if (resultData.length == 1 && gIsCheck == 0) {
            className = "status status-wait";
        } else if (resultData.length == 1 && gIsCheck == 1) {
            className = "status status-check";
        } else if (i == 0 && gIsCheck == 0) {
            className = "status status-wait";
        } else if (i == 0 && gIsCheck == 1) {
            className = "status status-check";
        } else if (i == resultData.length - 1) {
            className = "status status-first";
        } else {
            className = "status";
        }
        var context = resultData[i].context;
        context = getJumpNetContext(context, gCompanyCode, "fonter1");
        context = getTelContext(context);
        var weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var dateStr = resultData[i].time
        var myDate = new Date(Date.parse(dateStr.replace(/-/g, "/")));
        var week = (weekDay[myDate.getDay()]);
        var day = resultData[i].time.substring(0, 10).replace(/-/g, ".");
        if (i < resultData.length - 1) {
            var preday = resultData[i + 1].time.substring(0, 10).replace(/-/g, ".");
        }
        var time = resultData[i].ftime.substring(11, 16)
        if (i == 0) {
            resultTable.append("<tr class=\"last\"><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span>&nbsp;&nbsp;<span class='week'>" + week + "</span></td><td class=\"" + className + "\">&nbsp;<div class=\"col2\"><span class=\"step\"><span class=\"line1\"></span><span class=\"line2\"></span></span></div></td><td class='context'>" + context + "</td></tr>");
        } else if (i == resultData.length - 1) {
            resultTable.append("<tr><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span>&nbsp;&nbsp;<span class='week'>" + week + "</span></td><td class=\"" + className + "\">&nbsp;</td><td class='context'>" + context + "</td></tr>");
        } else {
            if (day != preday) {
                resultTable.append("<tr><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span>&nbsp;&nbsp;<span class='week'>" + week + "</span></td><td class=\"" + className + "\">&nbsp;<div class=\"col2\"><span class=\"step\"><span class=\"line1\"></span><span class=\"line2\"></span></span></div></td><td class='context'>" + context + "</td></tr>");
            } else {
                resultTable.append("<tr><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span></td><td class=\"" + className + "\">&nbsp;<div class=\"col2\"><span class=\"step\"><span class=\"line1\"></span><span class=\"line2\"></span></span></div></td><td class='context'>" + context + "</td></tr>");
            }
        }
    }
    resultTable.show();
    $(".ex-right").addClass("ex-right-position");
    $(".ex-info").children().eq(2).addClass("ex-right-position");
}

//排序-倒序
function sortdown() {
    var resultTable = $("#queryResult");
    var resultData = gResultData;
    resultTable.empty();
    for (var i = resultData.length - 1; i >= 0; i--) {
        var className = "";
        if (resultData.length == 1 && gIsCheck == 0) {
            className = "status status-wait";
        } else if (resultData.length == 1 && gIsCheck == 1) {
            className = "status status-check";
        } else if (i == 0 && gIsCheck == 0) {
            className = "status status-wait";
        } else if (i == 0 && gIsCheck == 1) {
            className = "status status-check";
        } else if (i == resultData.length - 1) {
            className = "status status-first";
        } else {
            className = "status";
        }
        var context = resultData[i].context;
        context = getJumpNetContext(context, gCompanyCode, "fonter1");
        context = getTelContext(context);
        var weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var dateStr = resultData[i].time
        var myDate = new Date(Date.parse(dateStr.replace(/-/g, "/")));
        var week = (weekDay[myDate.getDay()]);
        var day = resultData[i].time.substring(0, 10).replace(/-/g, ".");
        if (i < resultData.length - 1) {
            var preday = resultData[i + 1].time.substring(0, 10).replace(/-/g, ".");
        }
        var time = resultData[i].ftime.substring(11, 16)
        if (i == 0) {
            resultTable.append("<tr class=\"last\"><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span>&nbsp;&nbsp;<span class='week'>" + week + "</span></td><td class=\"" + className + "\">&nbsp;</td><td class='context'>" + context + "</td></tr>");
        } else {
            if (day != preday || i == resultData.length - 1) {
                resultTable.append("<tr><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span>&nbsp;&nbsp;<span class='week'>" + week + "</span></td><td class=\"" + className + "\">&nbsp;<div class=\"col2\"><span class=\"step\"><span class=\"line1\"></span><span class=\"line2\"></span></span></div></td><td class='context'>" + context + "</td></tr>");
            } else {
                resultTable.append("<tr><td class=\"row1\"><span class='day'>" + day + "</span><span class='time'>" + time + "</span></td><td class=\"" + className + "\">&nbsp;<div class=\"col2\"><span class=\"step\"><span class=\"line1\"></span><span class=\"line2\"></span></span></div></td><td class='context'>" + context + "</td></tr>");
            }
        }
    }
    resultTable.show();
    $(".ex-right").addClass("ex-right-position");
    $(".ex-info").children().eq(2).addClass("ex-right-position");
}

//隐藏查询提示和内容
function hideTips() {
    $("#inputTips").hide();
    $("#queryWait").hide();
    $(".logo-model").hide();
    $("#errorTips").hide();
    $("#queryContext").hide();
    $("#queryQr").hide();
    $("#queryPs").hide();
    $("#notFindTip").hide();
    $("#notFindRight1").hide();
    $("#notFindRight2").show();
    $("#notFindRight").show();
    $("#notFindUpdate").hide();
    $("#companyName").hide();
	$("#companyNum").hide();
    $("#companyTel").hide();
    $("#companyUrl").hide();
    $("#timeCost").hide();
    $("#arrTime").hide();
    if (gAjaxGet) {
        gAjaxGet.abort();
    }
}

//查询前单号验证
function validateKuaidiNumber() {
    if ($("#queryWait").is(":visible")) { // 正在查询中->返回
        return false;
    }
    gKuaidiNumber = $("#postid").val().Trim();
    if (gCompanyCode == "rufengda" && checkRegOfcompany(gKuaidiNumber, "^\\d{16}$")) {
        gKuaidiNumber = "DD" + gKuaidiNumber;
    }
    $("#postid").val(gKuaidiNumber);
    gValiCode = $("#valicode").val().Trim(); // 验证
    var errorListName = "";
    if ($("#companyListType").val() != null && $("#companyListType").val() == "wuliuCompanyList") {
        errorListName = "物流";
    } else {
        errorListName = "快递";
    }
    if (gCompanyCode == "") {
        $("#errorTips").show();
        if (gQueryType == 13 || gQueryType == 14) {
            $("#errorMessage").html("请您在上方选择一家" + errorListName + "公司");
        } else {
            $("#errorMessage").html("请您在左侧列表中选择一家" + errorListName + "公司");
        }
        return false;
    }
    if (gKuaidiNumber == "" || gKuaidiNumber == $("#postid").attr("placeholder")) {
        $("#errorTips").show();
        $("#errorMessage").html("请您填写" + errorListName + "单号。");
        $("#postid").focus();
        return false;
    }
    if (!isNumberLetterFuhao(gKuaidiNumber)) {
        $("#errorTips").show();
        $("#errorMessage").html("单号仅能由数字、字母和特殊符号组合，请您查证。");
        $("#postid").focus();
        return false;
    }
    if (gKuaidiNumber.length < 5) {
        $("#errorTips").show();
        $("#errorMessage").html("单号不能小于5个字符，请您查证。");
        $("#postid").focus();
        return false;
    }
    if (gKuaidiNumber.length > 40) {
        $("#errorTips").show();
        $("#errorMessage").html("单号不能超过40个字符，请您查证。");
        $("#postid").focus();
        return false;
    }
    if (gCheckStr != '' && gCheckStr != null) {
        if (!checkRegOfcompany(gKuaidiNumber, gCheckStr)) {
            $("#errorTips").show();
            $("#errorMessage").html(gCheckInfo);
            $("#postid").focus();
            return false;
        }
    }
    return true;
}

//刷新验证码
function refreshCode() {
    $("#valicode").val("");
    $("#valiimages").attr("src", "https://cdn.kuaidi100.com/images/clear.gif");
    $("#valiimages").width(1);
    $("#valiimages").height(1);
    var imageUrl = "/images?type=" + gCompanyCode + "&temp=" + Math.random();
    $("#valiimages").attr("src", imageUrl);
    $("#valiimages").width(100);
    $("#valiimages").height(34);
    $("#valicode").focus();
}

//获取快递员连接
function getTelContext(context) {
    var reg = new RegExp("1\\d{10}", "gi");
    return context.replace(reg, function ($0) {
        var html = "";
        $.ajax({
            type: "post",
            url: "/courier/searchapi.do",
            data: "method=courierinfobyphone&json={%22phone%22:%22" + $0 + "%22}",
            dataType: "json",
            async: false,
            success: function (resultJson) {
                if (resultJson.status == 200) {
                    html = "<a target=\"_blank\" href=\"https://www.kuaidi100.com/courier/detail_" + resultJson.guid + ".html\">" + $0 + "</a>";
                } else {
                    html = $0;
                }
            }
        });
        return html;
    });
}

//获取网点连接
function getJumpNetContext(context, com, flag) {
    var beforeKeyword = "(?:(?!的|员|型|是).|^)";
    var keyword = ".?到达.?|.?问题.?|.?派件.?|.?签收.?|.?疑难.?|.?扫描.?|.?装袋.?|.?装包.?|.?妥投.?|.?操作员.?|.?审核.?|.?备注.?|.?客服.?|.?网点经理.?|.?员工.?|.?门卫.?|.?本人.?|.?草签.?|.?图片.?|.?分拨中心.?";
    var companyNetworkCodes = {
        "shentong": "5",
        "huitongkuaidi": "6",
        "huiqiangkuaidi": "27",
        "tiantian": "7",
        "zhaijisong": "12",
        "quanfengkuaidi": "23",
        "longbanwuliu": "24",
        "guotongkuaidi": "20",
        "kuaijiesudi": "18",
        "debangwuliu": "1",
        "zhongtong": "3",
        "yunda": "2"
    }
    switch (com) {
        case ("shentong"):
        case ("huitongkuaidi"):
        case ("huiqiangkuaidi"):
        case ("tiantian"):
        case ("quanfengkuaidi"):
        case ("longbanwuliu"):
        case ("guotongkuaidi"):
        case ("kuaijiesudi"): {
            var pattern = beforeKeyword + "【((?:(?!" + keyword + ")[^\\s\\d【]){2,})】";
            var reg = new RegExp(pattern, "gi");
            context = context.replace(reg, function ($0, $1, $2) {
                return "【<a href=\"https://www.kuaidi100.com/network/?from=" + flag + "&searchText=" + encodeURIComponent($1) + "&company=" + companyNetworkCodes[com] + "\" target=\"_blank\">" + $1 + "</a>】";
            });
            break;
        }
        case ("debangwuliu"):
        case ("zhaijisong"):
        case ("zhongtong"):
		case ("yunda"): {
            var pattern = beforeKeyword + "\\[((?:(?!" + keyword + ")[^\\s\\d【]){2,})\\]";
            var reg = new RegExp(pattern, "gi");
            context = context.replace(reg, function ($0, $1, $2) {
                return "[<a href=\"https://www.kuaidi100.com/network/?from=" + flag + "&searchText=" + encodeURIComponent($1) + "&company=" + companyNetworkCodes[com] + "\" target=\"_blank\">" + $1 + "</a>]";
            });
            break;
        }
    }
    return context;
}

//判断是否是数字/字母
function isNumberOrLetter(s) {
    var regu = "^[0-9a-zA-Z]+$";
    var re = new RegExp(regu);
    if (re.test(s)) {
        return true;
    } else {
        return false;
    }
}

//判断是否是数字/字母/特殊字符
function isNumberLetterFuhao(str) {
    var regStr = "^[0-9a-zA-Z\@\#\$\-\]+$";
    var reg = new RegExp(regStr);
    if (reg.test(str)) {
        return true;
    } else {
        return false;
    }
}

//正则表达式验证单号
function checkRegOfcompany(s, regu) {
    s = s.toUpperCase();
    var re = new RegExp(regu);
    if (re.test(s)) {
        return true;
    } else {
        return false;
    }
}

//onTimeout
function onTimeout() {
    if ($("#queryWait").is(":visible")) {
        $("#queryWait").hide();
        $(".logo-model").hide();
        $("#errorTips").show();
        $("#errorMessage").html("查询时间过长，请您稍后查询。");
    }
}

//go to feedback
function gotofeedback() {
    window.open("/help/feedback.shtml?mscomcode=" + gCompanyCode + "&mscomnu=" + gKuaidiNumber + "&msrandommath=" + Math.random());
}

//user time
function time2str(t) {
    var str = "";
    str = "已耗时";
    if (t != 0) {
        t = t / 1000;
        var day = parseInt(t / 86400);
        var hour = parseInt(t % 86400 / 3600);
        var minute = parseInt(t % 86400 % 3600 / 60);
        if (day != 0) {
            str += day + "天";
        }
        if (hour != 0 || day != 0) {
            str += hour + "小时";
        }
    }
    return str;
}

//Obj to str
function Obj2str(o) {
    if (o == undefined) {
        return "\"\"";
    }
    var r = [];
    if (typeof o == "string") return "\"" + o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
    if (typeof o == "object") {
        if (!o.sort) {
            for (var i in o)
                r.push("\"" + i + "\":" + Obj2str(o[i]));
            if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                r.push("toString:" + o.toString.toString());
            }
            r = "{" + r.join() + "}"
        } else {
            for (var i = 0; i < o.length; i++)
                r.push(Obj2str(o[i]))
            r = "[" + r.join() + "]";
        }
        return r;
    }
    return o.toString().replace(/\"\:/g, '":""');
}

//istel
String.prototype.isTel = function () {
    return (/^([0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^[0-9]{3,12}$)|(^((\(\d{3}\))|(\d{3}\-))?1[3578]\d{9})$/.test(this.Trim()));
}

//isMobile
String.prototype.isMobile = function () {
    return (/^(?:13\d|14\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));
}

//Trim
String.prototype.Trim = function () {
    return this.replace(/\s/g, "");
}