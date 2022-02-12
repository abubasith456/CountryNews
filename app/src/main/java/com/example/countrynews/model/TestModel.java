package com.example.countrynews.model;

import java.util.ArrayList;

public class TestModel {

    private String status;
    public int totalResult;
    private ArrayList<NewsModel> arrayList;

    public TestModel(String status, int totalResult, ArrayList<NewsModel> arrayList) {
        this.status = status;
        this.totalResult = totalResult;
        this.arrayList = arrayList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<NewsModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<NewsModel> arrayList) {
        this.arrayList = arrayList;
    }
}
