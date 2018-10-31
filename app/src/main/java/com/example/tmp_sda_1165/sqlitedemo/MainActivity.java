package com.example.tmp_sda_1165.sqlitedemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener {

    private Button btnAdd,btnGetAll;
    private TextView item_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        onClick(btnGetAll);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onClick(btnGetAll);
    }

    @Override
    public void onClick(View v) {
        if (v== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,AccountDetail.class);
            intent.putExtra("item_Id",0);
            startActivity(intent);

        }else {

            AccountImp repo = new AccountImp(this);

            ArrayList<HashMap<String, String>> itemList =  repo.getAccountList();
            if(itemList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        item_view = (TextView) view.findViewById(R.id.item_Id);
                        String itemId = item_view.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),AccountDetail.class);
                        objIndent.putExtra("item_Id", Integer.parseInt( itemId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,itemList, R.layout.view_account_entry, new String[] { "id", "label", "date", "count"}, new int[] {R.id.item_Id, R.id.label, R.id.date, R.id.count});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this, "No item!", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
