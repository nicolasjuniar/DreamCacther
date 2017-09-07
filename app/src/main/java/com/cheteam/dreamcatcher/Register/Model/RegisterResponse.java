package com.cheteam.dreamcatcher.Register.Model;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class RegisterResponse {
    public String success;
    public String message;
    public String status_code;
    public DataUser data;

    public class DataUser
    {
        public int id_user;
        public String name;
        public String email;
    }
}
