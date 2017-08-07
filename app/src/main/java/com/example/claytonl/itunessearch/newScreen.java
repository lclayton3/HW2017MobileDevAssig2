package com.example.claytonl.itunessearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import static com.example.claytonl.itunessearch.MainActivity.SearchQuery;

/**
 * Created by claytonl on 7/30/17.
 */

public class newScreen extends Fragment{
    TextView mSearchBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newscreen, container, false);
        Bundle bundle = getArguments();
        mSearchBox = (TextView)view.findViewById(R.id.lookingFor);
        mSearchBox.append(bundle.getString(SearchQuery));


        return view;
    }

}
