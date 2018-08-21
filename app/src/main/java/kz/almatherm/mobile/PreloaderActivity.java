package kz.almatherm.mobile;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreloaderActivity extends AppCompatActivity {

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);
//        ButterKnife.bind(this);
        linearLayout = findViewById(R.id.linear_layout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView image = (ImageView) linearLayout.getChildAt(i);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (image.getDrawable() instanceof AnimatedVectorDrawable) {
                    AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) image.getDrawable();
                    drawable.start();
                }
            } else {
                if (image.getDrawable() instanceof AnimatedVectorDrawableCompat) {
                    AnimatedVectorDrawableCompat drawable = (AnimatedVectorDrawableCompat) image.getDrawable();
                    drawable.start();
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
