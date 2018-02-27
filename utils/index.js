
module.exports={
	 wraprp(content){
	 	content = content.replace(/\n/g,'\r\n')
	 	content = content.replace(/\\\\/g,'\\\\\\')
	 	return content
	 }
}