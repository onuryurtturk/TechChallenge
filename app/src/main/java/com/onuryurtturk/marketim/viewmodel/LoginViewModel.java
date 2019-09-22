package com.onuryurtturk.marketim.viewmodel;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onuryurtturk.marketim.model.User;

public class LoginViewModel extends ViewModel {

   public MutableLiveData<String> username = new MutableLiveData<>();
   public MutableLiveData<String> password = new MutableLiveData<>();
   public boolean rememberMe = false;

   private MutableLiveData<User> userLiveData;

   public MutableLiveData<User> getUserInfo(){
       if(userLiveData == null){
           userLiveData = new MutableLiveData<>();
       }
       return userLiveData;

   }

   public void onClick(View view){
       User user = new User(username.getValue(), password.getValue());
       userLiveData.setValue(user);
   }

    public void onCheckedChanged(boolean checked) {
        rememberMe = checked;
    }

}
