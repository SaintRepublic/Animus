package com.saintrepublic.animussample;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.saintrepublic.animus.Animus;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    ImageView tester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        tester = findViewById(R.id.imageView);

        // Set default animation interpolator for all animations
        Animus.setCommonInterpolator(Animus.Interpolation.LINEAR);
    }


    public void topButtonClick(View topButton) {
        if (((Button) topButton).getText().toString().equals("toTop")) {
            ((Button) topButton).setText("fromTop");

            tester.startAnimation(Animus.Move.To.top(500, true));
        }
        else {
            ((Button) topButton).setText("toTop");

            tester.startAnimation(Animus.Move.From.top(500, true));
        }
    }

    public void rightButtonClick(View rightButton) {
        if (((Button) rightButton).getText().toString().equals("toRight")) {
            ((Button) rightButton).setText("fromRight");

            tester.startAnimation(Animus.Move.To.right(500, true));
        }
        else {
            ((Button) rightButton).setText("toRight");

            tester.startAnimation(Animus.Move.From.right(500, true));
        }
    }

    public void bottomButtonClick(View bottomButton) {
        if (((Button) bottomButton).getText().toString().equals("toBottom")) {
            ((Button) bottomButton).setText("fromBottom");

            tester.startAnimation(Animus.Move.To.bottom(500, true));
        }
        else {
            ((Button) bottomButton).setText("toBottom");

            tester.startAnimation(Animus.Move.From.bottom(500, true));
        }
    }

    public void leftButtonClick(View leftButton) {
        if (((Button) leftButton).getText().toString().equals("toLeft")) {
            ((Button) leftButton).setText("fromLeft");

            tester.startAnimation(Animus.Move.To.left(500, true));
        }
        else {
            ((Button) leftButton).setText("toLeft");

            tester.startAnimation(Animus.Move.From.left(500, true));
        }
    }

    public void hideButtonClick(View hideButton) {
        if (((Button) hideButton).getText().toString().equals("hide")) {
            ((Button) hideButton).setText("show");

            tester.startAnimation(Animus.Alpha.hide(500, true));
        }
        else {
            ((Button) hideButton).setText("hide");

            tester.startAnimation(Animus.Alpha.show(500, true));
        }
    }

    public void scaleButtonClick(View scaleButton) {
        if (((Button) scaleButton).getText().toString().equals("scale 1 to 0")) {
            ((Button) scaleButton).setText("scale 0 to 1");

            tester.startAnimation(Animus.Scale.from1to0(500, true));
        }
        else {
            ((Button) scaleButton).setText("scale 1 to 0");

            tester.startAnimation(Animus.Scale.from0to1(500, true));
        }
    }

    public void rotateButtonClick(View rotateButton) {
        tester.startAnimation(Animus.Rotate.relativeToSelf(0, 70, 0.5f, 0.5f, 500,true));
    }
}
