package com.yourpackage;

import android.content.Intent;
import android.app.Activity;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class UninstallMyselfPlugin extends CordovaPlugin {

	@Override
    protected void pluginInitialize() {
        super.pluginInitialize();
    }
	
    /**
    * 覆盖execute方法
    *  param:action 判断javascript调用的方法名
    * 
    */
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
    	final Activity activity = this.cordova.getActivity();
        if (action.equals("showInfo")) {
        	
        }else if(action.equals("uninstallApp")){
            // 这里要输入你的app的包名
        	uninstallMyself("com.your.app", activity);
            return true;
        }else {
        }
        return false;
    }

    /**
     * 弹出手机系统本身的卸载对话框，让用户卸载
     * @param packageName
     * @param activity
     */
    public void uninstallMyself(String packageName, Activity activity) {
        if (checkApplication(packageName, activity)) {
            Uri packageURI = Uri.parse("package:" + packageName);
            // 这里是调用手机系统本身的卸载activity
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(packageURI);
            activity.startActivity(intent);
        }
    }

    /**
     * 匹配你的App包名是否能够找到，然后再卸载
     * @param packageName
     * @param activity
     * @return
     */
    public boolean checkApplication(String packageName, Activity activity) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            /**
             * 这里使用了GET_UNINSTALLED_PACKAGES是因为项目本身的androidSDK版本较低
             * 现在这个属性用MATCH_UNINSTALLED_PACKAGES替代，看源码注释可知
              */
        	activity.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    
    /**
	* 弹出提示框
	*/
	void show(String info) {
		Activity activity = this.cordova.getActivity();
		Toast toast = Toast.makeText(activity, info, Toast.LENGTH_SHORT);
		toast.show();
	}
    
}
