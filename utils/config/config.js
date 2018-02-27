//本文件为主配置文件，程序执行时会将子配置文件信息合并进来(a2w.cljs、w2a.cljs)
const config = {
	constant:{
		/**
		 * 删除指定后缀的文件
		 */
		 DELETEFILE : 1,
		/**
		 将符合后缀的文件替换为指定的后缀 
		 */
		 UPDATESUFFIX : 2,
		/*文本信息转换
		 */
		 CONVERT:3
	},
	//默认转换规则
	ctype:'w2a',
    //转换目录
    "dir": "E:\\cl\\mwp\\11wxxcx\\11wxxcx\\11wxxcx1",
    //需要转换的文件的扩展名
	"convertExts": ['.js','.json','.wxss','.acss','.axml','.wxml']
};
module.exports =config