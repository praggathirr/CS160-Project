package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;


public class UserFields extends AppCompatActivity {
    EditText totalBudget, billsBudget, foodBudget, entertainmentBudget, otherBudget, monthlyIncome;
    Spinner spinner;
    Button finish;
    BroqueDB broqueDB;
    String jsonMyAccount;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfields);
        System.out.println("onCreateUserFields");
        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
            user = extras.getString("Username");
        }
        Account account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);
        System.out.println("onCreateUserFieldsPrint");
//        spinner = (Spinner) findViewById(R.id.spinner);
        monthlyIncome = (EditText) findViewById(R.id.edt_monthlyincome_userfields);
        totalBudget = (EditText) findViewById(R.id.edt_total_userfields);
        billsBudget = (EditText) findViewById(R.id.edt_bill_userfields);
        foodBudget = (EditText) findViewById(R.id.edt_food_userfields);
        entertainmentBudget = (EditText) findViewById(R.id.edt_entertainment_userfields);
        otherBudget = (EditText) findViewById(R.id.edt_other_userfields);
        finish = (Button) findViewById(R.id.btn_finish_userfields);
        broqueDB = new BroqueDB();
        
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set budget in the database, if user doesn't enter any value for any budget category, set budget to 0

                String mIncome = monthlyIncome.getText().toString();
                String tBudget = totalBudget.getText().toString();
                String bBudget = billsBudget.getText().toString();
                String fBudget = foodBudget.getText().toString();
                String eBudget = entertainmentBudget.getText().toString();
                String oBudget = otherBudget.getText().toString();
                System.out.println(mIncome + " " + tBudget + " " + bBudget + " " + fBudget + " " + eBudget + " " + oBudget);

                if (mIncome.isEmpty()){
                    monthlyIncome.setError("monthly income is empty");
                    monthlyIncome.requestFocus();
                    return;
                }
                if (tBudget.isEmpty()){
                    totalBudget.setError("total is empty");
                    totalBudget.requestFocus();
                    return;
                }
                if (bBudget.isEmpty()){
                    billsBudget.setError("bills is empty");
                    billsBudget.requestFocus();
                    return;
                }
                if (fBudget.isEmpty()){
                    foodBudget.setError("food is empty");
                    foodBudget.requestFocus();
                    return;
                }
                new UserFieldsTask().execute(user, tBudget, bBudget, fBudget, eBudget, oBudget);
                Intent finishIntent = new Intent(UserFields.this, Dashboard.class);
                startActivity(finishIntent);
            }
        });
    }

    public class UserFieldsTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("Userfields start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.insertBudget(args[0], args[1], args[2], args[3], args[4], args[5]);
                System.out.println(s);
            } catch (
                    IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (
                    URISyntaxException e) {
                System.out.println("uriexception caught");
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }//SignUpTask


}