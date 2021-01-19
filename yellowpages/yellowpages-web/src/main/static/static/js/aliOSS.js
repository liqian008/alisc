(function(){
    // var appServer = 'http://open.i1758.cn/pay-admin/common/temporaryToken.json';
    var appServer = '/pay-admin/common/temporaryToken.json';
    var bucket = '1758-bj-images';
    var region = 'oss-cn-beijing';
    var OSS = window.OSS;
    var OSS = OSS.Wrapper;
    // 临时授权
    var ST= null;



    // 上传图片
    function uploadFiles(files,insertFn){
        applyTokenDo(function(client){
            for(var i = 0; i < files.length;i++){
                var t = uploadFile(client,files[i],insertFn);
            }
        });
    }

    /**
     * 上传文件
     * @param {*} client 
     * @param {*} file 
     */
    function uploadFile(client,file,insertFn) {
        if(!checkFileType(file.name)){
            return ;
        }
        // 获取文件信息
        var key = buildImgName(file.name);
        var uploadClient = client.multipartUpload(key, file,{
            progress:function(p){
                return function (done) {
                    done();
                }
            }
        }).then(function (res) {
            insertFn('http://img.1758.com/'+res.name)
        }).catch(function (err) {
            alert('上传失败！')
        });
        return uploadClient;
    };


    // 检查文件类型，不是图片的文件要过滤掉
    function checkFileType(name){
        var reg = /\.(png|jpg|jpeg|gif)$/ig
        return reg.test(name)
    }

    // 构造图片路径和name
    function buildImgName(name){
        var arr = name.split('.');
        return 'image/assets/open/'+getTodayDate()+'/'+$.md5(name)+'.'+arr[arr.length-1]
    }

    /**
     * 获取今天时间
     */
    function getTodayDate(){
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        month = month < 10 ? '0'+month : month;
        day = day < 10 ? '0'+day : day;
        return ''+year+month+day;
    }

    // 请求token
    function applyTokenDo(func) {
        new Promise(function(resolve,reject){
            $.get(appServer,function(resData){
                if(resData.result == 1){
                    ST = resData;
                    resolve();
                }else{
                    reject(resData);
                }
            })
        })
        .then(function(){
            var creds = ST.data;
            var client = new OSS({
                region: region,
                accessKeyId: creds.accessKeyId,
                accessKeySecret: creds.accessKeySecret,
                stsToken: creds.stsToken,
                bucket: bucket
            });
            func(client);
        })
        .catch(function(resData){
            console.log(resData)
            // location.href = resData.data;
        })
    };
    /**
     * 各种提示
     * @param {String} text 
     * @param {Number} time 
     */
    function tip(text,time){
        var dom = document.createElement('div');
        var style = "position:fixed;padding:10px 20px;border-radius:20px;-webkit-border-radius:20px;left:50%;top:50%;margin:auto;background:rgba(0,0,0,.7);color:#fff;display:inline-block;transform:translate(-50%,-50%);-webkit-transform:translate(-50%,-50%);z-index:10"
        dom.setAttribute('style',style)
        dom.innerHTML = '<span>'+text+'</span>'
        document.body.appendChild(dom);
        if(typeof time != 'boolean'){
            time = time || 1500;
            setTimeout(function(){
                dom.remove();
            },time)
        }
        return dom
    }
    window.aliOSS = {
        uploadFiles: uploadFiles
    }
})()
