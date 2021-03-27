package com.Rai.studycenter.firebase.ui.frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.firebaseutils.firebaseMenu;
import com.Rai.studycenter.firebase.login.Login;
import com.Rai.studycenter.firebase.ui.FirebaseMenuActivity;
import com.Rai.studycenter.utils.NetworUtils;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.Rai.studycenter.constant.Constant.CollegeKey;
import static com.Rai.studycenter.constant.Constant.SharedPref;
import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public class profile extends Fragment  implements firebaseMenu {
    FirebaseAuth mAuth ;
    String currentId;
    TextView fbTVname,fbTVemail,profileId,TVNotification;
    CircleImageView fbProifleImg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    StartClass startClass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth= FirebaseAuth.getInstance();
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        startClass=new StartClass();
        ref = database.getReference("users");
        fbProifleImg=(CircleImageView) view.findViewById(R.id.fb_menu_profile_image);
        fbTVname=view.findViewById(R.id.fb_menu_username);
        fbTVemail=view.findViewById(R.id.fb_menu_email);
        profileId=view.findViewById(R.id.profile_id);
        TVNotification=view.findViewById(R.id.profile_notification);


        return view;
    }

    @Override
    public void onStart() {
        NetworUtils b = new NetworUtils();

        if(b.isNetworkAvailable(getActivity())==true){
            FirebaseUser currentUser = mAuth.getCurrentUser();
            //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            updateUI(currentUser);

        }
        else {
            Toast.makeText(getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
        }

        super.onStart();

            if(startClass.getUniqueID(getActivity()).equals("not found")){
                currentId=null;
                profileId.setText("Generate \nyour college id \nfrom next activity");

            }
            else {
                currentId=startClass.getUniqueID(getActivity());
                profileId.setText("Your current \nCollege id \nis : "+currentId);
                firebaseGetUpdates();
            }

    }

    @Override
    public void getAuth() {
        fbTVname.setText(mAuth.getCurrentUser().getDisplayName());
        fbTVemail.setText(mAuth.getCurrentUser().getEmail());
        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).placeholder(R.drawable.user_avatar).dontAnimate().into(fbProifleImg);
    }


    void firebaseGetUpdates(){
        if(currentId==null){

        }
        else {
            ref.child(currentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int i=0;
                    for(DataSnapshot data:snapshot.getChildren()){
                        i++;
                        TVNotification.setText("You uploaded "+i+" books in "+data.child("semName").getValue().toString());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            getAuth();


        } else {
            Toast.makeText(getContext(), "Please Sign in first", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getActivity(), Login.class);
            startActivity(intent);

        }
    }
}