package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Sandwich sandwich;
    TextView morigin_tv;
    TextView malso_known_tv;
    TextView mdescription_tv;
    TextView mingredients_tv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        morigin_tv=(TextView)findViewById(R.id.origin_tv);
        malso_known_tv=(TextView)findViewById(R.id.also_known_tv);
        mdescription_tv=(TextView)findViewById(R.id.description_tv);
        mingredients_tv=(TextView)findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        morigin_tv.setText(sandwich.getPlaceOfOrigin());
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++){

            malso_known_tv.append(sandwich.getAlsoKnownAs().get(i));
            }


        Sandwich.alsoKnownAs.clear();

        mdescription_tv.setText(sandwich.getDescription());

        for(int i=0;i<sandwich.getIngredients().size();i++){

            mingredients_tv.append(sandwich.getIngredients().get(i));

        }

        Sandwich.ingredients.clear();

    }
}
