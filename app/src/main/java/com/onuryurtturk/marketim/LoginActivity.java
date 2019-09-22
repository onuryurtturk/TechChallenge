package com.onuryurtturk.marketim;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.onuryurtturk.marketim.databinding.ActivityLoginBinding;
import com.onuryurtturk.marketim.model.User;
import com.onuryurtturk.marketim.util.LoginHelper;
import com.onuryurtturk.marketim.viewmodel.LoginViewModel;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;
    private LoginHelper loginValidator;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginValidator = new LoginHelper();
        if (loginValidator.isUserStored(LoginActivity.this)) {
            completeLoginOperation();
        } else {
            loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
            activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
            activityLoginBinding.setLifecycleOwner(this);
            activityLoginBinding.setViewModel(loginViewModel);
            loginViewModel.getUserInfo().observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (TextUtils.isEmpty(Objects.requireNonNull(user).getUserName())) {
                        activityLoginBinding.edtUserName.setError(getResources().getString(R.string.username_empty));
                        activityLoginBinding.edtUserName.requestFocus();
                    } else if (!user.isUserNameValid()) {
                        activityLoginBinding.edtUserName.setError(getResources().getString(R.string.username_wrong));
                        activityLoginBinding.edtUserName.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                        activityLoginBinding.edtPassword.setError(getResources().getString(R.string.password_empty));
                        activityLoginBinding.edtPassword.requestFocus();
                    } else if (!user.isPasswordValid()) {
                        activityLoginBinding.edtPassword.setError(getResources().getString(R.string.password_wrong));
                        activityLoginBinding.edtPassword.requestFocus();
                    } else {
                        if(loginViewModel.rememberMe) {
                            loginValidator.saveUserInfo(LoginActivity.this, user);
                        }
                        completeLoginOperation();
                    }
                }
            });
        }
    }

    private void completeLoginOperation() {
        Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_successfull), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MyOrdersActivity.class));
        LoginActivity.this.finish();

    }

}
