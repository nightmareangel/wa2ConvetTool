const config =require('../config/config.js')
let fs = require("fs");
let importReplace=function(content,file){
    switch(file){
        case `${config.dir}\\app.js`:
        return content
        break
    }
    //js文件头部引入regeneratorRuntime 
    if(!/const regeneratorRuntime = App\.regeneratorRuntime/.test(content))
        content = `const regeneratorRuntime = App.regeneratorRuntime\r\n${content}`

    let is = content.match(/import.+from\s*['"](\W+)(.+)['"]/g)//从文件内容中获取所有import 
    for(let i in is){
        let ci = is[i]//用于检索引入包是否存在
        let result=is[i]//用于文本替换
        ci = ci.replace(/\//g,'\\')//将/替换为\
        let pp = ci.replace(/import.+from\s*['"](\W+)(.+)['"]/,'$1,$2').split(',')//[0]路径前缀 [1]相对路径
        switch(pp[0]){
            case '\\'://根目录，由于微信不支持根目录，替换为const 包名 = App.包名
                let pn = result.replace(/.*['"]\W+(.+)['"]/,'$1')//包名
                result = `const ${pn} = App.${pn}`
            break
            case '.\\'://相对目录
                let p=''//存放包的系统路径
                p = `${file.replace(/((?:(?!\\).)+)$/,'')}${pp[1]}`;
                result = result.replace('./','')

                try{
                    let stat = fs.statSync(p);
                    if (stat.isFile()) {
                    }else{//目录为文件夹
                        result = result.replace(/(.+)(['"])\s*$/,'$1/index.js$2')
                    }
                }catch(e){//
                    switch(e.errno){//no such file or directory
                    case -4058:
                        break
                    }
                }
                result = result.replace(/import(.+)from(.+)\s*/g,'const $1=require($2)\r\n')
            break
        }
        //console.log(result)
        content = content.replace(is[i],result)

    }
    return content
}

module.exports={
	importReplace,

    resAppjs(){
        //app.js 路径
        let ap = `${utils.config.dir}\\app.js`
        //app.js 内容
        let ac = fs.readFileSync(ap, "utf8");
        //app.js const runtime = require('utils/common/third/runtime')\r\nApp.regeneratorRuntime = runtime
        //引入runtime 真机支持es6异步转同步函数
        if(!/require\('utils\/common\/third\/runtime'\)/.test(ac))
            ac = `const runtime = require('utils/common/third/runtime')\r\nApp.regeneratorRuntime = runtime\r\n${ac}`
        //utils/index.js 路径
        let up = `${utils.config.dir}\\utils\\index.js`
        //utils/index.js 内容
        let uc = fs.readFileSync(up, "utf8");
        if(!/App\.utils=utils/.test(ac)){
            //utils/index.js 中的引入包
            let is = uc.match(/const.+=require\( .+\)\s/g)
            for(let i in is){
                //将const bao = require('bao.js') 转换为 const bao = require('utils/bao.js') 
                is[i] = is[i].replace(/const(.+)=require\(\s*['"](.+)['"]\)/,'const$1=require(\'utils\/$2\');\r\nutils.$1=$1\r\n')
            }
            //import utils from 'utils/index.js' 转换为 const utils={} App.utils=utils
            ac = ac.replace(/(import.+from.+\s)/,'const utils={}\r\nApp.utils=utils\r\n')
            //将utils/index.js 中引入的包 放到app.js中引入 
            ac = ac.replace('App.utils=utils','App.utils=utils\r\n'+is.join(''))
        }

        fs.writeFileSync(ap, ac);
        console.log("app.js转换完毕");
    }    
}