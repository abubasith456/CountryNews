package com.example.countrynews.test;

import com.example.countrynews.model.news.NewsHeadLines;

import java.util.List;

public interface OnFetchDataListener<NewsResponse> {

    void onFetchData(List<NewsHeadLines> list,String message);

    void onErrorMessage(String message);

}
