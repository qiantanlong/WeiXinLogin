package com.zhengsheng.administrator.childteach.bean;

/**
 * Created by yuhongzhen on 2016/10/8.
 */

public class TokenBean {

    /**
     * access_token : bfVnjtNIosh7-GVt2J6E8SukEkmIUl7m7zmEkTSesvxFX32T1MapYea5XkRGYyn6m5h2pYI1gy50unYxWrU3gMhluubpk4KRs0DxUIrEqOo
     * expires_in : 7200
     * refresh_token : kMkzVgsRlNopMHetoZjvoJX1k--TUJNZ6MSnyGl4Owr7ZjCXVdltkjaUKtN5Kq__knZI65LU4lkep3IVyAw91-clCeeKoobnl_1Gdaboa4w
     * openid : oPC_9sjsCy_pewUcsAYKfqqk4ejo
     * scope : snsapi_userinfo
     * unionid : oDhn7wmxgpctvmWGAApYa8FGqXTQ
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
