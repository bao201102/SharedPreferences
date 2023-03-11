package bao201102.gmail.com.hellosharedprefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn_red, btn_green, btn_blue, btn_black,btn_count;
    Button btn_setting;
    Integer mCount;
    Integer mCurrentColor;
    SharedPreferences mPreferences;
    Boolean save;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        assignId(btn_black, R.id.btn_black);
        assignId(btn_red, R.id.btn_red);
        assignId(btn_blue, R.id.btn_blue);
        assignId(btn_green, R.id.btn_green);
        assignId(btn_count, R.id.btn_count);
        btn_setting = findViewById(R.id.btn_setting);
        textView.setTextColor(getColor(R.color.white));

        mPreferences = getSharedPreferences("share", MODE_PRIVATE);
        if (savedInstanceState == null){
            int num = mPreferences.getInt("count", 0);
            int col = mPreferences.getInt("color", 0);

            textView.setText(String.valueOf(num));
            textView.setBackgroundColor(col);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        save = sharedPreferences.getBoolean("switch",false);
        if (save == true){
            SharedPreferences.Editor myEdit = mPreferences.edit();
            myEdit.putInt("count", mCount);
            myEdit.putInt("color",mCurrentColor);
            myEdit.clear();
            myEdit.apply();
        }
    }

    public void assignId(Button btn, int id ){
        btn = findViewById(id);
        btn.setOnClickListener(clickListener);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_count:
                    int i = Integer.parseInt(textView.getText().toString());
                    int num = i + 1;
                    mCount = num;
                    textView.setText(String.valueOf(num));
                    break;
                case R.id.btn_black:
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(getColor(R.color.white));
                    mCurrentColor = Color.BLACK;
                    break;
                case R.id.btn_red:
                    textView.setBackgroundColor(Color.RED);
                    textView.setTextColor(getColor(R.color.white));
                    mCurrentColor = Color.RED;
                    break;
                case R.id.btn_green:
                    textView.setBackgroundColor(Color.GREEN);
                    textView.setTextColor(getColor(R.color.white));
                    mCurrentColor = Color.GREEN;
                    break;
                case R.id.btn_blue:
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(getColor(R.color.white));
                    mCurrentColor = Color.BLUE;
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_setting:
                intent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void intent() {
        Intent intent = new Intent(getApplicationContext(), MySettingActivity.class);
        startActivity(intent);
    }
}