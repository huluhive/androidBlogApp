package com.huluhive.user.fbandgooglelogin.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huluhive.user.fbandgooglelogin.R;
import com.huluhive.user.fbandgooglelogin.adapter.BlogViewAdapter;
import com.huluhive.user.fbandgooglelogin.adapter.ClickEventHandler;
import com.huluhive.user.fbandgooglelogin.database.SquiliteHelper;
import com.huluhive.user.fbandgooglelogin.pojo.Post;

import java.util.ArrayList;

/**
 * Created by User on 4/4/2017.
 */

public class HomeFragment extends Fragment implements ClickEventHandler {
    public static final String TAG=HomeFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private SquiliteHelper mSQLiteOpenHelper;
    private ArrayList<Post> posts;
    private BlogViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSQLiteOpenHelper=new SquiliteHelper(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         posts=mSQLiteOpenHelper.getAllPosts();
        Log.e(TAG,posts.toString());
        adapter=new BlogViewAdapter(getActivity(),posts,this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onLikeButtonClicked(Post post) {
        mSQLiteOpenHelper.setPostLiked(post);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDeleteButtonClicked(Post post) {
        mSQLiteOpenHelper.deletePost(post);
        posts=mSQLiteOpenHelper.getAllPosts();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onEditButtonClicked(final Post post) {
        final Dialog dialogbox = new Dialog(getActivity());
        dialogbox.setContentView(R.layout.dialog);
        final EditText title = (EditText) dialogbox
                .findViewById(R.id.title_post);
        final EditText content = (EditText) dialogbox
                .findViewById(R.id.content_post);
        Button editPost = (Button) dialogbox.findViewById(R.id.updateEntry);
        Button edcancel = (Button) dialogbox
                .findViewById(R.id.cancelentry);
        dialogbox.show();
        editPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "INPUT ID", Toast.LENGTH_LONG).show();
                } else {
                    post.setTitle(title.getText().toString());
                    post.setContent(content.getText().toString());
                    mSQLiteOpenHelper.updatePost(post);
                    Toast.makeText(getActivity(),
                            "Post Successfully Added", Toast.LENGTH_LONG)
                            .show();
                    adapter.notifyDataSetChanged();
                    dialogbox.dismiss();
                }
            }
        });
        edcancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
            }
        });

    }
}
