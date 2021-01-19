function sendGetRequest(url, successFunc) {
    $.ajax({
        url: url,
        type: 'GET',
        success: function (res) {
            console.log('请求相应成功',res)
            if (typeof successFunc === "function") {
                successFunc(res);
            } else { //不是函数
                buildDefaultSuccessResponse(res);
            }
        },
        error: function () {

        },
        complete: function (res) {
            window.location.reload()
        }
    });
}

function sendDeleteRequest(url,successFunc) {
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function (res) {
            console.log('请求相应成功',res)
            if (typeof successFunc === "function") {
                successFunc(res);
            } else { //不是函数
                buildDefaultSuccessResponse(res);
            }
        },
        error: function () {

        },
        complete: function (res) {

        }
    });
}

function sendPutRequest(url,successFunc) {
    $.ajax({
        url: url,
        type: 'PUT',
        success: function (res) {
            console.log('请求相应成功',res)
            if (typeof successFunc === "function") {
                successFunc(res);
            } else { //不是函数
                buildDefaultSuccessResponse(res);
            }
        },
        error: function () {

        },
        complete: function (res) {
            // window.location.reload()
        }
    });
}

function buildDefaultSuccessResponse(res) {
    if (res.errorCode === 0) {
        window.location.reload()
    }else {
        alert(res.errorMessage);
    }
}


function buildPaginatorUrl(pageNo,pageSize,base){
    base = base || '/sys/resource/list';
    if(!~base.indexOf('?')){
        base += '?'
    }
    return base+'&pageNo='+pageNo+'&pageSize='+pageSize;
}
var pageClick;

//显示分页页码的长度，默认为5
var fixNum = 5;

function getTablePaginatorDom(pageData, displayLimit, selector, baseUrl){
    var type = 'link';
    if(typeof baseUrl === 'function'){
        type = 'fn';
    }
    pageClick = function pageClick(pageNo){
        if(type == 'link'){
            location.href = buildPaginatorUrl(pageNo,displayLimit,baseUrl);
        }else{
            baseUrl(pageNo,displayLimit)
        }
    };
    var str = '';
    str += "<div class='dataTables_info' id='dataTables_info'>总计 "+pageData.totalCount+"</span> 条, &nbsp;第&nbsp; "+pageData.pageNo+"&nbsp;/&nbsp;"+pageData.totalPage+"&nbsp;页</div>";
    if(pageData.totalPage > 1){
        str += "<div class='dataTables_paginate paging_full_numbers' id='DataTables_Table_paginate'>";
        // 首页
        str += "<a onclick='pageClick(1)' class='first paginate_button'>首页</a>"

        // 上一页、
        if(pageData.pageNo > 1){
            str += "<a onclick='pageClick("+(pageData.pageNo - 1)+")' class='previous paginate_button' id='DataTables_Table_previous'> < </a>"
        }


        //计算左右偏移的页码
        var offsetNum = (fixNum-1) / 2;
        var beginNum = pageData.pageNo - offsetNum;
        var endNum = pageData.pageNo + offsetNum ;
        console.log("offsetNum",offsetNum)
        if(beginNum < 1){
            beginNum = 1;
            endNum = beginNum + fixNum -1;
            endNum = endNum > pageData.totalPage ? pageData.totalPage : endNum;
        }else if(endNum > pageData.totalPage){
            endNum = pageData.totalPage;
            beginNum = endNum - fixNum +1;
        }

        beginNum = beginNum<1?1:beginNum;


        // 中间页码
        str += "<span>";
        for(var i = beginNum; i <= endNum; i++){
            if(pageData.pageNo == i){
                str += "<a onclick='pageClick("+i+")' class='paginate_active'>"+i+"</a>"
            }else {
                str += "<a onclick='pageClick("+i+")' class='paginate_button'>"+i+"</a>"
            }
        }
        str += "</span>"
        // 下一页
        if(pageData.pageNo < pageData.totalPage){
            str += "<a onclick='pageClick("+(pageData.pageNo + 1)+")' class='next paginate_button'>></a>"
        }
        // 尾页
        str += "<a onclick='pageClick("+(pageData.totalPage)+")' class='last paginate_button'>尾页</a>"
        str += "</div>"
    }
    document.querySelector(selector).innerHTML = str;
}

/**
 * table的渲染状态
 * loading no-data
 *
 * @param {*} container
 * @param {*} num
 * @param {*} text
 */
function setTableStatus(container,num,text){
    $(container).html('<tr><td colspan="'+num+'" style="height:200px;line-height:200px;color:#9b9b9b;width:100%;text-align:center;">'+text+'</td></tr>')
}

/**
 * chart渲染的状态
 * loading  no-data
 *
 * @param {*} container
 * @param {*} text
 */
function setChartStatus(container,text){
    $(container).html('<div style="height:200px;line-height:200px;color:#9b9b9b;width:100%;text-align:center;">'+text+'</div>');
}
