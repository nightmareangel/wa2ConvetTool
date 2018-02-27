
const utils =global.utils
let fs = require("fs");
let path = require("path");

const convert={
	cc:{},//当前转换器
	initConfig(ctype){
		//以文件方式读取配置信息，这样配置文件中可直接带有\
		let a2wcStr =fs.readFileSync(`.\\utils\\config\\${ctype}.cljs`, "utf8")
		// 替换注释
		a2wcStr = a2wcStr.replace(/\/\/#\^#.+/g,'')
		a2wcStr = a2wcStr.replace(/\/\*#\^#((?:(?!\/\*#\^#)[\s\S])+)#\^#\*\//g,'')
		// \替换为\\ 
		a2wcStr = a2wcStr.replace(/\\/g,'\\\\')
		//字符串模板替换
		//筛选 字符串模板
		let tss = a2wcStr.match(/`((?:(?!`)[\s\S])+)`/g)
		if(tss){
		    tss.forEach(f=>{
		        //将换行符替换为其字符形式
		        let s = f.replace(/\r\n/g,'\\r\\n')
		        //字符串模板改为字符串
		        s = s.replace(/`/g,'"')
		        a2wcStr = a2wcStr.replace(f,s)
		    })
		}
		//转为json对象
		utils[`${ctype}c`]= JSON.parse(a2wcStr)		
		utils.config =
		Object.assign(
			utils.config,
			utils[`${ctype}c`]
		)
		this.cc = require(`./${ctype}.js`)
		utils.config.ctype = ctype
		//将正则内的索引+2，因为转换的时候所拼接的正则已占两位索引
	    utils.config.cmethods.forEach(f=>{// 替换方法名
        	let tors = f.to.split('$')//以$分割字符串
        	if(tors.length>0)//如果存在正则变量替换
				f.to = tors.map(m => {
					let t = m
					if (/^\d+/.test(m)) {//如果以数字开头
						t = parseInt(m.replace(/(\d+).*/, '$1')) + 2//索引值+2
						t += m.replace(/\d+(.*)/, '$1')
					}
					return t
				}).join('$')
        })
        //console.log(utils.config)
	},
	//按照指令处理文件
	handleFile(file,order) {
	    try {
	        fs.accessSync(file)
	        let stat = fs.statSync(file);
	        if (stat.isFile()) {
	        	//过滤不需要替换的文件
				if(utils.config.convertExts.indexOf(path.extname(file))<0){
					return 
				}
	            switch (order) {
	                case utils.config.constant.DELETEFILE:
	                utils.convert.deleteFile(file);
	                break;
	                case utils.config.constant.UPDATESUFFIX:
	                utils.convert.updateSuffix(file);
	                break;
	                case utils.config.constant.CONVERT:
	                utils.convert.convert(file);
	                break;
	                default:
	                console.log("没有该种处理文件的方式");
	                break;
	            }
	        } else if (stat.isDirectory()) {
	            let dirs = fs.readdirSync(file);
	            for (let i in dirs) {
	                this.handleFile(path.resolve(file, dirs[i]),order);
	            }
	        }
	    } catch (e) {
	        console.log(e)
	    }
	},


	/*
	  将符合后缀的文件替换为指定的后缀、
	 */
	 updateSuffix(file) {
	    let csuffixs = utils.config.csuffixs;
	    
	    let cs = csuffixs.find(f=>{
	    	return file.endsWith(f.from)
	    })
	    if(cs){
		    try {
		    	let newfile = file.replace(new RegExp(cs.from, "g"), cs.to)
	            fs.renameSync(file, newfile);
		        console.log("文件修改后缀名：" + newfile);
		    } catch (e) {
		        console.log(e)
		        console.log("文件修改后缀名出错：" + newfile + "--" + e.getMessage());
		    }		    
		}
	},

	/*
	  删除指定后缀的文件
	 */
	 deleteFile(file) {
	    try {
	        fs.accessSync(file);
	        fs.unlinkSync(file);
	        console.log("文件删除成功:" + file);
	    } catch (e) {
	        console.log(e);
	        console.log(file + "文件有误");
	    }
	},
	cpas(content){
		let cpas = utils.config.cpas
		cpas.forEach(f=>{
			//f为一个方法的替换规则
	        let rg = new RegExp(`\\.${f.pname}\\(\\s*(?:(?!\\))[\\s\\S])+\\)`,'g');
	        //从content中取出当前规则匹配到的所有方法的字符串
	        let ps = content.match(rg)
	        if(!ps) return content
	        ps.forEach(psf=>{
	        	//psf 为一个方法的字符串
	        	let newv = psf
	        	f.rps.forEach(frpsf=>{
	        		//frpsf为当前方法所需处理属性的替换规则
	        		let akrg
	                switch(frpsf.type){
	                    case 'AddKEY'://替换类型为添加属性
		                    akrg = new RegExp(`\(\\.${f.pname}\)\\s*\\(\\s*\\{`,'g')
		                    newv = newv.replace(akrg,`$1({${frpsf.kto}:${frpsf.vto},`)
	                    break	                	
	                    case 'DelKEY'://替换类型为删除属性
		                    akrg = new RegExp(`\(\\.${f.pname}\)\\s*\\(\\s*\\{\\s*${frpsf.kfrom}:\\s*(?:(?!,).)+\\s*,`,'g')
		                    newv = newv.replace(akrg,`$1({^_^`)
		                    	//console.log(f,newv)
	                    break	                	
	                    case 'ChangeKEY'://替换类型为替换属性
	                    	//替换属性名称
		                    newv = newv.replace(new RegExp(`${frpsf.kfrom}s*:`,'g'), `${frpsf.kto}:`)
		                    //替换属性值类型
		                    let rms = newv.match(new RegExp(`.*(${frpsf.kto}s*:.+).*`))//取出键值对
		                    switch(frpsf.vtype){
		                    	case 'string':
			                    	newv = newv.replace(rms[1],rms[1].replace(/:\s*(\d+)\s*/,':\'$1\''))
		                    	break
		                    	case 'number':
                                    newv = newv.replace(rms[1],rms[1].replace(/['"]/,''))
		                    	break
		                    }
	                    break	                	
	                }

	        	})
	            content = content.replace(psf,newv)
	        })
		})
		return content
	},
	convert(file){
	//if(file.indexOf('powerStation.js')==-1)return
		let preffix='',toPreffix=''
        switch(utils.config.ctype){
            default:
            case 'a2w':
		        preffix="(^|,|;|\\s+)my([\\.\\[])"
		        toPreffix = "$1wx$2"  
			break
            case 'w2a':
		        preffix="(^|,|;|\\s+)wx([\\.\\[])"
		        toPreffix = "$1my$2"        
			break
		}
        try {

	        let content = fs.readFileSync(file, "utf8");

			switch(path.extname(file)){
				case ".js":
					let cmethods = utils.config.cmethods;
		            //特性替换 
		            content = this.specialReplace(content,file)
		            //替换import方式
		            content = this.cc.importReplace(content,file)
		            //替换方法入参对象属性
		            content = this.cpas(content)
		            //return
		            //content = this.proKeysReplace(content,utils.config.JSApiPropReplace)
		            cmethods.forEach(f=>{// 替换方法名
		                content = content.replace(new RegExp(preffix + f.from, "g"), toPreffix + f.to);
		            })

		            content = content.replace(new RegExp(preffix, "g"), toPreffix);// 统一修改未进行方法替换的前缀
				break
				case ".axml":
				case ".wxml":
			        let cXXMLs = utils.config.cXXMLs;
			        cXXMLs.forEach(f=>{// 修改不一样的方法
		                content = content.replace(new RegExp(f.from, "g"), f.to);
			        })
				break
				case ".json":
			        let cJSONs = utils.config.cJSONs;
			        cJSONs.forEach(f=>{// 修改不一样的属性
		                content = content.replace(new RegExp(f.from, "g"), f.to);
			        })
				break
			}
	        fs.writeFileSync(file, content);
		    console.log("文件转换完毕：" + file);
        } catch (e) {
            console.log(e)
            console.log("转换文件出错：" + file);
        }	    	
	},
    /*特殊文本替换规则
    */
    specialReplace(content,file){
        let cSRs = utils.config.cSRs

        file = file.replace(utils.config.dir,'').replace(/\\/g,'/')

        let csrs = cSRs.filter(f=>{
            return f.path == file ||f.path == 'all'
        })
        csrs.forEach(f=>{
            f.rps.forEach(ff=>{
                switch(ff.type){
                    default:
                    case 'string':
                        content = content.replace(ff.from,ff.to)
                    break
                    case 'RegExp':
                        content = content.replace(new RegExp(ff.from,'g'),ff.to)
                    break
                }
            })         
        })

        return content

    },	
	//替换方法的属性
	proKeysReplace(content,JSApiPropReplace){
	    for(let k in JSApiPropReplace){
	        let rks = JSApiPropReplace[k]
	        let rg = new RegExp(`\\.${k}\\(\\s*(?:(?!\\))[\\s\\S])+\\)`,'g');
	        //从文本中取出需要替换的字符串

	        let ps = content.match(rg)
	        for(let i in ps){
	            let newv=ps[i]
	            //迭代具体需要替换的方法的属性
	            for(let rksk in rks){
	                let v = rks[rksk]
	                switch(v.cptype){
	                    case 'AddKEYS':
	                    let akrg = new RegExp(`\(\\.${k}\)\\s*\\(\\s*\\{`,'g')
	                    newv = newv.replace(akrg,`$1({${rksk}:${v.value},`)
	                    break
	                    default:
	                        //v[0] 新属性名 v[1] 属性值类型转换
	                        v = v.split('=')
	                        //替换新的属性名
	                        newv = newv.replace(new RegExp(`${rksk}\s*:`,'g'), `${v[0]}:`)
	                        if(v[1]){//配置中存在属性值类型转换
	                            //newv = newv.replace(`=${v[1]}`,'')
	                            let rms = newv.match(new RegExp(`.*(${v}\s*:.+),.*`))//取出键值对
	                            if(rms&&rms[1]){
	                                switch(v[1]){
	                                    case 'number->string':
	                                    newv = newv.replace(rms[1],rms[1].replace(/:\s*(\d+)\s*/,':\'$1\''))
	                                    break
	                                    case 'string->number':
	                                    newv = newv.replace(rms[1],rms[1].replace(/['"]/,''))
	                                    break
	                                }
	                            }
	                        }                    
	                }

	            }
	            content = content.replace(ps[i],newv)
	        }
	    }
	    return content
	},
	resAppjs(){
		this.cc.resAppjs()
	}
}
// //特殊字符替换
// function replaceSC(content,tn){
//     let scs = "."
//     for(let i in scs){
//     //console.log(`${tn}${scs[i].charCodeAt()}_`)
//     content = content.replace(new RegExp(`\\${scs[i]}`,'g'),`${tn}${scs[i].charCodeAt()}_`)
// }
// console.log(content)
// return content
// }
// function backTo(content,tn){
//     let scs = content.match(new RegExp(`${tn}\d+_`,'g'))
//     for(let i in scs){
//         content.replace(scs[i],String.fromCharCode(scs[i].replace(/.+_(\d+)_$/,'$1')))
//     }
//     return content
// }

module.exports = convert