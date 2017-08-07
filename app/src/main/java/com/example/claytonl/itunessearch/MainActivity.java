package com.example.claytonl.itunessearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String mSearch;
    private TextView oMessage;
    public final static String SearchQuery = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search_bar:

                AlertDialog.Builder builderObject = new AlertDialog.Builder(this);
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builderObject.setView(input);

                builderObject.setTitle(R.string.searchBox).setCancelable(false)
                        .setPositiveButton(R.string.searchBok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mSearch = input.getText().toString();
                                oMessage = (TextView) findViewById(R.id.oMessage);
                                oMessage.setText("");

                                getSupportFragmentManager().beginTransaction().replace(R.id.container
                                        , createCustomFragment(new newScreen(), mSearch)).commit();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                        createCustomFragment(new SearchListFragment(), mSearch)).commit();
                            }
                        })
                        .setNegativeButton(R.string.searchBnotOk, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builderObject.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Fragment createCustomFragment(Fragment f, String query){
        Bundle bundle = new Bundle();
        bundle.putString(SearchQuery, query);
        f.setArguments(bundle);
        return f;
    }
}


