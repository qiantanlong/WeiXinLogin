package com.zhengsheng.administrator.childteach.bean;

import java.util.List;

/**
 * Created by yuhongzhen on 2016/10/8.
 */

public class UserBean {

    /**
     * openid : oPC_9sjsCy_pewUcsAYKfqqk4ejo
     * nickname : 尉老师
     * sex : 1
     * language : zh_CN
     * city : Shijiazhuang
     * province : Hebei
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/Q3auHgzwzM6v1mUeD9k258vII17LBRgAG8eL0YppyPZBZxH8ZOm7CEKeBD2Ph8nibYuIu04Fu6tkbhqwzhDe8vA/0
     * privilege : []
     * unionid : oDhn7wmxgpctvmWGAApYa8FGqXTQ
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
