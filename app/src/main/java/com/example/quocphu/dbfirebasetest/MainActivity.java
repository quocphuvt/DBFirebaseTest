package com.example.quocphu.dbfirebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button bt;
    TextView tv;
    FirebaseDatabase database;
    DatabaseReference nut_ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        bt = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        database = FirebaseDatabase.getInstance();
        nut_ten = database.getReference("ten");
        //doc du lieu tu database len
        nut_ten.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nut_ten.setValue(et.getText().toString());
            }
        });

        DatabaseReference nut_user = database.getReference("users");
        List<User> ds_user = new ArrayList<>();
        ds_user.add(new User("quocphu","lequocphu"));
        ds_user.add(new User("quocphong","lequocphong"));
        for(int i = 0; i<ds_user.size();i++){
            nut_user.push().setValue(ds_user.get(i));
        }









    }


}
