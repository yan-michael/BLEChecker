package com.myan.michaelyanyoga.bluetoothchecker;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import nl.dionsegijn.konfetti.KonfettiView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView checker = (CardView) findViewById(R.id.cardview);
        final ImageView logo = (ImageView) findViewById(R.id.logo);
        final TextView info = (TextView) findViewById(R.id.textView2);
        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);

        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        final Animation fadeInFast = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_fast);
        final Animation fadeOutFast = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_fast);

        final RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1500);
        rotate.setInterpolator(new AccelerateInterpolator());

        logo.startAnimation(fadeIn);
        checker.startAnimation(fadeIn);
        info.startAnimation(fadeOut);

        final AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(fadeInFast);
        animationSet.addAnimation(rotate);


        checker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logo.startAnimation(fadeOutFast);
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {

                    info.setText("Bluetooth Low Energy (BLE) \n is not supported :(");
                    info.startAnimation(fadeIn);

                    logo.setImageResource(R.drawable.bleno);
                    logo.startAnimation(animationSet);


                } else {

                    info.setText("Your device supports \n Bluetooth Low Energy (BLE)!");
                    info.startAnimation(fadeIn);

                    logo.setImageResource(R.drawable.bleyes);
                    logo.startAnimation(animationSet);

                    DisplayMetrics display = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(display);
                    konfettiView.build()
                            .addColors(Color.rgb(255, 36, 0), Color.GREEN, Color.rgb(255, 232, 103), Color.MAGENTA,
                                    Color.rgb(65, 105, 225))
                            .setDirection(0.0, 359.0)
                            .setSpeed(1f, 5f)
                            .setFadeOutEnabled(true)
                            .setTimeToLive(2000L)
                            .addShapes(Shape.RECT, Shape.CIRCLE)
                            .addSizes(new Size(12, 5f))
                            .setPosition(-50f, display.widthPixels + 50f, -50f, -50f)
                            .streamFor(100, 2800L);


                }
            }
        });

    }
}
