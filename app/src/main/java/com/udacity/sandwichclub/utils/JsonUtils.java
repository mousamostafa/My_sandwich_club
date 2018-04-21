package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    static Sandwich ss=new Sandwich();
    static String mainName;
    static List<String> alsoKnownAs = new ArrayList<String>();
    static   String placeOfOrigin;
    static String description;
     static String image;
     static List<String> ingredients = new ArrayList<String>();


    public static Sandwich parseSandwichJson(String json) {


        try {
            JSONObject sandwich_detail=new JSONObject(json);
            JSONObject name=sandwich_detail.getJSONObject("name");
            mainName=name.getString("mainName");

            JSONArray alsoKnown=name.getJSONArray("alsoKnownAs");

            for (int i=0;i<alsoKnown.length();i++) {
                if (i == alsoKnown.length() - 1) {
                    alsoKnownAs.add(alsoKnown.getString(i) + ".");

                } else {
                    alsoKnownAs.add(alsoKnown.getString(i) + " , ");

                }
            }
            placeOfOrigin=sandwich_detail.getString("placeOfOrigin");

            description=sandwich_detail.getString("description");

            image=sandwich_detail.getString("image");

            JSONArray mingredients=sandwich_detail.getJSONArray("ingredients");

            for (int x=0;x<mingredients.length();x++){
                if(x==mingredients.length()-1){
                    ingredients.add(mingredients.getString(x)+".");
                }
                else {
                    ingredients.add(mingredients.getString(x)+" , ");
                }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
