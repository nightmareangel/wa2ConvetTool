let fs = require("fs");


/*入参
系统入参：
[0]:node程序所在目录
例如：'C:\\Program Files\\nodejs\\node.exe'
[1]:运行文件目录
例如：'E:\\cl\\tool\\wx2ali-master\\node\\index'
[2-*]自定义参数 
例如：
指定转换方式：
    -t a2w
        a2w：支付宝转微信
        w2a：微信转支付宝
指定转换目录：
    -p 'E:\cl\mwp\11wxxcx\11wxxcx\'
*/
let arg = process.argv;

//引入工具包
let utils=require('./utils/index.js')
//将工具包绑定到全局对象
global.utils=utils
//引入转换工具包
utils.convert= require('./utils/convert/index.js')
//程序运行时配置信息
utils.config= require('./utils/config/config.js')


utils.convert.initConfig(utils.config.ctype)

//移除系统入参
arg.shift()
arg.shift()
//存在自定义入参
if(arg.length){
    //入参转换为数组
    let args = (arg.join(',')+',').match(/-\w+,(?:(?!,).)*/g)
    args.forEach(f=>{
        f = f.replace(/,$/,'')
        f = f.split(',')
        let key=f[0]
        let value=f[1]
        switch(key){
            case '-h':
                console.log(
`-t 转换类型 可选值：a2w w2a  说明：a2w支付宝转微信 w2a微信转支付宝
-p 转换目录 例如："E:\\cl`
                    )
            break;
            case '-t':
            if(value)
                utils.convert.initConfig(value)
            break;
            case '-p':
            if(value){
                utils.config.dir=value
            }
            break;
            default:

        }    
    })
    if(args.some(s=>s.split(',')[0]=='-h')) return 
}


//修改文件扩展名
utils.convert.handleFile(utils.config.dir,utils.config.constant.UPDATESUFFIX);
//替换文件内容
utils.convert.handleFile(utils.config.dir,utils.config.constant.CONVERT);
//修改app.js
utils.convert.resAppjs()
console.log("*****************************************************************************")
console.log("转换完成");
console.log("*****************************************************************************")











