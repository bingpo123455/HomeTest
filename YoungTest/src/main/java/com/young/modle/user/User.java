package com.young.modle.user;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private int uid;
    private String phone;
    private String nick;
    private String avatar;
    private String tfs;
    private String passwd;
    private int pwdsafestatus;
    private int logintype;
    private int utype;
    private String thirdid;
    private Date lastlogintime;
    private Date createtime;
    private Date modifytime;
    private int state;
    private int disableuserid;
    private Date disabletime;
    private String openid;
    private int isspam;
    private int transfertype;
    private Date transfertime;
    private int gid;
    private Date gradeupdatetime;


}
