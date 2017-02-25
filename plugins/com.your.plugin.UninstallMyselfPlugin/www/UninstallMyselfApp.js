var exec = require("cordova/exec");//引用类
//exports用于导出我们的方法
var uninstallMyselfPlugin={
	showInfo:function(info, success, error) {
		exec(success, error, "UninstallMyselfPlugin", "showInfo", [info]);
	},
	uninstallApp:function(success, error) {
		exec(success, error, "UninstallMyselfPlugin", "uninstallApp", []);
	}
}; 
module.exports = uninstallMyselfPlugin;