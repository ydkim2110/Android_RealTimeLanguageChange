package com.example.anti2110.realtimelanguage;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.anti2110.realtimelanguage.Helper.LocaleHelper;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Init Pager first
        Paper.init(this);

        // Default language is English
        String language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "en");
        }

        updateView((String) Paper.book().read("language"));

    }

    private void updateView(String lang) {
        if(lang.equals("kr"))
            lang = "ko";

        Context context = LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();

        textView.setText(resources.getString(R.string.hello));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.language_en) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        } else if (item.getItemId() == R.id.language_kr) {
            Paper.book().write("language", "kr");
            updateView((String) Paper.book().read("language"));
        }

        return true;
    }
}
