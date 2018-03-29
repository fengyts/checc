/**
 * 查询子页面js
 */
var gQueryType = 1; //查询类型，1为www（web查询），5为chaxunAPI，19为源码查询
var noValidCode;
var noValid = false;
var querycookie;//历史单号
$(function () {
    $("#rssBtn").click(function () {
        setcookie_kuaidi100("addcom", gCompanyCode);
        setcookie_kuaidi100("addnu", gKuaidiNumber);
        window.location.href = "//buyer.kuaidi100.com";
    });
    //share
    $("#shareBtn").click(function () {
        openSenBox();
        $("#shareBox").show();
        $("#shareBox").css("z-index","98");
        $("#sendHistory").css("margin-top", ($(window).height() - 160 ) / 2 + "px");
    });
    $("#shareClose").click(function () {
        closeHisCtrl();
        $("#shareBox").hide();
    });
    $("#useTips").click(function () {
        var str = $(this).text();
        $('#postid').focus();
        $('#postid').val(str);
        $('#postid').css("font-size", "22px")
    });
    $("#downloadLink").mouseenter(function () {
        $("#resultImgBox").hide();
        $("#downloadImgBox").show();
    }).mouseleave(function () {
        $("#downloadImgBox").hide();
        $("#resultImgBox").show();
    });
    comInit();
});
$(document).click(function () {
    if (!$("#inputTips").is(":hidden")) {
        $("#inputTips").hide();
    }
});
$("#postid").click(function (e) {
    var keyword = $(this).val();
    e.stopPropagation();
    getqueryHistory();
})
$("#postid").bind('input propertychange', function () {
    var keyword = $("#postid").val();
    if (keyword == "") {
        $(this).css("font-size", "16px")
    } else {
        $(this).css("font-size", "22px")
    }
});
//print
$("#printBtn").click(function () {
    bdhtml = window.document.body.innerHTML;
    $("#serversite").hide();
    $("#companyNum").show();
    $("#companyNum").addClass("font16px");
    $("#printBtn").hide();
    $("#shareBtn").hide();
    $("#rssBtn").hide();
    $("#bindBtn").hide();
    window.document.body.innerHTML = $('.ex-title').html() + "<br/>" + $('#resultHeader').html() + "<br/>" + $('.result-top').html() + "<br/>" + $('#queryResult').html();
    window.print();
    window.document.body.innerHTML = bdhtml;
});

//input tips
$("#inputTips").delegate("li.selection", "mouseenter", function () {
    $("#inputTips li").removeClass("hover");
    $(this).addClass("hover");
    gSelectKeywordIndex = $(this).attr("data-index");
}).delegate("li.selection", "click", function () {
    $("#postid").val($(this).attr("data-num")).css({"color": "#323232", "font-size": "22px"}).focus().change();
    query();
}).delegate("a.delhistory", "click", function (e) {
    var code = $(this).parent().attr("data-code");
    var nu = $(this).parent().attr("data-num");
    var history = querycookie;
    ;
    for (var i = 0; i < history.length && i < 10; i++) {
        if (history[i].nu == nu && history[i].code == code) {
            history.splice(i, 0);
            queryHistoryFrame.attr("src", "//cache.kuaidi100.com/index.html?option=remove&gCompanyCode=" + history[i].code + "&gKuaidiNumber=" + history[i].nu);
        }
    }
    $(this).parent().remove();
    e.stopPropagation();
});
;
$("#delList").live("click", function () {
    queryHistoryFrame.attr("src", "//cache.kuaidi100.com/index.html?option=empty");
    var history = querycookie;
    for (var i = 0; i < history.length && i < 10; i++) {
        if (history[i].code == gCompanyCode) {
            history.splice(i, 0);
            queryHistoryFrame.attr("src", "//cache.kuaidi100.com/index.html?option=remove&gCompanyCode=" + history[i].code + "&gKuaidiNumber=" + history[i].nu);
        }
    }
    $("#inputTips li").remove();
    $("#inputTips").hide();
})

