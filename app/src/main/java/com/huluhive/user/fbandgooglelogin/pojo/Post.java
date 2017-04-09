package com.huluhive.user.fbandgooglelogin.pojo;

/**
 * Created by User on 4/7/2017.
 */

public class Post {
    private String username;
    private String title;
    private String content;
    private byte[] image;
    private int mPostId;
    private int isLiked;
    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }



    public void setPostId(int postId) {
        mPostId = postId;
    }



    public Post(){

    }

    public Post(String username, String title, String content, byte[] image,int a) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.image = image;
        this.isLiked=a;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPostId() {
        return mPostId;
    }
}
