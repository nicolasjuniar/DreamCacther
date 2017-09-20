package com.cheteam.dreamcatcher.InterestForm.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 08/09/2017.
 */

public class ModelInterest {
    @SerializedName("interest")
    public String interest;

    public ModelInterest(String interest) {
        this.interest = interest;
    }
}
