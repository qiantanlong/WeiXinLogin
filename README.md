# WeiXinLogin
微信授权登录，access_token刷新，用户信息获取。
# 微信授权登录 #
## 项目介绍 ##
1、实现微信授权

2、获取用户的信息

3、检测access_token是否可用，刷新access_token

## 微信授权的坑 ##
1、注意access_token的有效期是两个小时，refresh_token有效期是30天，注意刷新

2、要想调起授权，必须使用正式签名文件进行签名，否则调起授权无效

3、WXEntryActivity类中的onResp回调方法中注意finish()这个activity，否则授权成功或取消时，会停止在一个白屏界面

4、WXEntryActivity必须放在你的完整包名目录下，也 就是你在微信开发平台建APP时那个包名

5、一定注意包名，一定注意包名，一定注意包名，重要事说三遍

## 项目中用到的第三方框架 ##
1、eventbus用户授权界面和本地界面数据传递

2、xutils网络访问框架，很经典，很经典

3、butterknife懒得写findviewbyid

4、自己写了网络检测、Log打印、SP存储（本项目没有使用）工具类
## 使用这个项目注意事项 ##
1、xutils是一个库，需要初始化

	public class MyApp extends Application {
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        x.Ext.init(this);//初始化xutils
	    }
	}
在项目的build中添加

	dependencies {
	        ***********
			***********
	        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.2'
	        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.4.1'
	
	       
	    }

2、butterknife使用，自己上网查
