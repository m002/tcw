package com.hhgs.tcw.Model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by MFH on 2016/11/28 0028.
 */

@Table(name = "jpmessage", execAfterTableCreated = "")
public class JPMessage {
    @Id
    private java.lang.String id;
    @Column
    private java.lang.String title;
    @Column
    private java.lang.String msg;
    @Column
    private long date;
//    @Transient
//    private java.lang.String check;


    //这里必须要有一个无参构造函数，DBUtils需要
    public JPMessage() {
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String data) {
        this.msg = data;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getCheck() {
//        return check;
//    }
//
//    public void setCheck(String check) {
//        this.check = check;
//    }
}