//快递管家-显示打印快递单
$("#printWaybill").click(function () {
    $("#printShow").show();
})
$("#printClose").click(function () {
    $("#printShow").hide();
})
$("#userprintNow").click(function () {
    var loginSession = getcookie("loginSession");
    var userType = getcookie("loginType");
    if (loginTOKEN && loginSession == "1") {
        if (userType == "BUYER" || userType == "ENT") {
            window.location.href = "http://sso.kuaidi100.com/user/setting.jsp?from=sntprint"
        } else {
            window.location.href = "http://b.kuaidi100.com/index.shtml"
        }
    } else {
        setcookie("printBox", 1);
        window.location.href = "//www.kuaidi100.com/user/";
    }
})
$("#toLogin").click(function () {
    setcookie("printBox", 1);
    window.location.href = "//www.kuaidi100.com/user/";
})
function comInit() {
    gCompanyCode = $("#companyCode").val(); // 选择公司
    $.ajax({
        type: "post",
        url: "/company.do",
        data: "method=companyjs&number=" + gCompanyCode,
        dataType: "json",
        success: function (jsoncom) {
            gCompanyName = jsoncom.name; // 公司名称
            var tel = jsoncom.contactTel; // 公司电话
            var url = jsoncom.shortNumber; //页面文件名
            var iseffect = jsoncom.available; // 公司网站是否能查询
            var gpromptinfo = jsoncom.haltMessage; // 查询通道故障提示
            var companyurl = jsoncom.siteUrl; // 通道官网url
            var serversite = jsoncom.introduceUrl;
            var shortName = jsoncom.shortName;
            gCheckStr = jsoncom.checkReg;
            gCheckInfo = jsoncom.checkRule;
            gCompanyUrl = companyurl;
            queryurl = jsoncom.queryUrl;
            isavailable = jsoncom.available;
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
            //$("#selectComBtn").html(gCompanyName);
            $("#selectComBtn img").attr("src", "https://cdn.kuaidi100.com/images/all/56/" + gCompanyCode + ".png");
            $("#notfindComapnyUrl").attr("href", companyurl);
            $("#notFindRight2").hide();
            $("#notFindRight1").show();
            $(".span-shortname").text(gCompanyName);
            if (tel == null || tel == "") {
                $("#allcompanyurl").hide();
            } else {
                document.getElementById("allcompanytel").innerHTML = tel.replace(/<.+?>/gim, '');
            }
            if (companyurl == null || companyurl == "") {
                $("#allcompanytel").hide();
            } else {
                document.getElementById("allcompanyurl").href = companyurl.replace(/<.+?>/gim, '');
                document.getElementById("allcompanyurl").innerHTML = companyurl.replace(/<.+?>/gim, '');
            }
            if (iseffect != null && iseffect == "1") {
                $("#errorTips").show();
                $("#errorMessage").html(name + "网站不稳定，请稍后尝试查询.");
            }
            if (serversite != null && serversite != "" && serversite != "null") {
                $("#serversite").attr("href", serversite);
            } else {
                $("#serversite").detach();
            }
            var thisurl=window.location.href;
            if(thisurl.indexOf("/global/dhl.shtml")!=-1||thisurl.indexOf("/global/fedex.shtml")!=-1||thisurl.indexOf("/global/usps.shtml")!=-1||thisurl.indexOf("/global/ups.shtml")!=-1||thisurl.indexOf("/global/tnt.shtml")!=-1){
                $("#sendOnInter").show();
                $("#serversite").css("margin-right","10px");
            }
           /* $.ajax({
                type: "post",
                url: "/sntprint/template.do",
                data: "method=notelist&company=" + gCompanyCode,
                dataType: "json",
                success: function (resultJson) {
                    if (resultJson.status == 200) {
                        if(resultJson.data!=null&&resultJson.data!=""){
                            if (resultJson.data.length > 0) {
                                var waybillId;
                                for(var i=0;i<resultJson.data.length;i++){
                                    if(resultJson.data[i].name.indexOf("电子面单")==-1){
                                        waybillId= resultJson.data[i].id;
                                        break;
                                    }
                                }
                                var loginSession = getcookie("loginSession");
                                var userType = getcookie("loginType");
                                if (loginTOKEN && loginSession == "1") {
                                    $("#printRem").hide();
                                    if (userType != "ENT" && userType != "BUYER" && userType != "SNT") {
                                        $("#printWaybill").hide();
                                    } else {
                                        $("#postid").css("width", "465px");
                                        $("#printWaybill").show();
                                        $("#printCom").html("[ " + shortName + " ]");
                                        $("#printExc").append("<img src='http://cdn.kuaidi100.com/images/notes/" + gCompanyCode + "/" + waybillId + "/big.jpg'>")
                                        var printBox = getcookie("printBox")
                                        if (printBox == 1) {
                                            $("#printShow").show();
                                            deleteCookie("printBox");
                                        }
                                    }
                                }else{
                                    $("#postid").css("width", "465px");
                                    $("#printWaybill").show();
                                    $("#printCom").html("[ " + shortName + " ]");
                                    $("#printExc").append("<img src='http://cdn.kuaidi100.com/images/notes/" + gCompanyCode + "/" + waybillId + "/big.jpg'>")
                                }
                            }
                        }
                    }
                }
            })*/
        }
    })
    var mscomnu = GetQueryString("mscomnu");
    if (!isNumberLetterFuhao(mscomnu)) mscomnu = "";
    if (noValidCode != "" && noValidCode != null) {
        gQueryType = 9;
        mscomnu = GetQueryString("nu");
    } else if (mscomnu == null) {
        mscomnu = GetQueryString("nu");
        if (mscomnu != null) { //来自chaxunAPI
            gQueryType = 5;
        }
    } else { //来自源码查询
        gQueryType = 19;
    }
    if ($("#companyCode").val() != "") {
        $("#postid").focus();
    }
    if (mscomnu != null) {
        $("#postid").val(mscomnu);
        //$("#postid").css("font-size","24px");
        $("#postid").css("color", "#000000");
        if (gHasVali == "1") {
            $("#valicode").focus();
        } else {
            query();
        }
    }
}

