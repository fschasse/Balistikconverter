package com.chasseprecision.balstikconverter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;

import com.chasseprecision.balstikconverter.R;
import com.chasseprecision.balstikconverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    static SharedPreferences sharedpreferences;
    private static final String mypreference = "settingsValues";
    private static final String scopeUnitUD = "scope_unit_ud";
    private static final String scopeUnitLR = "scope_unit_lr";
    private static final String scopeValueUD = "scope_value_ud";
    private static final String scopeValueLR = "scope_value_lr";
    private static final String systemUnit = "system_unit";
    private static final String systemDistance = "system_distance";
    private static String scopeUnitUDParam;
    private static String scopeUnitLRParam;
    private static String systemUnitParam;
    private static String systemDistanceParam;
    private static float scopeValueUDParam;
    private static float scopeValueLRParam;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.getString(scopeUnitUD, "").isEmpty()) {
            editor.putString(scopeUnitUD, "MOA");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeUnitLR, "").isEmpty()) {
            editor.putString(scopeUnitLR, "MOA");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueUD, "").isEmpty()) {
            editor.putString(scopeValueUD, "0.25");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueLR, "").isEmpty()) {
            editor.putString(scopeValueLR, "0.25");
            editor.commit();
        }
        if(sharedpreferences.getString(systemUnit, "").isEmpty()) {
            editor.putString(systemUnit, "METRIC");
            editor.commit();
        }
        if(sharedpreferences.getString(systemDistance, "").isEmpty()) {
            editor.putString(systemDistance, "METERS");
            editor.commit();
        }
        systemUnitParam = sharedpreferences.getString(systemUnit, "");
        systemDistanceParam = sharedpreferences.getString(systemDistance, "");
        scopeUnitLRParam = sharedpreferences.getString(scopeUnitLR, "");
        scopeUnitUDParam = sharedpreferences.getString(scopeUnitUD, "");
        scopeValueLRParam = Float.parseFloat(sharedpreferences.getString(scopeValueLR, ""));
        scopeValueUDParam = Float.parseFloat(sharedpreferences.getString(scopeValueUD, ""));

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 16, 16, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private CharSequence menuIconWithText(Drawable r, String title) {
        r = resize(r);
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    public static String GetPrefValue(String valueKey)
    {
        String prefValue = "";
        switch(valueKey) {
            case "SCOPE_UNIT_UD" :
                prefValue = scopeUnitUDParam;
                break;
            case "SCOPE_UNIT_LR" :
                prefValue = scopeUnitLRParam;
                break;
            case "SCOPE_VALUE_UD" :
                prefValue = String.valueOf(scopeValueUDParam);
                break;
            case "SCOPE_VALUE_LR" :
                prefValue = String.valueOf(scopeValueLRParam);
                break;
            case "SYSTEM_UNIT" :
                prefValue = String.valueOf(systemUnitParam);
                break;
            case "SYSTEM_DISTANCE" :
                prefValue = String.valueOf(systemDistanceParam);
                break;
        }
        return prefValue;
    }

    public static boolean CommitPref()
    {
        boolean isOk = false;
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString(scopeUnitUD, scopeUnitUDParam);
        editor2.putString(scopeUnitLR, scopeUnitLRParam);
        editor2.putString(scopeValueUD, String.valueOf(scopeValueUDParam));
        editor2.putString(scopeValueLR, String.valueOf(scopeValueLRParam));
        if(editor2.commit()) { isOk = true; }
        return isOk;
    }

    public static boolean CommitGenPref()
    {
        boolean isOk = false;
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString(systemUnit, systemUnitParam);
        editor2.putString(systemDistance, systemDistanceParam);
        if(editor2.commit()) { isOk = true; }
        return isOk;
    }

    public static boolean SetPrefValue(String param, String valueKey)
    {
        boolean isok = false;
        switch(param) {
            case "SCOPE_UNIT_UD" :
                scopeUnitUDParam = valueKey;
                break;
            case "SCOPE_UNIT_LR" :
                scopeUnitLRParam = valueKey;
                break;
            case "SCOPE_VALUE_UD" :
                scopeValueUDParam = Float.parseFloat(valueKey);
                break;
            case "SCOPE_VALUE_LR" :
                scopeValueLRParam = Float.parseFloat(valueKey);
                break;
        }
        if(CommitPref()) { isok=true; }
        return isok;
    }

    public static boolean SetGenPrefValue(String param, String valueKey)
    {
        boolean isok = false;
        switch(param) {
            case "SYSTEM_UNIT" :
                systemUnitParam = valueKey;
                break;
            case "SYSTEM_DISTANCE" :
                systemDistanceParam = valueKey;
                break;
        }
        if(CommitGenPref()) { isok=true; }
        return isok;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.add_plus), getResources().getString(R.string.action_scope)));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.settings_configuration), getResources().getString(R.string.action_settings)));
        menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.drawable.help_about), getResources().getString(R.string.action_about)));
        menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.drawable.money_icon), getResources().getString(R.string.action_donate)));
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        switch(id) {
            case 1:
                navController.navigate(R.id.action_Go_to_Settings);
                break;
            case 2 :
                navController.navigate(R.id.action_Go_to_GeneralSettings);
                break;
            case 3 :
                navController.navigate(R.id.action_Go_to_About);
                break;
            case 4 :
                navController.navigate(R.id.action_Go_to_Donate);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}