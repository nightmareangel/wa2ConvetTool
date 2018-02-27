# wa2ConvetTool #
微信/阿里小程序双向转换工具

**双向转换，无需安装依赖**
## 使用 ##
1.  进入项目根目录
2. 	执行
**node index -t a2w -p d:\demo**<br/>
  参数说明：<br/>
  -t
  指定转换方式：<br/>
          a2w：支付宝转微信<br/>
          w2a：微信转支付宝<br/>
  -p
  指定转换目录：<br/>
       'd:\demo'<br/>
也可快速执行
**node index**<br/>
前提是
-t 与 -p 已在 utils/config/config.js 中配置
## 文件: ##
	utils //工具包
	  config //配置
	    --config.js //主配置文件
	    --a2w.cljs //支付宝转微信配置信息
	    --w2a.cljs //微信转支付宝配置信息
	  convert //代码转换
	    --index.js //主转换
	    --a2w.cljs //支付宝转微信
	    --w2a.cljs //微信转支付宝
	--index.js //入口

