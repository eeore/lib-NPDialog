package com.nespoon.npdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class NPDialog {
    public interface DialogListener {
        void onClickComplete(boolean isOkay);
        void onDismiss();
    }

    public interface DialogSingleChoiceListener {
        void onClickComplete(int selectedIndex, String text);
        void onDismiss();
    }
    /*public static ProgressDialog progress(Context context) {
        ProgressDialog oDialog = new ProgressDialog(context,
            android.R.style.Theme_DeviceDefault_Light_Dialog);
        oDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        oDialog.setMessage("잠시만 기다려 주세요.");

        oDialog.show();
        return oDialog;
    }*/

    public static AlertDialog simple(Context context, String title, String message, String stringOkay, DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title).setMessage(message);

        builder.setPositiveButton(stringOkay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (listener != null) {
                    listener.onClickComplete(true);
                }
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (listener != null) {
                    listener.onDismiss();
                }
            }
        });
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog confirm(Context context, String title, String message, String stringOkay, String stringCancel, DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title).setMessage(message);

        builder.setPositiveButton(stringOkay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (listener != null) {
                    listener.onClickComplete(true);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(stringCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (listener != null) {
                    listener.onClickComplete(false);
                }
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (listener != null) {
                    listener.onDismiss();
                }
            }
        });
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog singleChoice(Context context, String[] list, String title, DialogSingleChoiceListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,
            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        builder.setTitle(title)
            .setItems(list, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    String text = list[which];
                    listener.onClickComplete(which, text);
                }
            })
            .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();


        return dialog;
    }
}
