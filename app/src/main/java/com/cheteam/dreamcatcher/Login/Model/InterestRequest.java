package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class InterestRequest {
    @SerializedName("categories")
    ArrayList<String> categories;

    public InterestRequest(ArrayList<String> categories) {
        this.categories = categories;
    }
}
