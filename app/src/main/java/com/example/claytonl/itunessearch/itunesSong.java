package com.example.claytonl.itunessearch;



import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by claytonl on 7/30/17.
 */

public class itunesSong {

    private RequestQueue mRequestQueue;
    private static itunesSong mFiles;
    private Context mContext;
    private ImageLoader picLoader;
    private final static int IMAGE_CACHE_COUNT = 100;

    public interface ItunesResultsListener {
        void onSongResponse(List<Music> songFronFile);
    }

    public static itunesSong get(Context context) {
        if (mFiles == null) {
            mFiles = new itunesSong (context);
        }
        return mFiles;
    }
    private itunesSong (Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);

        picLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(IMAGE_CACHE_COUNT);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }
    public void getItunesResults(String searchTerm, ItunesResultsListener resultsListener) {
        final ItunesResultsListener songListenerInternal = resultsListener;
        String url = "https:itunes.apple.com/search?term=" + searchTerm + "&entity=musicTrack";

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Music> songFromFile = new ArrayList<Music>();
                            JSONArray jSongs = response.getJSONArray("results");
                            for (int i = 0; i < jSongs.length(); i++){
                                JSONObject temp;
                                temp = jSongs.getJSONObject(i);

                                songFromFile.add(new Music(temp));
                            }
                            songListenerInternal.onSongResponse(songFromFile);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            songListenerInternal.onSongResponse(null);

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {songListenerInternal.onSongResponse(null);

                    }
                });
        mRequestQueue.add(jsonObjRequest);
    }

    public ImageLoader getImageLoader() {
        return picLoader;
    }
}

