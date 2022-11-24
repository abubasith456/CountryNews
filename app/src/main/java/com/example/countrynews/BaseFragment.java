package com.example.countrynews;

import android.graphics.Color;
import android.os.Bundle;
import android.text.util.Linkify;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.countrynews.listener.BaseListener;

public class BaseFragment extends Fragment implements BaseListener {

    MaterialDialog alertDialog;
    MaterialDialog confirmDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void showDialogWithSingleButton(String titleMessage, String message, String buttonText, MaterialDialog.SingleButtonCallback callback) {
        alertDialog = new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .title(titleMessage)
                .content(message)
                .contentColor(Color.BLACK)
                .canceledOnTouchOutside(false)
                .positiveColor(Color.BLACK)
                .positiveText(buttonText)
                .onPositive(callback)
                .build();
        alertDialog.getTitleView().setTextSize(16);
        alertDialog.getContentView().setTextSize(14);
        alertDialog.show();
    }

    @Override
    public void showDialogWithTwoButton(String titleMessage, String message, String positiveButtonText, String negativeButtonText, MaterialDialog.SingleButtonCallback positiveButton, MaterialDialog.SingleButtonCallback negativeButton) {
        closeOpenedDialog();
        confirmDialog = new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .title(titleMessage)
                .content(message)
                .contentColor(Color.BLACK)
                .canceledOnTouchOutside(false)
                .positiveColor(Color.BLACK)
                .negativeColor(Color.BLACK)
                .positiveText(positiveButtonText)
                .negativeText(negativeButtonText)
                .onPositive(positiveButton)
                .onNegative(negativeButton)
                .build();
        confirmDialog.getTitleView().setTextSize(16);
        confirmDialog.getContentView().setTextSize(14);
        confirmDialog.show();
    }

    @Override
    public void closeOpenedDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (confirmDialog != null) {
            confirmDialog.dismiss();
        }
    }


}
