package com.example.evento;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    ImageView dp;
    EditText user_name,user_email;
    Button btn_update;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view =  inflater.inflate(R.layout.fragment_profile,container,false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        dp = view.findViewById(R.id.ProfileImage);
        user_name = view.findViewById(R.id.ProfileName);
        user_email = view.findViewById(R.id.ProfileEmail);
        btn_update = view.findViewById(R.id.btnupdate);

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference childreference = databaseReference.child("Users").child(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("User Profile Images").child(firebaseAuth.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(dp);

            }
        });




        childreference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);


                if (userProfile != null) {
                    user_name.setText("  "+userProfile.getUsername());
                    user_email.setText("  "+userProfile.getUseremail());
                }
                else
                {
                    user_name.setText("");
                    user_email.setText("");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());

                String email = user_email.getText().toString().trim();
                String name = user_name.getText().toString().trim();

                UserProfile userProfile = new UserProfile(email,name);
                reference.setValue(userProfile);

                Toast toast = Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });



        return view;

    }
}
