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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //nut chuoi
        DatabaseReference nut_chuoi=database.getReference("nutchuoi");
        nut_chuoi.setValue("nut chuoi moi");

//nut so
        DatabaseReference nut_so=database.getReference("nutso");
        nut_so.setValue(8);

//nut Map
        DatabaseReference nut_map=database.getReference("nutmap");
        Map<String,Object> m=new HashMap<String,Object>();
        m.put("tensv","nguyen van teo");
        m.put("tuoi",5);
        m.put("diachi","30/31 duong 3 thang 2");
        nut_map.setValue(m);

//nut List
        DatabaseReference nut_list=database.getReference("nut_list");
        List<String> list=new ArrayList<String>();
        list.add("muc thu 1");
        list.add("muc thu 2");
        nut_list.setValue(list);
        //nut conCho
        DatabaseReference nut_list_cho=database.getReference("nut_list_cho");
        List<ConCho> list_cho=new ArrayList<ConCho>();
        list_cho.add(new ConCho("ki","trang xam",2));
        list_cho.add(new ConCho("na","vang den",4));
        nut_list_cho.setValue(list_cho);




    }
    public class ConCho {
        public String key;
        public String ten;
        public String maulong;
        public int tuoi;
        public ConCho()
        {
            //phai co 1 ham tao khong doi so
        }
        public ConCho(String ten, String maulong,int tuoi)
        {
            this.ten=ten;
            this.tuoi=tuoi;
            this.maulong=maulong;
        }
    }
}
