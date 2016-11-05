package com.zhengsheng.administrator.childteach.wxapi;



import com.google.gson.Gson;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhengsheng.administrator.childteach.bean.TokenBean;
import com.zhengsheng.administrator.childteach.contacts.Contacts;
import com.zhengsheng.administrator.childteach.utils.LogUtils;
import com.zhengsheng.administrator.childteach.utils.SPUtil;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI mApi;

	private String code = "";
	private String state;
	private String country;
	private String lang;
	private TokenBean tokenBean;
	private String type;
	private SPUtil spUtil;
	private int mHour;
	Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message message) {
			switch (message.what) {
				case 0:
					getAccess_token(code);//通过code获取token
					break;
				case 1:
					//获取token成功后的操作
					String access_token = tokenBean.getAccess_token();
					int expires_in = tokenBean.getExpires_in();
					String openid = tokenBean.getOpenid();
					String refresh_token = tokenBean.getRefresh_token();
					String scope = tokenBean.getScope();
					String unionid = tokenBean.getUnionid();
					LogUtils.i("openid","openid:"+openid);
					EventBus.getDefault().post(tokenBean);


					break;
			}
			return false;
		}
	});

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApi = WXAPIFactory.createWXAPI(this, Contacts.APP_ID, true);
		boolean b = mApi.handleIntent(this.getIntent(), this);
		LogUtils.i("weixin", "shouquan:" + b);
	}
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		boolean b = mApi.handleIntent(intent, this);
		LogUtils.i("onNewIntent", "onNewIntent:" + b);
	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				finish();
				LogUtils.i("onResp","ERR_OK");
				code = ((SendAuth.Resp) resp).code;
				state = ((SendAuth.Resp) resp).state;
				country = ((SendAuth.Resp) resp).country;
				lang = ((SendAuth.Resp) resp).lang;
				handler.sendEmptyMessage(0);
				LogUtils.i("code",code);
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				LogUtils.i("onResp","ERR_USER_CANCEL");
				finish();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				LogUtils.i("onResp","ERR_AUTH_DENIED");
				finish();
				break;
			case BaseResp.ErrCode.ERR_BAN:
				LogUtils.i("onResp","ERR_BAN");
				finish();
				break;
			case BaseResp.ErrCode.ERR_COMM:
				LogUtils.i("onResp","一般错误");
				finish();
				break;
			case BaseResp.ErrCode.ERR_SENT_FAILED:
				LogUtils.i("onResp","发送失败");
				finish();
				break;
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				LogUtils.i("onResp","不支持的错误");
				finish();
				break;
			default:
				break;
		}

	}
	@Override
	public void onReq(BaseReq arg0) {
		LogUtils.i("onReq", "onReq");
	}

	/**
	 * 获取openid accessToken值用于后期操作
	 *
	 * @param code 请求码
	 */
	private void getAccess_token(final String code) {
		String path = "https://api.weixin.qq.com/sns/oauth2/access_token";
		RequestParams requestParams = new RequestParams(path);
		requestParams.addParameter("appid", Contacts.APP_ID);
		requestParams.addParameter("secret", Contacts.APP_SECRET);
		requestParams.addParameter("code", code);
		requestParams.addParameter("grant_type", "authorization_code");
		requestParams.setAsJsonContent(true);
		requestParams.setConnectTimeout(5000);
		LogUtils.i("token",Contacts.APP_ID+"__"+Contacts.APP_SECRET+"---"+code+"____");
		x.http().get(requestParams, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				LogUtils.i("getAccess_token", "getAccess_token:" + result);
				parseGetToken(result);//解析获取token返回的结果
				handler.sendEmptyMessage(1);
			}
			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				LogUtils.i("getAccess_token-error:", ex.toString());
			}
			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {

			}
		});
	}
	//解析token返回的结果
	private void parseGetToken(String result) {
		Gson gson = new Gson();
		tokenBean = gson.fromJson(result, TokenBean.class);
	}

}