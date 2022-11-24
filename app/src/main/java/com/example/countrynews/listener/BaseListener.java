package com.example.countrynews.listener;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public interface BaseListener {

    void showDialogWithSingleButton(String titleMessage, String message, String buttonText, MaterialDialog.SingleButtonCallback callback);

    void showDialogWithTwoButton(String titleMessage, String message, String positiveButtonText,String negativeButtonText, MaterialDialog.SingleButtonCallback positiveButton, MaterialDialog.SingleButtonCallback negativeButton);

    void closeOpenedDialog();
}
