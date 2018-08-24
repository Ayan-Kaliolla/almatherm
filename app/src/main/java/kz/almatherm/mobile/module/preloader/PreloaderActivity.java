package kz.almatherm.mobile.module.preloader;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.module.main.MainActivity;

public class PreloaderActivity extends MvpAppCompatActivity implements PreloaderView {

    @InjectPresenter
    PreloaderPresenter preloaderPresenter;

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);
        preloaderPresenter.loadData();
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

    @Override
    public void completeLoad() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @ProvidePresenter
    public PreloaderPresenter preloaderPresenter() {
        return new PreloaderPresenter(this);
    }
}
