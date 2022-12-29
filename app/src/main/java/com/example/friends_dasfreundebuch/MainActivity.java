package com.example.friends_dasfreundebuch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//TODO
// json to collection
// pictures are mixed up

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private PersonListAdapter listAdapter;
    private ArrayList<dummyFriend> friends;
    private FloatingActionButton menuButton;
    private FloatingActionButton menuButtonAddFriend;
    private FloatingActionButton menuButtonOptions;
    public boolean btnSwitch = true;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button closeButton;

    public int getVisibility(){
        setBtnSwitch();
        if (btnSwitch) {
            menuButton.setRotation(45);
            return View.VISIBLE;
        }
        else {
            menuButton.setRotation(0);
            return View.INVISIBLE;
        }
    }

    public boolean setBtnSwitch() {
        return this.btnSwitch = !btnSwitch;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listFriends);
        menuButton = findViewById(R.id.floatingActionButton3);
        menuButtonAddFriend = findViewById(R.id.floatingActionButton1);
        menuButtonOptions = findViewById(R.id.floatingActionButton2);


        friends = FriendsDB.getFriendsList();

//        FriendsDB.toGson();
        listAdapter = new PersonListAdapter(this, R.layout.friend_item, friends);
        listView.setAdapter(listAdapter);

        int height = (friends.size() * 230);
        listView.getLayoutParams().height = height;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object friend = listView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, FriendProfile.class);

                if (friend instanceof Parcelable) {
                    intent.putExtra("friendObject", (Parcelable) friend);
                    startActivity(intent);
                }
            }
        });


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = getVisibility();
                findViewById(R.id.floatingActionButton1).setVisibility(temp);
                findViewById(R.id.floatingActionButton2).setVisibility(temp);

            }
        });

        menuButtonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createComingSoonDialog();
            }
        });

        menuButtonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createComingSoonDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void gotToMyPage(View view) {
        Intent intent = new Intent(MainActivity.this, OwnProfile.class);
        startActivity(intent);
    }

    private void createComingSoonDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View comingsoonPopupView = getLayoutInflater().inflate(R.layout.coming_soon_popup, null);
        closeButton = (Button) comingsoonPopupView.findViewById(R.id.close);

        dialogBuilder.setView(comingsoonPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
