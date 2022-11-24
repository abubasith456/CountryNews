package com.example.countrynews.messages;

import com.example.countrynews.NewsFragment;
import com.example.countrynews.utils.Constant;

public class NewsMessage extends BaseMessage {

    boolean showBack;

    public NewsMessage(boolean showBack) {
        this.showBack = showBack;
    }


    @Override
    public String getFragment() {
        return Constant.FRAGMENT_NEWS;
    }
}
