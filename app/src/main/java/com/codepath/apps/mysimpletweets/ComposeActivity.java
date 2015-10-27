package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by rsukhi on 10/26/15.
 */
public class ComposeActivity  extends ActionBarActivity {


    public static int REQUEST_CODE = 20;

    EditText tweetText;
    Button submit;

    TwitterClient client = TwitterApplication.getRestClient();



    // ActivityNamePrompt.java -- launched for a result
    public void onSubmit(View v) {

        tweetText = (EditText) findViewById(R.id.etMessage);
        submit = (Button) findViewById(R.id.btnTweet);

        client.post(tweetText.getText().toString(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // Go back to the timeline
                Intent i = new Intent();
                setResult(REQUEST_CODE, i);
                finish();
            }


            // TODO handle error.

        });
        // Prepare data intent
        Intent data = new Intent();
        setResult(REQUEST_CODE, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

    }




}
