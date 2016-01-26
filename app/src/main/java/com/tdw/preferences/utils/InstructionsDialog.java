package com.tdw.preferences.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.tdw.preferences.R;

/**
 * Created by akash.jatangi on 1/26/16.
 */
public class InstructionsDialog {
    public static void showDialog(Context mContext,String section){
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle(mContext.getString(R.string.instructions_title));
        if(section.equalsIgnoreCase("E")){
            alertDialog.setMessage(mContext.getString(R.string.instructions_body));
        }
        else if(section.equalsIgnoreCase("F")){
            alertDialog.setMessage(mContext.getString(R.string.instructions_f_body));
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, mContext.getString(R.string.instructions_button_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
