package com.kamo.roomdemo.Util;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

public class UtilTool {

    public static boolean isEmpty(EditText text) {
        CharSequence s=text.getText().toString();
        return TextUtils.isEmpty(s);
    }

    public static boolean isEmail(EditText email) {
        CharSequence s=email.getText().toString();
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        return (!TextUtils.isEmpty(s)&& EMAIL_ADDRESS_PATTERN.matcher(s).matches());
    }


    public static boolean isPhoneNumber(EditText phone) {
        CharSequence s=phone.getText().toString();
        return (!TextUtils.isEmpty(s)&&(s.toString().length()==10));
    }
}
