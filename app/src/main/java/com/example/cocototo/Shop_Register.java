package com.example.cocototo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Shop_Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mOwnerName, mAddress, mTelephone, mShopName, mUsername, mPassword, mDescription;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);

        mOwnerName   = findViewById(R.id.ownername);
        mAddress     = findViewById(R.id.address);
        mTelephone   = findViewById(R.id.telephone);
        mShopName    = findViewById(R.id.shopname);
        mUsername    = findViewById(R.id.username);
        mPassword    = findViewById(R.id.password);
        mDescription = findViewById(R.id.desciption);

        mRegisterBtn = findViewById(R.id.reg_btn);
        mLoginBtn    = findViewById(R.id.new_here);

        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username   = mUsername.getText().toString().trim();
                String password   = mPassword.getText().toString().trim();
                String ownername  = mOwnerName.getText().toString().trim();
                String address    = mAddress.getText().toString().trim();
                String telephone  = mTelephone.getText().toString().trim();
                String shopname   = mShopName.getText().toString().trim();
                String desciption = mDescription.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    mUsername.setError("User Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Requited");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be more than 6 characters");
                    return;
                }

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Shop_Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("ownername",ownername);
                            user.put("address",address);
                            user.put("telephone",telephone);
                            user.put("shopname",shopname);
                            user.put("desciption",desciption);
                            user.put("email",username);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for "+userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Shop_Dashboard.class));

                        }else{
                            Toast.makeText(Shop_Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Shop_Login.class));
            }
        });
    }
}