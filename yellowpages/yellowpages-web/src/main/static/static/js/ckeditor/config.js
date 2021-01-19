/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config )  
    {  
   config.toolbar = 'simple';//把默认工具栏改为‘MyToolbar’ 
       config.toolbar_simple =  
        [  
           // ['NewPage','Preview'],  
           // ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],  
           // ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],  
           // ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
            ['Bold','Italic','Strike'],  
    ['Styles','Format','RemoveFormat'],  
            ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],  
            ['Link','Unlink','Anchor'],  
           // ['Maximize','-','About']  
        ]; 
      
        config.toolbar_full =  
        [  
            ['NewPage','Preview'],  
            ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],  
            ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],  
            ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],  
            '/',  
            ['Styles','Format'],  
            ['Bold','Italic','Strike'],  
            ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],  
            ['Link','Unlink','Anchor'],  
            ['Maximize','-','About']  
        ];  
config.toolbar_basic =  
    [  
        //['NewPage','Preview'],  
        //['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],  
        //['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],  
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar', 
'PageBreak','-','Link','Unlink',/*'Anchor'*/'-','Bold','Italic','Strike','RemoveFormat'],  
        '/',   
        ['NumberedList','BulletedList','-','Outdent','Indent','Styles','Format'] 
    ];  
	
	//预览区域显示内容
	config.image_previewText=' ';
	//upload img url
	config.filebrowserImageUploadUrl= "articleImgUpload";
};