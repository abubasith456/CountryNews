package com.example.countrynews.messages;

import com.example.countrynews.utils.Constant;

public class LoginMessages extends BaseMessage {

    public boolean showBack;

    public LoginMessages(boolean showBack) {
        this.showBack = showBack;
    }

    @Override
    public String getFragment() {
        return Constant.FRAGMENT_LOGIN;
    }
}
