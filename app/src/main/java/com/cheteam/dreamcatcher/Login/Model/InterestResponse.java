package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class InterestResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("categories")
    public ArrayList<String> categories;
    @SerializedName("message")
    public String message;
}
