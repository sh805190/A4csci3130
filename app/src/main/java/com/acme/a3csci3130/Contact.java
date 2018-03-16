package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;
    public  String business;
    public  String address;
    public  String province;
    public  String number;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String name, String number,String email,String business,String province,String address){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.business = business;
        this.province = province;
        this.address = address;
        this.number = number;

    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);
        result.put("business", business);
        result.put("province", province);
        result.put("address", address);
        result.put("number", number);
        return result;
    }
}
