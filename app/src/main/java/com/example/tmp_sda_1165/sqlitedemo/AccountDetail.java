package com.example.tmp_sda_1165.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountDetail extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnDelete;
    private Button btnClose;
    private EditText etLabel;
    private EditText etDate;
    private EditText etCount;
    private int _item_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        etLabel = (EditText) findViewById(R.id.etLabel);
        etDate = (EditText) findViewById(R.id.etDate);
        etCount = (EditText) findViewById(R.id.etCount);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        
        _item_id = 0;
        Intent intent = getIntent();
        _item_id = intent.getIntExtra("item_Id", 0);
        AccountImp repo = new AccountImp(this);
        Account account = new Account();
        account = repo.getItemById(_item_id);

        etCount.setText(String.valueOf(account.count));
        etLabel.setText(account.label);
        etDate.setText(account.date);

    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btnSave)) {
            AccountImp repo = new AccountImp(this);
            Account account = new Account();
            account.count = Integer.parseInt(etCount.getText().toString());
            account.date = etDate.getText().toString();
            account.label = etLabel.getText().toString();
            account.account_ID = _item_id;

            if (_item_id == 0) {
                _item_id = repo.insert(account);
                Toast.makeText(this, "New Account Insert", Toast.LENGTH_SHORT).show();
            } else {
                repo.update(account);
                Toast.makeText(this, "Account Record updated", Toast.LENGTH_SHORT).show();
            }
        } else if (v == findViewById(R.id.btnDelete)) {
            AccountImp repo = new AccountImp(this);
            repo.delete(_item_id);
            Toast.makeText(this, "Account Record Deleted", Toast.LENGTH_SHORT);
            finish();
        } else if (v == findViewById(R.id.btnClose)) {
            finish();
        }
    }

}
