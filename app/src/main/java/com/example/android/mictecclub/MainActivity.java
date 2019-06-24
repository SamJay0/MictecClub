package com.example.android.mictecclub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mictecclub.Data.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
private ActionBarDrawerToggle toggle;
    private String TAG="MictecClub-> APP";

    private GoogleSignInClient googleSignInClient;

    public static final String GOOGLE_ACCOUNT = "google_account";
    private SharedPreferences prefs;
    public static final String MyPREFERENCES = "com.example.android.mictecclub.PREFERENCE_FILE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) header.findViewById(R.id.imageView);
        TextView userName = (TextView) header.findViewById(R.id.user_name);
        TextView userEmail = (TextView) header.findViewById(R.id.user_email);
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        try{
            Picasso.with(this).load((Uri.parse(prefs.getString("imageUri",null)))).transform(new CircleTransform()).into(imageView);
            userName.setText(prefs.getString("userName","username"));
            userEmail.setText(prefs.getString("userEmail","useremail"));
        }catch (Exception e){

        }


//        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
//
//
//        try {
//            if (!googleSignInAccount.equals(null)) {
//                Picasso.with(this).load(googleSignInAccount.getPhotoUrl()).transform(new CircleTransform()).into(imageView);
//                userName.setText(googleSignInAccount.getDisplayName());
//                userEmail.setText(googleSignInAccount.getEmail());
//
//                User user = new User(googleSignInAccount.getPhotoUrl(), googleSignInAccount.getDisplayName(), googleSignInAccount.getEmail());
//
//                Toast.makeText(this, "" + user, Toast.LENGTH_SHORT).show();
//            }
//
//        }catch (Exception e){
////            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
//        }


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.db) {
//            Toast toast=Toast.makeText(getApplicationContext(),"dashboard clicked",Toast.LENGTH_SHORT);
//            toast.show();

            GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
            if (alreadyloggedAccount != null) {
                Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
                onLoggedIn(alreadyloggedAccount);
            } else {
                Log.d(TAG, "Not logged in");
                startActivity(new Intent(MainActivity.this, LoginInActivity.class));
            }



        } else if (id == R.id.events) {
            Toast toast=Toast.makeText(getApplicationContext(),"events clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.classes) {
            Toast toast=Toast.makeText(getApplicationContext(),"classes clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.leaders) {
            Toast toast=Toast.makeText(getApplicationContext(),"leaders clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.members) {
            Toast toast=Toast.makeText(getApplicationContext(),"members clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.gallery) {
            Toast toast=Toast.makeText(getApplicationContext(),"gallery clicked",Toast.LENGTH_SHORT);
            toast.show();

        }else if (id == R.id.help) {
            Toast toast=Toast.makeText(getApplicationContext(),"help clicked",Toast.LENGTH_SHORT);
            toast.show();

        }else if(id==R.id.about){
            Toast toast=Toast.makeText(getApplicationContext(),"about clicked",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(id==R.id.action_settings){
            Toast toast=Toast.makeText(getApplicationContext(),"settings clicked",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (id==R.id.signout){
              /*
              Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
              listener which will be invoked once the sign out is the successful
               */
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //On Succesfull signout we navigate the user back to LoginActivity
//                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);

                    prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("imageUri", String.valueOf(R.drawable.mictec));
                    editor.putString("userName", "username");
                    editor.putString("userEmail","usermail");
                    editor.apply();

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }

}
