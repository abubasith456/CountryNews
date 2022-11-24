package com.example.countrynews.messages;

import com.example.countrynews.utils.Constant;

public class RegisterMessage extends BaseMessage {

    boolean showBack;

    public RegisterMessage(boolean showBack) {
        this.showBack = showBack;
    }

    public boolean isShowBack() {
        return showBack;
    }

    @Override
    public String getFragment() {
        return Constant.FRAGMENT_REGISTER;
    }
}
