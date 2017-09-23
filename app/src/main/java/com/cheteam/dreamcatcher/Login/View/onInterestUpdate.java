package com.cheteam.dreamcatcher.Login.View;

import com.cheteam.dreamcatcher.Login.Model.InterestResponse;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public interface onInterestUpdate {
    void InterestUpdateResponse(boolean error, InterestResponse response,Throwable t);
}
