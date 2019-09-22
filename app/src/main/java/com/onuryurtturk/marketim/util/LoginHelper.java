package com.onuryurtturk.marketim.util;


import android.content.Context;
import android.content.SharedPreferences;
import com.onuryurtturk.marketim.model.User;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static android.content.Context.MODE_PRIVATE;

public class LoginHelper {

    //Username pattern for validating usernames
    private Pattern pattern;

    //Default user informations
    private final String DEF_USERNAME = "kariyer";
    private final String DEF_PASSWORD = "2019ADev";

    //SharedPreferences tags for edit and delete operations
    private final String prefName = "Login";
    private final String TAG_Username = "username";
    private final String TAG_Password = "password";

    //username pattern
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    public LoginHelper(){
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    /**
     * Validates username
     * @param username - user input
     * Control whether Check user input  with pattern
     */
    public boolean validateUserName(String username){
        Matcher matcher = pattern.matcher(username);
        return matcher.matches() && username.equals(DEF_USERNAME);
    }

    /**
     * Validates password
     * @param password - user input
     * Control whether Check user input  with pattern
     */
    public boolean validatePassword(String password){
        return password.length() > 0 && password.equals(DEF_PASSWORD);
    }

    /**
     * Store user info if remember me checked
     * @param context - used for init shared preferences
     * @param user - user info to save
     */
    public void saveUserInfo(Context context, User user){
        SharedPreferences mPref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mPref.edit();
        mEditor.putString(TAG_Username,user.getUserName());
        mEditor.putString(TAG_Password,user.getPassword());
        mEditor.apply();
    }

    /**
     * Check if user stored before
     * @param context - used for init shared preferences
     */
    public boolean isUserStored(Context context){
        SharedPreferences mPreferences=context.getSharedPreferences(prefName, MODE_PRIVATE);
        return Objects.equals(mPreferences.getString(TAG_Username, ""), DEF_USERNAME) &&
                Objects.equals(mPreferences.getString(TAG_Password, ""), DEF_PASSWORD);

    }

    /**
     * Delete stored user data
     * @param context - used for init shared preferences
     * Used in logout process
     */
    public void deleteStoredUser(Context context){
        SharedPreferences mPreferences=context.getSharedPreferences(prefName, MODE_PRIVATE);
        mPreferences.edit().clear().apply();

    }

}
