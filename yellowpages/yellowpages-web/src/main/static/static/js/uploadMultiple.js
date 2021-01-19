(function(){
    // var appServer = 'http://open.i1758.cn/pay-admin/common/temporaryToken.json';
    var appServer = '/pay-admin/common/temporaryToken.json';
    var bucket = '1758-bj-images';
    var region = 'oss-cn-beijing';
    var OSS = window.OSS;
    var OSS = OSS.Wrapper;
    // 记录第几个图片 方便做class处理
    var uid = 0;
    // 临时授权
    var ST= null;

    init();
    function init(){
        bindEvent('draggleWrapper','draggle-files')
    }

    /**
     * 绑定拖拽和点击事件
     */
    function bindEvent(wrapperId,draggleFiles){
        // 绑定事件
        var dragWrapper = document.getElementById(wrapperId);
        dragWrapper.ondrop = dropFile;
        dragWrapper.ondragenter = dragEnter;
        dragWrapper.ondragover = dragOver;
        dragWrapper.ondragleave = dragLeave;

        var inputFileDom = document.getElementById(draggleFiles);
        inputFileDom.onchange = getFiles;

        function dropFile(evt){
            evt.target.classList.remove('is-dragover');
            evt.preventDefault();
            var fileList = evt.dataTransfer.files; //获取文件对象
            uploadFiles(fileList);
        }

        function dragEnter(evt){
            evt.preventDefault()
            evt.target.classList.add('is-dragover')
        }

        function dragOver(evt){
            evt.preventDefault();
        }
        // 离开
        function dragLeave(evt){
            evt.target.classList.remove('is-dragover');
        }

        // 点击获取input输入图片
        function getFiles(evt){
            uploadFiles(evt.target.files);
        }
    }


    // 上传图片
    function uploadFiles(files){
        applyTokenDo(function(client){
            for(var i = 0; i < files.length;i++){
                var t = uploadFile(client,files[i]);
            }
        });
    }

    /**
     * 上传文件
     * @param {*} client 
     * @param {*} file 
     */
    function uploadFile(client,file) {
        if(!checkFileType(file.name)){
            return ;
        }
        // 生成classname
        var className = $.md5(uid.toString()).slice(-6);
        uid ++;

        // 组装dom
        setImgDom(className);
        // 获取文件信息
        var filePromise = getFileInfo(file,className);
        var key = buildImgName(file.name);
        var uploadClient = client.multipartUpload(key, file,{
            progress:function(p){
                return function (done) {
                    $('.'+className+' .progress').text( Math.floor(p * 100) + '%' )
                    done();
                }
            }
        }).then(function (res) {
            $('.'+className+' .head img').attr("src",'http://img.1758.com/'+res.name)
            $('.'+className+' .copy').attr("data-src",'http://img.1758.com/'+res.name)
            return 'http://img.1758.com/'+res.name;
        }).catch(function (err) {
            $('.'+className+' .progress').text('上传失败');
            $('.'+className+' .progress').css('color','red');
        });
        // 等待Promise
        Promise.all([filePromise,uploadClient])
            .then(function(arr){
                imgInfoUploadServer(arr[0].width,arr[0].height, arr[0].name, arr[0].size, arr[1])
            })
        return uploadClient;
    };


    // 检查文件类型，不是图片的文件要过滤掉
    function checkFileType(name){
        var reg = /\.(png|jpg|jpeg|gif)$/ig
        return reg.test(name)
    }

    // 构造图片路径和name
    function buildImgName(name){
        // var reg = /(.*)\.(png|jpg|jpeg|gif)$/i;            
        // var arr = name.match(reg) || ['','','zip'];
        var arr = name.split('.');
        return 'image/assets/open/'+getTodayDate()+'/'+$.md5(name+Date.now()+Math.random())+'.'+arr[arr.length-1]
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
        // var creds = ST.data;
        // var client = new OSS({
        //     region: region,
        //     accessKeyId: creds.accessKeyId,
        //     accessKeySecret: creds.accessKeySecret,
        //     stsToken: creds.stsToken,
        //     bucket: bucket
        // });
        // func(client);
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
            location.href = resData.data;
        })
    };

    function setImgDom(className){
        var str = '';
        str += '<li flex="cross:center box:justify" class="'+className+'"><div class="head" flex-box="0"><img src="" ></div><div class="name" flex-box="1"></div><div class="size" flex-box="0"></div><div class="kb" flex-box="0"></div><div class="progress" flex-box="0">上传中</div><div class="copy" flex-box="0">复制图片链接</div></li>'
        $('section ul').prepend(str);
    }

    /**
     * 根据本地信息 获取图片信息
     * @param {*} file 
     * @param {*} className 
     */
    function getFileInfo(file,className){
        return new Promise(function(resolve,reject){
            var fileReader = new FileReader();
            fileReader.onload = function () {
                //获取图片真实宽度和高度
                var image = new Image();
                image.onload = function () {
                    $('.'+className+' .size').text(image.width+' X '+ image.height);
                    var width = image.width;
                    var height = image.height;
                    var fileSize = file.size.toFixed(0);
                    resolve({
                      width: width,
                      height: height,
                      name: file_name,
                      size: fileSize,
                    });
                };
                image.src = fileReader.result;
                // 设置图片src
                $('.'+className+' .head img').attr("src", fileReader.result);
                // 设置图片name
                var file_name = file.name.substring(0, file.name.lastIndexOf('.'));
                $('.'+className+' .name').text(file_name);
                $('.'+className+' .kb').text((file.size / 1024).toFixed(1) + 'kb');
            }
            fileReader.readAsDataURL(file); //Base64
        })
    }

    function imgInfoUploadServer(width,height,name,size,resourceUrl){
        var url = '/pay-admin/common/saveAssetsManage.json';
        // var url = "http://localhost:1337/pay-admin/common/saveAssetsManage.json?VSCODE_CORS=" +JSON.stringify(VSCODE_CORS_URL)
        $.post(url,{
            width:width,	
            height:height,	
            name:name,
            description:'',	
            size:size,	
            resourceUrl:resourceUrl,	
            resourceType: 0,
        },function(res){
            if(res.result == 1){
                console.log(res)
            }
        })
    }

    // 复制内容
    function copyContent(s) {
        if (typeof document.execCommand === 'function') {
            var oInput = document.createElement('input');
            oInput.value = s;
            oInput.style.position = 'fixed';
            oInput.style.zIndex = -222;
            oInput.className = 'oInput';
            document.body.appendChild(oInput);
            oInput.select(); // 选择对象
            var bl = document.execCommand("Copy"); // 执行浏览器复制命令
            oInput.remove();
            return bl;
        } else {
            return false;
        }
    }

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

    $(document).on('click','.copy',function(){
        var url = $(this).attr('data-src');
        if(copyContent(url)){
            tip('复制成功！');
        }else{
            prompt('复制失败，请手动复制',url)
        }
    })

})()
