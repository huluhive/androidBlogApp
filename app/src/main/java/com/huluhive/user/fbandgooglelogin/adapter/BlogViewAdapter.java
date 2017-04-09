package com.huluhive.user.fbandgooglelogin.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.huluhive.user.fbandgooglelogin.R;
import com.huluhive.user.fbandgooglelogin.pojo.Post;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by User on 4/7/2017.
 */

public class BlogViewAdapter extends RecyclerView.Adapter<BlogViewAdapter.BlogViewHolder>{
    private static final String TAG=BlogViewHolder.class.getSimpleName();
    ArrayList<Post> mPosts;
    Context mContext;
    LayoutInflater mInflater;
    ClickEventHandler mClickEventHandler;
    public BlogViewAdapter(Context context,ArrayList<Post> posts,ClickEventHandler clickEventHandler){
        mPosts=posts;
        mContext=context;
        mInflater=LayoutInflater.from(context);
        mClickEventHandler=clickEventHandler;
    }

    @Override
    public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BlogViewHolder(mInflater.inflate(R.layout.blogs_row,parent,false));
    }

    @Override
    public void onBindViewHolder(BlogViewHolder holder, int position) {
        holder.mTitleField.setText(mPosts.get(position).getTitle());
        holder.mContentField.setText(mPosts.get(position).getContent());
        holder.mUsername.setText(mPosts.get(position).getUsername());
        ByteArrayInputStream imageStream = new ByteArrayInputStream(mPosts.get(position).getImage());
        Bitmap theImage= BitmapFactory.decodeStream(imageStream);
        holder.mPostImage.setImageBitmap(theImage);
        if(mPosts.get(position).getIsLiked()==1){
            holder.likeButton.setImageResource(R.drawable.ic_thumbs_up);
        }else {
            holder.likeButton.setImageResource(R.drawable.ic_thumbs_down);
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitleField;
        TextView mContentField;
        TextView mUsername;
        ImageView mPostImage;
        ImageButton likeButton;
        Button deleteButton;
        Button editButton;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mTitleField= (TextView) itemView.findViewById(R.id.blog_title);
            mUsername=(TextView)itemView.findViewById(R.id.blog_username);
            mContentField=(TextView)itemView.findViewById(R.id.blog_content);
            mPostImage=(ImageView)itemView.findViewById(R.id.blog_image);
            likeButton=(ImageButton)itemView.findViewById(R.id.like_button);
            deleteButton =(Button)itemView.findViewById(R.id.comment_button);
            editButton=(Button)itemView.findViewById(R.id.edit_button);
            likeButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            editButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id=view.getId();
            switch (id){
                case R.id.like_button:
                    if(mPosts.get(getAdapterPosition()).getIsLiked()==1){
                        mPosts.get(getAdapterPosition()).setIsLiked(0);
                    }else {
                        mPosts.get(getAdapterPosition()).setIsLiked(1);
                    }
                mClickEventHandler.onLikeButtonClicked(mPosts.get(getAdapterPosition()));
                    break;
                case R.id.comment_button:
                mClickEventHandler.onDeleteButtonClicked( mPosts.get(getAdapterPosition()));
                    break;
                case R.id.edit_button:
                mClickEventHandler.onEditButtonClicked(mPosts.get(getAdapterPosition()));
                    break;
            }


        }

        private void toggleButton() {
        }
    }

}
