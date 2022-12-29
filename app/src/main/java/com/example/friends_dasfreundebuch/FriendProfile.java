package com.example.friends_dasfreundebuch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FriendProfile extends AppCompatActivity {

    private static final String TAG = "FriendProfile";
    private ListView listView;
    private ListView messageListView;
    private AttributeListAdapter listAdapter;
    private MessageListAdapter messageListAdapter;
    private ArrayList<Attribute> attributes;
    private ArrayList<Message> messages;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText message;
    private Button sendButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_layout);
        listView = findViewById(R.id.listAttributes);
        messageListView = findViewById(R.id.messages);

        ImageButton backBtn = findViewById(R.id.back_button);
        dummyFriend friend = getIntent().getParcelableExtra("friendObject");


        for (Object o : FriendsDB.getFriendsList()) {
            if (o instanceof dummyFriend && ((dummyFriend) o).getImgURL().equals(friend.getImgURL())) {
                messages = ((dummyFriend) o).getMessages();
                attributes = ((dummyFriend) o).getAttributes();
            }
        }

        TextView tv = findViewById(R.id.friendName);
        tv.setText(friend.getName());
        ImageView iv = (ImageView) findViewById(R.id.user);

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(friend.getImgURL(), iv);


        listAdapter = new AttributeListAdapter(this, R.layout.attribute_item, attributes);
        listView.setAdapter(listAdapter);

        messageListAdapter = new MessageListAdapter(this, R.layout.message_item, messages);
        messageListView.setAdapter(messageListAdapter);

        int height = (attributes.size() * 180);
        listView.getLayoutParams().height = height;

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FriendProfile.this, MainActivity.class));
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FriendProfile.this, MainActivity.class));
            }
        });

        Button b = (Button)findViewById(R.id.sendMessage);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSendMessageDialog();
            }
        });
    }

    private void createSendMessageDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View messagePopupView = getLayoutInflater().inflate(R.layout.message_popup, null);
        message = (EditText) messagePopupView.findViewById(R.id.messageTextPopup);
        sendButton = (Button) messagePopupView.findViewById(R.id.sendMessagePopUp);

        dialogBuilder.setView(messagePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Toast.makeText(getApplicationContext(), "SEND MESSAGE: " + message.getText(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
