package com.example.apk_decompile_hw2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        if (id.length() == this.steps.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.steps;
                Log.d("iArr Length",""+iArr.length);
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = Integer.valueOf(String.valueOf(id.charAt(i))).intValue() % 4;
                Log.d("iArr[i]",""+iArr[i]+"and i is:"+i);
                i++;
                Log.d("i",""+i);
            }
        }
        findViews();
        initViews();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void arrowClicked(int direction) {
        if (this.goodToGo && direction != this.steps[this.currentLevel]) {
            this.goodToGo = false;
        }
        int i = this.currentLevel + 1;
        this.currentLevel = i;
        if (i >= this.steps.length) {
            finishGame();
        }
    }

    @SuppressLint("WrongConstant")
    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Log.d("state in finish game --------------------------------",state);
            Toast.makeText(this, "Survived in " + state, 1
            ).show();
        } else {
            Toast.makeText(this, "You Failed ", 1).show();
        }
        finish();
    }

    private void initViews() {
        int i = 0;
        while (true) {
            ImageButton[] imageButtonArr = this.arrows;
            if (i < imageButtonArr.length) {
                int finalI = i;
                imageButtonArr[i].setOnClickListener(new View.OnClickListener() {
                    /* class com.classy.survivegame.Activity_Game.AnonymousClass1 */
                   final int iForArrow = finalI;
                    public void onClick(View v) {
                        Activity_Game.this.arrowClicked(iForArrow);
                    }
                });
                i++;
            } else {
                return;
            }
        }
    }

    private void findViews() {
        this.arrows = new ImageButton[]{(ImageButton) findViewById(R.id.game_BTN_left), (ImageButton) findViewById(R.id.game_BTN_right), (ImageButton) findViewById(R.id.game_BTN_up), (ImageButton) findViewById(R.id.game_BTN_down)};
    }
}
