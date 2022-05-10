package com.example.cocototo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Shop_Update extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView Profileownername, Profileaddress, Profiletelephone, Profileshopname, Profileusername, Profiledesciption;
    Button update_btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_update);

//        Intent data = getIntent();
//        String ownername  = data.getStringExtra("ownername");
//        String shopname   = data.getStringExtra("shopname");
//        String telephone  = data.getStringExtra("telephone");
//        String address    = data.getStringExtra("address");
//        String email      = data.getStringExtra("email");
//        String desciption = data.getStringExtra("description");

        fAuth             = FirebaseAuth.getInstance();
        fStore            = FirebaseFirestore.getInstance();
        user              = fAuth.getCurrentUser();

        Profileownername  = findViewById(R.id.Profileownername);
        Profileaddress    = findViewById(R.id.Profileaddress);
        Profiletelephone  = findViewById(R.id.Profiletelephone);
        Profileshopname   = findViewById(R.id.Profileshopname);
        Profileusername   = findViewById(R.id.Profileusername);
        Profiledesciption = findViewById(R.id.Profiledesciption);

        update_btn        = findViewById(R.id.update_btn);

//        update_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Profileownername.getText().toString().isEmpty() || Profileaddress.getText().toString().isEmpty() || Profiletelephone.getText().toString().isEmpty() || Profileshopname.getText().toString().isEmpty() || Profileusername.getText().toString().isEmpty() || Profiledesciption.getText().toString().isEmpty());
//                Toast.makeText(Shop_Update.this, "One or Many Fields are Empty", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            String email = Profileusername.getText().toString();
//        });
//
//        Profileownername.setText(ownername);
//        Profileaddress.setText(address);
//        Profiletelephone.setText(telephone);
//        Profileshopname.setText(shopname);
//        Profileusername.setText(email);
//        Profiledesciption.setText(desciption);
//
//        Log.d(TAG,"onCreate: " + ownername + " " + shopname + " " + telephone + " " + address + " " + email + " " + desciption);

    }
}