function test() {
   alert("12345678") ;
}

function calowr(){
    var url = 'http://localhost:8080/jsonp';
    getHtml(url,getHtmlSuccess,getHtmlFail);
}

var GetData = function (data) {
    alert(data.BookName + " " + data.Pages);
};

/**
 * jsonp 写法
 */
function getHtml( url,suc,fail) {
    $.ajax({
        type: "get",
        async: false,
        url: url,
        dataType: "jsonp",
        jsonp: "callback",//传递给请求处理程序或页面的，标识jsonp回调函数名(一般为:callback)
        jsonpCallback: "GetData",//callback的function名称
        success: function (data) {
            alert('suc')
        },
        error: function (data) {
            fail(data);
        }
    });
}

/**
 * 获取html成功，解析标签
 */
function getHtmlSuccess(data) {
    GetData(data)

}

/**
 *获取html失败，解析标签
 */
function getHtmlFail(data) {
    alert('失败')
}