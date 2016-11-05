package com.zhengsheng.administrator.childteach.bean;

/**
 * Created by yuhongzhen on 2016/10/9.
 */

public class RefreshTokenResultBean {

    /**
     * openid : oPC_9shYeGIRVwVISFMrz9YmXcmw
     * access_token : r9iZ-wu3-0XuLLj37nsjKzYVv6SMNibd9aa7zP-sxte2IClJuDD-dmGdnMqvKwhIzozm4d0hv946IQE2WUZhzQ79zPAOnhIqF5y0yC89u8w
     * expires_in : 7200
     * refresh_token : r9iZ-wu3-0XuLLj37nsjKxPqPZE8OLiPAMpO1MMxtYy2s8U8iXmjZ3eLDg9g4I_q3kvfUrsqhYAlpGXi0jy5t0F7vd6AdNWr5m0ceTMnY4s
     * scope : snsapi_base,snsapi_userinfo,
     */

    private String openid;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String scope;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