/* 查询并判断是否保存单号 */
function query() {
    $("#postid").blur();
    //查询订阅按钮控制
    var loginType = getcookie("loginType")
    if (loginType == "BUYER") {
        $("#rssBtn").show();
    }
    var loginMobile = getcookie("loginMobile");
    if (loginMobile == "" || loginMobile == "null") {
        $("#bindBtn").show();
    }
    $(".ex-info").children().eq(2).removeClass("ex-right-position");
    $(".ex-right").removeClass("ex-right-position");
    $("#errorTips").hide();
    $("#notFindTip").hide();
    $("#resultHeader").hide();
    $("#queryContext").hide();
    if (validateKuaidiNumber()) {
        getResult(gCompanyCode, $("#postid").val().Trim());
    }
}

//autonumber
$.fn.pasteEvents = function (delay) {
    if (delay == undefined) delay = 20;
    return $(this).each(function () {
        var $el = $(this);
        $el.on("paste", function () {
            $el.trigger("prepaste");
            setTimeout(function () {
                $el.trigger("postpaste");
            }, delay);
        });
    });
};
$("#postid").on("postpaste", function () {
    var num = $("#postid").val().Trim();
    gLastNum = num;
    if (gLastNum.length < 5) {
        return false;
    }
    $.ajax({
        type: "post",
        url: "/autonumber/autoComNum?text=" + num,
        dataType: "json",
        success: function (resultJson) {
            $("#postid").val(resultJson.num);
        }
    });
}).pasteEvents();

//history
function jsoncallback(data) {
    querycookie = eval('(' + decodeURIComponent(data) + ')');
    gethistory(querycookie);
}
function getqueryHistory() {
    $.ajax({
        url: "//cache.kuaidi100.com/querycookie.jsp",
        type: "GET",
        dataType: 'jsonp',
        jsonp: 'jsoncallback'
    });
    return true;
}
function gethistory(querycookie) {
    $("#suggestList").empty();
    $("#inputTips").empty();
    $("#inputTips").hide();
    var history = querycookie;
    var thisHis = "";
    var companyName = $(".ex-txt h3").text();
    for (i = 0; i < history.length && i < 10; i++) {
        if (history[i].code == gCompanyCode) {
            thisHis = 1;
        }
    }
    if (thisHis == 1) {
        $("#inputTips").show();
        $("#suggestList").append("<span class=\"li-title\">推荐</span>");
        for (i = 0; i < history.length && i < 10; i++) {
            if (history[i].code == gCompanyCode) {
                $("#inputTips").append("<li class=\"selection selection" + i + "\" data-index=\"" + i + "\" data-code=\"" + history[i].code + "\" data-num=\"" + history[i].nu + "\"><span>" + history[i].nu + "</span>&emsp;" + companyName + "<a class=\"hidden delhistory\"></a></li>");
                //$("#inputTips").append("<li class=\"selection selection" + i + "\" data-index=\"" + i + "\" data-code=\"" + history[i].code + "\" data-num=\"" + history[i].nu + "\">" + history[i].nu+ "&emsp;" + companyName + "</li>");
            }
        }
        $("#inputTips").append("<li class=\"histips_bottom\">最近十条查询记录。需要永久保存？更多功能？<a class=\"login-tips\" href='http://sso.kuaidi100.com/sso/login.jsp'>马上登录试试</a><a class=\"del-list\" id=\"delList\">全部清除</a></li>");
        //$("#inputTips").append("<li class=\"tips_bottom\">快递100大数据为您提供智能识别</li>");
    }
}

/* 已登录后 */
function logged() {
    $("#friendTip").hide();
    //countUncheck();
    countHis();
}

function unLogged() {
    countHis();
}

/*
 function countUncheck(){
 $.ajax({
 type:"post",
 url:"/userquery/query",
 data:"method=querycount&transstatus=1",
 success:function(responseText){
 var resultJson = eval("(" + responseText + ")");
 if(resultJson.status == 200){
 $("#hisNum2").html(resultJson.totalsize).show().unbind("mouseenter").unbind("mouseleave").mouseenter(function(){
 $(this).css("width","140px");
 $(this).html("您还有" + $(this).html() + "个未签收快递");
 }).mouseleave(function(){
 $(this).css("width","16px");
 var subStrIndex = $(this).html().indexOf("个");
 $(this).html($(this).html().substring(3,subStrIndex));
 });
 }
 }
 });
 }
 */