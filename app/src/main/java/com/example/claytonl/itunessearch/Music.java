package com.example.claytonl.itunessearch;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by claytonl on 7/31/17.
 */

public class Music {


    protected String mNameofSong;
    protected String martist;
    protected String mAlbum;
    protected String mcoverArt;
    protected String mwebPage;
    protected String msnippetURL;

    public Music (JSONObject jObj){
        try{
            mNameofSong = jObj.getString("trackName");
            martist = jObj.getString("artistName");
            mAlbum = jObj.getString("collectionName");
            mcoverArt = jObj.getString("artworkUrl60");
            mwebPage = jObj.getString("trackViewUrl");
            msnippetURL = jObj.getString("previewUrl");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getNameofSong() {
        return mNameofSong;
    }

    public String getartist() {
        return martist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public String getcoverArt() {
        return mcoverArt;
    }

    public String getwebPage() {
        return mwebPage;
    }

    public String getAudioSnippet() {return msnippetURL;}

}




