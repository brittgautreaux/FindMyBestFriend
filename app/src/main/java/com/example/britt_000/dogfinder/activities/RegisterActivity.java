package com.example.britt_000.dogfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.example.britt_000.dogfinder.R;
import com.example.britt_000.dogfinder.helper.InputValidation;
import com.example.britt_000.dogfinder.model.User;
import com.example.britt_000.dogfinder.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputConfirmPassword);

        // Sets text input to display password values
        // Have to do this bc inputType="password" doesn't show hint
        textInputEditTextPassword.setTransformationMethod(new PasswordTransformationMethod());
        textInputEditTextConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite(){
        if(!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutEmail, getString(R.string.error_field_required))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_invalid_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_field_required))){
            return;
        }
        if(!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword, getString(R.string.error_password_match))){
            return;
        }
        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            // Direct to user to userActivity
            Intent accountsIntent = new Intent(activity, UserAreaActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else{
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
