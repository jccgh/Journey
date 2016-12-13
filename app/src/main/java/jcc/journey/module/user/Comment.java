package jcc.journey.module.user;

import cn.bmob.v3.BmobObject;

/**
 * Created by JCC on 2016/12/13.
 */

public class Comment extends BmobObject{
    private String comment;
    private MyUser user;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
