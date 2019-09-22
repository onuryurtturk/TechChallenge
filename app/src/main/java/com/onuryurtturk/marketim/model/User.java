package com.onuryurtturk.marketim.model;

import androidx.annotation.NonNull;

import com.onuryurtturk.marketim.util.LoginHelper;

public class User {

    @NonNull
    private String mUserName;
    @NonNull
    private String mPassword;

    public User(@NonNull final String username, @NonNull final String password) {
        mUserName = username;
        mPassword = password;
    }

    @NonNull
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(@NonNull final String username) {
        mUserName = username;
    }

    @NonNull
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(@NonNull final String password) {
        mPassword = password;
    }

    public boolean isUserNameValid() {
        return new LoginHelper().validateUserName(mUserName);
    }

    public boolean isPasswordValid() {
        return new LoginHelper().validatePassword(mPassword);
    }



}
