package com.huluhive.user.fbandgooglelogin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huluhive.user.fbandgooglelogin.pojo.Post;

import java.util.ArrayList;

/**
 * Created by User on 4/7/2017.
 */

public class SquiliteHelper extends SQLiteOpenHelper {

    public static String DATABASENAME = "mydatabase", POSTTABLE = "posttable";
    private ArrayList<Post> mPostList=new ArrayList<>();


    public SquiliteHelper(Context context) {
        super(context, DATABASENAME, null, 6);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS posttable("
                + " postid INTEGER PRIMARY KEY AUTOINCREMENT, " + "username TEXT, " + "title VARCHAR, "
                +"image BLOB, "+"likepost INTEGER,"+"content VARCHAR)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + POSTTABLE);
        onCreate(db);
    }
    public void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", post.getUsername());
        contentValues.put("title", post.getTitle());
        contentValues.put("image", post.getImage());
        contentValues.put("content", post.getContent());
        contentValues.put("likepost", post.getIsLiked());
        db.insert(POSTTABLE, null, contentValues);
        db.close();
    }


    public ArrayList<Post> getAllPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+POSTTABLE, null);
        mPostList.clear();
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Post post = new Post();
                    post.setUsername(cursor.getString(cursor
                            .getColumnIndex("username")));
                    post.setTitle(cursor.getString(cursor
                            .getColumnIndex("title")));
                    post.setContent(cursor.getString(cursor
                            .getColumnIndex("content")));
                    post.setImage(cursor.getBlob(cursor
                            .getColumnIndex("image")));
                    post.setPostId(cursor.getInt(cursor
                            .getColumnIndex("postid")));
                    post.setIsLiked(cursor.getInt(cursor
                            .getColumnIndex("likepost")));
                    mPostList.add(post);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return mPostList;
    }

    public void updatePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", post.getTitle());
        contentValues.put("content", post.getContent());
        db.update(POSTTABLE, contentValues, "postid=" + post.getPostId(), null);
        db.close();
    }

    public void setPostLiked(Post post) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("likepost",post.getIsLiked() );
        db.update(POSTTABLE,contentValues,"postid="+post.getPostId(), null);
        db.close();
    }

    public void deletePost(Post post){
       try {
           SQLiteDatabase db=this.getWritableDatabase();
           db.execSQL("delete from "+POSTTABLE+" where postid = " + post.getPostId());
           db.close();

       }catch (Exception e){
           e.printStackTrace();
       }


    }
}
