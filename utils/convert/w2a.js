const config =require('../config/config.js')
let fs = require("fs");
let importReplace=function(content,file){
    switch(file){
        case `${config.dir}\\app.js`:
        return content
        break
    }
    //js文件头部引入regeneratorRuntime 
    content = content.replace(/const regeneratorRuntime = App\.regeneratorRuntime[\r\n]/,'')
    content = content.replace(`const utils = App.utils`,`import utils from '/utils'`)

    content = content.replace(/const\s*(.+)=require\(\s*'((?:(?!\()[\s\S])+)'\s*\)/g,`import $1 from './$2'`)//替换所有 require方式 为import方式

    return content
}

module.exports={
	importReplace,

    resAppjs(){
        //app.js 路径
        let ap = `${utils.config.dir}\\app.js`
        //app.js 内容
        let ac = fs.readFileSync(ap, "utf8");
        ac = ac.replace(/^[\s\S]*App\(([\s\S]*$)/,`import utils from '/utils'\r\nApp($1`)
        fs.writeFileSync(ap, ac);
        console.log("app.js转换完毕");
    }    
}