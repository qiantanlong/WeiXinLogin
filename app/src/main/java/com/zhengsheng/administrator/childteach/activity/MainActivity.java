package com.zhengsheng.administrator.childteach.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhengsheng.administrator.childteach.R;
import com.zhengsheng.administrator.childteach.bean.RefreshTokenResultBean;
import com.zhengsheng.administrator.childteach.bean.TestAccesTokenResultBean;
import com.zhengsheng.administrator.childteach.bean.TokenBean;
import com.zhengsheng.administrator.childteach.bean.UserBean;
import com.zhengsheng.administrator.childteach.contacts.Contacts;
import com.zhengsheng.administrator.childteach.utils.LogUtils;
import com.zhengsheng.administrator.childteach.utils.StateOfNetWork;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.btn_user_info)
    Button btnUserInfo;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.ll_nick_name)
    LinearLayout llNickName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.ll_id)
    LinearLayout llId;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_access_token)
    TextView tvAccessToken;
    @BindView(R.id.tv_refresh_token)
    TextView tvRefreshToken;
    @BindView(R.id.ll_access_token)
    LinearLayout llAccessToken;
    @BindView(R.id.ll_refresh_token)
    LinearLayout llRefreshToken;
    @BindView(R.id.tv_unionid)
    TextView tvUnionid;
    @BindView(R.id.ll_unionid)
    LinearLayout llUnionid;
    private IWXAPI api;
    private boolean isWeiXin;
    private String access_token;
    private String openid;
    private boolean isOK = false;
    private UserBean userBean;
    private TestAccesTokenResultBean testAccesTokenResultBean;
    private RefreshTokenResultBean refreshTokenResultBean;
    private String refresh_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);//eventbus注册
        ButterKnife.bind(this);
        regitWeiXin();//微信注册
        isWeiXin = isWXAppInstalledAndSupported();//检测微信是否安装
    }

    //eventbus接收微信登陆返回的参数
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TokenBean b) {
        LogUtils.i("openid", b.getOpenid());
        openid = b.getOpenid();
        access_token = b.getAccess_token();
        refresh_token = b.getRefresh_token();
        isOK = true;
        tvType.setText("授权成功！");
        tvId.setText(b.getOpenid());
        tvAccessToken.setText(b.getAccess_token());
        tvRefreshToken.setText(b.getRefresh_token());
    }

    /**
     * 网络连接状态
     *
     * @return true连接，false未连接
     */
    public boolean isInternetConnect() {

        if (StateOfNetWork.getInstance(MainActivity.this).isNetConnected()) return true;
        return false;
    }

    //注册到微信
    public void regitWeiXin() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Contacts.APP_ID, true);
        api.registerApp(Contacts.APP_ID);
        LogUtils.i("zhuce", "zhuce");
    }

    //检测微信是否安装
    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(Contacts.APP_ID);
        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();
        return sIsWXAppInstalledAndSupported;
    }

    @OnClick({R.id.btn_login, R.id.btn_user_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (isInternetConnect()) {
                    gotoWeiXin();
                } else {
                    Toast.makeText(MainActivity.this, "无网络连接！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_user_info:
                if (isInternetConnect() && isOK) {
                    getUserMesg(access_token, openid);//获取用户信息
                } else {
                    Toast.makeText(MainActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //微信授权
    private boolean gotoWeiXin() {
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        boolean b = api.sendReq(req);
        return b;
    }


    /**
     * 获取微信用户的个人信息
     */
    private void getUserMesg(final String myaccess_token, final String myopenid) {
        final String path = "https://api.weixin.qq.com/sns/userinfo";
        RequestParams requestParams = new RequestParams(path);
        requestParams.addParameter("access_token", myaccess_token);
        requestParams.addParameter("openid", myopenid);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtils.i("getUserMesg", "result:" + result);
                parserUser(result);
                tvNickName.setText(userBean.getNickname());
                tvUnionid.setText(userBean.getUnionid());
                if (userBean.getSex() == 1) {
                    tvSex.setText("男");
                } else if (userBean.getSex() == 2) {
                    tvSex.setText("女");
                } else if (userBean.getSex() == 0) {
                    tvSex.setText("未知");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.i("getUserMesg-error", ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    //解析获取用户信息返回的结果
    private void parserUser(String result) {
        Gson gson = new Gson();
        userBean = gson.fromJson(result, UserBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //判断accesstoken是否有效
    /*
    https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
     */
    private void testIsOkAccesToken() {
        if ((!openid.isEmpty()) && (!access_token.isEmpty())) {
            String path = "https://api.weixin.qq.com/sns/auth";
            final RequestParams requestParams = new RequestParams(path);
            requestParams.addParameter("access_token", "ACCESS_TOKEN");
            requestParams.addParameter("openid", openid);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    parseTestAccesTokenResult(result);
                    if (testAccesTokenResultBean.getErrcode() == 0) {
                        //获取用户信息
                        LogUtils.i("testAccesToken", "获取用户信息");
                        //getUserMesg(access_token, openid);
                    } else {
                        //无效则刷新
                        LogUtils.i("testAccesToken", "刷新");
                        //refreshToken();//刷新accesstoken
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        } else {
            LogUtils.i("testAccesToken", openid + "--kong--" + access_token);
            gotoWeiXin();//执行微信授权
        }

    }

    private void parseTestAccesTokenResult(String result) {
        Gson gson = new Gson();
        testAccesTokenResultBean = gson.fromJson(result, TestAccesTokenResultBean.class);
    }

    //刷新token的方法
    public void refreshToken() {
        if ((!openid.isEmpty()) && (!refresh_token.isEmpty())) {
            final String path = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
            final RequestParams requestParams = new RequestParams(path);
            requestParams.addParameter("appid", Contacts.APP_ID);
            requestParams.addParameter("grant_type", "refresh_token");
            requestParams.addParameter("refresh_token", refresh_token);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    LogUtils.i("refreshToken1", result);
                    parseRefreshTokenResult(result);
                    access_token = refreshTokenResultBean.getAccess_token();//获取access_token
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.i("refreshToken-error", "refreshToken-error:" + ex.toString());

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        } else {
            gotoWeiXin();//执行微信授权
        }

    }

    //解析刷新token的返回结果
    private void parseRefreshTokenResult(String result) {
        Gson gson = new Gson();
        refreshTokenResultBean = gson.fromJson(result, RefreshTokenResultBean.class);
    }

}
