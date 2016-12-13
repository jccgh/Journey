package jcc.journey.module.user;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/10/31.
 */

public class Like extends BmobObject {

    private String title;
    private MyUser user;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
