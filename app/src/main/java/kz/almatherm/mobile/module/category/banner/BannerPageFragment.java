package kz.almatherm.mobile.module.category.banner;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.Banner;

public class BannerPageFragment extends Fragment {
    static final String ARGUMENT_BANNER_ID = "arg_banner_id";
    static final String ARGUMENT_BANNER_IMAGE_URL = "arg_banner_image_url";

    private int bannerId;
    private String bannerImgUrl;

    private int backColor;

    public static BannerPageFragment newInstance(Banner banner) {
        BannerPageFragment pageFragment = new BannerPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_BANNER_ID, banner.getId());
        arguments.putString(ARGUMENT_BANNER_IMAGE_URL, banner.getImageUrl());
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getArguments());
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void initData(Bundle arguments) {
        if (arguments != null) {
            bannerId = arguments.getInt(ARGUMENT_BANNER_ID);
            bannerImgUrl = arguments.getString(ARGUMENT_BANNER_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner_item, null);
        ImageView imBanner = view.findViewById(R.id.imBanner);
        imBanner.setBackgroundColor(backColor);
        return view;
    }
}
