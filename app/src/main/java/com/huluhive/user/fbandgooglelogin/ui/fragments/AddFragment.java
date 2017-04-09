package com.huluhive.user.fbandgooglelogin.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.huluhive.user.fbandgooglelogin.R;
import com.huluhive.user.fbandgooglelogin.database.SquiliteHelper;
import com.huluhive.user.fbandgooglelogin.pojo.Post;
import com.huluhive.user.fbandgooglelogin.ui.activities.WelcomActivity;
import com.facebook.Profile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by User on 4/7/2017.
 */
public class AddFragment extends Fragment {
    private static final int GALLERTY_INTENT = 100;
    private static final String TAG=AddFragment.class.getSimpleName();
    EditText titleField;
    EditText contentField;
    ImageView imageField;
    Uri imageUri;
    boolean isFacebook;
    private SquiliteHelper mHelper;
    private String username;
    private Button submitButton;
    private Bitmap bitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper=new SquiliteHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_fragment_post,container,false);
        titleField= (EditText) view.findViewById(R.id.title_field);
        contentField= (EditText) view.findViewById(R.id.content_field);
        imageField= (ImageView) view.findViewById(R.id.image_field_post);
        submitButton=(Button)view.findViewById(R.id.submit_button);
            imageField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERTY_INTENT);
            }
        });

        if(getArguments()!=null) {
             username = getArguments().getString(WelcomActivity.GOOGLE_USERNAME);
        }else {
            Profile profile = Profile.getCurrentProfile();
            username=profile.getFirstName();
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] photo = baos.toByteArray();
                Post post=new Post(username,titleField.getText().toString(),contentField.getText().toString(),photo,0);

                if(!TextUtils.isEmpty(post.getUsername()) && !TextUtils.isEmpty(post.getTitle()) && !TextUtils.isEmpty(post.getContent())&& imageUri!=null){
                    mHelper.addPost(post);
                    Log.e(TAG,username);
                    Toast.makeText(getActivity(), "Added successfully", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,new HomeFragment()).commit();
                }else {
                    Toast.makeText(getActivity(), "Field Empty", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERTY_INTENT && resultCode==RESULT_OK){
            imageUri=data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageField.setImageURI(imageUri);
    }


    }
}
