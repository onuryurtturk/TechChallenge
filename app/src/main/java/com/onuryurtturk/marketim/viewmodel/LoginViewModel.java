package com.onuryurtturk.marketim.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onuryurtturk.marketim.model.User;

public class LoginViewModel extends ViewModel {

   public MutableLiveData<String> username = new MutableLiveData<>();
   public MutableLiveData<String> password = new MutableLiveData<>();

   //variable for remember me function
   public boolean rememberMe = false;

   //current user info
   private MutableLiveData<User> userLiveData;

    /**
     * observing and handling login errors in LoginActivity
     */
   public MutableLiveData<User> getUserInfo(){
       if(userLiveData == null){
           userLiveData = new MutableLiveData<>();
       }
       return userLiveData;
   }

    /**
     * Login button click method
     * updates current user live data
     */
   public void onClick(){
       userLiveData.setValue(new User(username.getValue(), password.getValue()));
   }

    /**
     * @param checked * represents switch's current state
     * Switch state change handler method
     */
    public void onCheckedChanged(boolean checked) {
        rememberMe = checked;
    }

}
