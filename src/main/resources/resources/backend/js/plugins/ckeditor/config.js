// CKEDITOR配置文件
CKEDITOR.editorConfig = function (config) {
    config.language = 'zh-cn';

    config.height = 400;
    config.width = 400;

    config.extraPlugins = 'image2';

    config.filebrowserImageUploadUrl='/ckeditor_upload_image';

    config.toolbarGroups = [
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'tools', groups: ['tools']},
        {name: 'clipboard', groups: ['clipboard', 'undo']},
        {name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing']},
        {name: 'forms', groups: ['forms']},
        {name: 'basicstyles', groups: ['basicstyles', 'cleanup']},
        {name: 'colors', groups: ['colors']},
        {name: 'styles', groups: ['styles']},
        {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi', 'paragraph']},
        {name: 'others', groups: ['others']},
        {name: 'about', groups: ['about']},
        {name: 'links', groups: ['links']},
        {name: 'insert', groups: ['insert']}
    ];

    config.removeButtons = 'About,Save,NewPage,Preview,Print,Templates,Find,Replace,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,Language,BidiRtl,BidiLtr,Flash,Iframe,PageBreak,SpecialChar,Smiley,Cut,Copy,Paste,PasteText,PasteFromWord,CopyFormatting,RemoveFormat,Anchor,Styles,Format,Font,JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock';
};