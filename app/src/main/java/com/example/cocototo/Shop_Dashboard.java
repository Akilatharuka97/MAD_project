package com.example.cocototo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Shop_Dashboard extends AppCompatActivity {

    TextView ownername, shopname, telephone, address, email, description;
    FirebaseAuth fAuth;
    TextView profileBTN;
    ImageView profileImage;
    FirebaseFirestore fStore;
    String userID;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dashboard);

        ownername    = findViewById(R.id.dashboardusername);
        shopname     = findViewById(R.id.dashboardshopname);
        telephone    = findViewById(R.id.dashboardphone);
        address      = findViewById(R.id.dashboardaddress);
        email        = findViewById(R.id.dashboardemail);
        description  = findViewById(R.id.dashboarddescription);
        profileBTN   = findViewById(R.id.profilebtn);
        profileImage = findViewById(R.id.profilephoto);
        storageReference = FirebaseStorage.getInstance().getReference();

        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();

        userID       = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                ownername.setText(documentSnapshot.getString("ownername"));
                shopname.setText(documentSnapshot.getString("shopname"));
                telephone.setText(documentSnapshot.getString("telephone"));
                address.setText(documentSnapshot.getString("address"));
                email.setText(documentSnapshot.getString("email"));
                description.setText(documentSnapshot.getString("desciption"));
            }
        });

        profileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000 );
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);

            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        StorageReference fileRef = storageReference.child("profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Shop_Dashboard.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Shop_Dashboard.this, "Image Upload Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Shop_Login.class));
        finish();
    }
}