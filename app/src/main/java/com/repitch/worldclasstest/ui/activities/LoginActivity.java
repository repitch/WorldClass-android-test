package com.repitch.worldclasstest.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.repitch.worldclasstest.Constants;
import com.repitch.worldclasstest.DataManager;
import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.SharedPrefsManager;
import com.repitch.worldclasstest.network.RFManager;
import com.repitch.worldclasstest.network.models.AuthResult;
import com.repitch.worldclasstest.network.models.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by repitch on 25.05.16.
 */
public class LoginActivity extends BaseActivity implements Callback<AuthResult> {

    private EditText mEditLogin, mEditPassword;
    private Button mBtnLogin;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initViews();

        if (DataManager.getInstance().mAuthToken != null) {
            String key = DataManager.getInstance().mAuthToken.key;
            if (key != null) {
                mProgress.show();
                // проверка на валидность ключа
                RFManager.getCustomer(key, new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        mProgress.hide();
                        if (response.isSuccess()) {
                            DataManager.getInstance().mCustomer = response.body();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Snackbar.make(mBtnLogin, "Вероятно токен устарел. Введите логин/пароль", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        mProgress.hide();
                        Snackbar.make(mBtnLogin, "internal error getCustomer", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private void findViews() {
        mEditLogin = (EditText) findViewById(R.id.edit_login);
        mEditPassword = (EditText) findViewById(R.id.edit_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initViews() {
        mEditLogin.setText(Constants.TEST_LOGIN);
        mEditLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isBtnLoginEnabled();
            }
        });
        mEditPassword.setText(Constants.TEST_PASS);
        mEditPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isBtnLoginEnabled();
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mEditLogin.getText().toString();
                String password = mEditPassword.getText().toString();
                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Введите логин/пароль", Toast.LENGTH_LONG).show();
                    return;
                }
                // посылаем запрос на сервер и ждем результата
                mProgress.show();
                RFManager.auth(login, password, LoginActivity.this);
            }
        });

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Подождите, пожалуйста...");
        mProgress.setCancelable(false);

    }

    private void isBtnLoginEnabled() {
        boolean isEnabled = !(mEditLogin.getText().toString().isEmpty() ||
                mEditPassword.getText().toString().isEmpty());
        mBtnLogin.setEnabled(isEnabled);
    }

    @Override
    public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
        mProgress.hide();
        if (response.isSuccess()) {
            Log.e("","");
            AuthResult result = response.body();
            if (result.isOk()) {
                // все норм
                DataManager.getInstance().mAuthToken = result.authToken;
                SharedPrefsManager.saveAuthToken();
                DataManager.getInstance().mCustomer = result.customer;
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Snackbar.make(mBtnLogin, result.getResultReadable(this), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<AuthResult> call, Throwable t) {
        mProgress.hide();
        Log.e("","");
        Snackbar.make(mBtnLogin, "internal error, try again", Snackbar.LENGTH_SHORT).show();
    }
}
