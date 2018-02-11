package com.example.christophergu.vetories;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // Firebase Instance Variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseStorage mFirebaseStorage;

    private TextView storyHeader;
    private TextView storyTitle;
    private TextView storyContent;
    private TextView audioHeader;
    private TextView videoHeader;
    private TextView profileHeader;
    private LinearLayout profileContent;
    private TextView profileName;
    private TextView profileGender;
    private TextView profileDOB;
    private TextView profileVeteranExperience;
    private TextView contactHeader;
    private LinearLayout contactContent;
    private TextView contactEmail;
    private TextView contactPhone;
    private ImageView addStoryButton;

    private Vetory vetory;
    private ArrayList<Vetory> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vetory = new Vetory();

        //Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mFirebaseDatabase.getReference("stories");
        mFirebaseStorage = FirebaseStorage.getInstance();

        storyHeader = findViewById(R.id.story_header);
        storyTitle = findViewById(R.id.story_title);
        storyContent = findViewById(R.id.story_content);
        audioHeader = findViewById(R.id.audio_header);
        videoHeader = findViewById(R.id.video_header);
        profileHeader = findViewById(R.id.profile_header);
        profileContent = findViewById(R.id.profile_content);
        profileName = findViewById(R.id.profile_name);
        profileGender = findViewById(R.id.profile_gender);
        profileDOB = findViewById(R.id.profile_dob);
        profileVeteranExperience = findViewById(R.id.profile_veteran_experience);
        contactHeader = findViewById(R.id.contact_header);
        contactContent = findViewById(R.id.contact_content);
        contactEmail = findViewById(R.id.contact_email);
        contactPhone = findViewById(R.id.contact_phone);

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot imageSnapshot : dataSnapshot.getChildren()) {
                    Vetory vetory = imageSnapshot.getValue(Vetory.class);
                    array.add(vetory);
                }
                Collections.shuffle(array);
                int size = array.size();
                Log.i("Size", Integer.toString(size));
                vetory = array.get(0);
                storyTitle.setText(vetory.getStoryTitle());
                storyContent.setText(vetory.getStoryContent());
                profileName.setText(vetory.getName());
                profileGender.setText(vetory.getGender());
                profileDOB.setText(vetory.getDOB());
                profileVeteranExperience.setText(vetory.getVeteranExperience());
                contactEmail.setText(vetory.getEmail());
                contactPhone.setText(vetory.getPhone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        collapsed();

        storyHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storyContent.getVisibility() == View.VISIBLE) {
                    storyTitle.setVisibility(View.GONE);
                    storyContent.setVisibility(View.GONE);
                } else {
                    storyTitle.setVisibility(View.VISIBLE);
                    storyContent.setVisibility(View.VISIBLE);
                }
            }
        });

        profileHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileContent.getVisibility() == View.VISIBLE) {
                    profileContent.setVisibility(View.GONE);
                } else {
                    profileContent.setVisibility(View.VISIBLE);
                }
            }
        });

        contactHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactContent.getVisibility() == View.VISIBLE) {
                    contactContent.setVisibility(View.GONE);
                } else {
                    contactContent.setVisibility(View.VISIBLE);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                recreate();
                return true;
            }
        });
    }

    public void collapsed() {
        storyHeader.setVisibility(View.VISIBLE);
        storyTitle.setVisibility(View.GONE);
        storyContent.setVisibility(View.GONE);
        audioHeader.setVisibility(View.VISIBLE);
        videoHeader.setVisibility(View.VISIBLE);
        profileHeader.setVisibility(View.VISIBLE);
        profileContent.setVisibility(View.GONE);
        contactHeader.setVisibility(View.VISIBLE);
        contactContent.setVisibility(View.GONE);
    }
}