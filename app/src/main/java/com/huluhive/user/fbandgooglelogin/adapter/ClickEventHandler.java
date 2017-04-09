package com.huluhive.user.fbandgooglelogin.adapter;

import com.huluhive.user.fbandgooglelogin.pojo.Post;

/**
 * Created by User on 4/7/2017.
 */
public interface ClickEventHandler {
    void onLikeButtonClicked(Post adapterPosition);

    void onDeleteButtonClicked(Post post);

    void onEditButtonClicked(Post post);
}
