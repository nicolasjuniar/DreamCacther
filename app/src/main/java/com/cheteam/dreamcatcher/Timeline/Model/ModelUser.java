package com.cheteam.dreamcatcher.Timeline.Model;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class ModelUser {
    public int id_user;
    public String name;
    public String address;
    public String bio;
    public int total_posts;
    public int id_avatar;
    public int id_cover_photo;
    public ArrayList<Integer> id_categories;
    public boolean success;
    public String message;
    public int status_code;
}
