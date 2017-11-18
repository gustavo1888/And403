package co.edu.aulamatriz.reviewapplication.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import co.edu.aulamatriz.reviewapplication.R;

public class MyPreferences {

    private final SharedPreferences.Editor editor;
    private Context context;
    private final SharedPreferences sharedPref;

    public MyPreferences(Context context) {
        sharedPref = context.getSharedPreferences(
                "MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        this.context = context;
    }

    public void setSession(boolean session) {
        editor.putBoolean(context.getString(R.string.session), session);
        editor.commit();
    }

    public boolean isSession(){
        return sharedPref.getBoolean(context.getString(R.string.session), false);
    }
}
