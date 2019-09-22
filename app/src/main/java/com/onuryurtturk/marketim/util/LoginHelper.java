package com.onuryurtturk.marketim.util;


import android.content.Context;
import android.content.SharedPreferences;

import com.onuryurtturk.marketim.model.User;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static android.content.Context.MODE_PRIVATE;

public class LoginHelper {

    private Pattern pattern;

    private final String DEF_USERNAME = "kariyer";
    private final String DEF_PASSWORD = "2019ADev";

    private final String prefName = "Login";
    private final String TAG_Username = "username";
    private final String TAG_Password = "password";

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    public LoginHelper(){
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    public boolean validateUserName(String username){
        Matcher matcher = pattern.matcher(username);
        return matcher.matches() && username.equals(DEF_USERNAME);
    }

    public boolean validatePassword(String password){
        return password.length() > 0 && password.equals(DEF_PASSWORD);
    }

    public void saveUserInfo(Context context, User user){
        SharedPreferences mPref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mPref.edit();
        mEditor.putString(TAG_Username,user.getUserName());
        mEditor.putString(TAG_Password,user.getPassword());
        mEditor.apply();
    }


    public boolean isUserStored(Context context){
        SharedPreferences mPreferences=context.getSharedPreferences(prefName, MODE_PRIVATE);
        return Objects.equals(mPreferences.getString(TAG_Username, ""), DEF_USERNAME) &&
                Objects.equals(mPreferences.getString(TAG_Password, ""), DEF_PASSWORD);

    }

    public void deleteStoredUser(Context context){
        SharedPreferences mPreferences=context.getSharedPreferences(prefName, MODE_PRIVATE);
        mPreferences.edit().clear().apply();

    }

}
