package com.example.dramalho.hw2017mobiledevlab4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dramalho on 7/26/17.
 */

public class ExchangeRateListFragment extends Fragment {

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Instantiates a layout XML file into its corresponding View objects
        View v = inflater.inflate(R.layout.fragment_exchangeratelist, container, false);

        // Specified type of mListView to fix starter code
        mListView = (ListView) v.findViewById(R.id.list_view);

        //Call this method getData() from onCreateView() so that the request is made on the creation of this fragment.
        getData();

        // Define a list of placeholder data
        List<String> list = Arrays.asList("foo", "bar", "baz");

        // ArrayAdapter: You can use this adapter to provide views for an AdapterView, Returns a view for each object
        // in a collection of data objects you provide, and can be used with list-based user interface widgets such as ListView or Spinner.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);                                               //     ^  what? layout for the items in the list?
        return v;
    }

    private void getData() {
        // A request dispatch queue with a thread pool of dispatchers
        RequestQueue queue = Volley.newRequestQueue(getContext());

        // Add url to request from
        String url ="http://api.fixer.io/latest?base=USD";

        // A request for retrieving a JSONObject response body at a given URL, allowing for an optional JSONObject
        // to be passed in as part of the request body.
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("INFO", "got response: " + response.toString());
                            handleResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INFO", "got error: " + error.toString());
                    }
                });
        queue.add(jsonObjRequest);

    }

    private void handleResponse(JSONObject response) {
        List<String> stringsList = new ArrayList<>();
        try {
            JSONObject ratesObject = response.getJSONObject("rates");
            Iterator<String> keys = ratesObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Double val = (Double) ratesObject.get(key);
                stringsList.add(key + " : " + val.toString());
            }
            // Finished compiling list, create a new adapter from it.
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, stringsList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

