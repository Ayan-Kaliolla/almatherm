package kz.almatherm.mobile.module.addresses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.MapItem;


public class AddressesFragment extends MvpAppCompatFragment implements OnMapReadyCallback, AddressesView {
    private GoogleMap mMap;

    @InjectPresenter
    AddressesPresenter presenter;

    private ArrayList<MapItem> mapItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addresses, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter.loadMapItems();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(121, 121));
        markerOptions.title("One Two Three!!!");

        mMap.addMarker(markerOptions);

//        mMap.setOnMarkerClickListener(marker -> true);

        mMap.setOnInfoWindowClickListener(marker -> {
            MapItem tempItem = new MapItem();
            tempItem.setId(marker.getTitle());
            int index = mapItems.indexOf(tempItem);
            MapItem mapItem = mapItems.get(index);
            BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, (ViewGroup) getView(), false);
            LinearLayout root = view.findViewById(R.id.root);
            dialog.setContentView(view);
            String[] phones = mapItem.getPhone().split(";");
            for (int i = 0; i < phones.length; i++) {
                Button button = new Button(new ContextThemeWrapper(getContext(), R.style.AppTheme_Button_Borderless), null,0);
                button.setText(phones[i]);
                int finalI = i;
                button.setOnClickListener(view1 -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phones[finalI]))));
                root.addView(button);
            }
            if (mapItem.getEmail() != null) {
                Button email = new Button(new ContextThemeWrapper(getContext(), R.style.AppTheme_Button_Borderless), null, R.style.AppTheme_Button_Borderless);
                email.setText(mapItem.getEmail());
                email.setOnClickListener(view1 -> {
                    Intent intent = new Intent(Intent.ACTION_SEND)
                            .setData(Uri.parse("mailto:"))
                            .setType("text/plain")
                            .putExtra(Intent.EXTRA_EMAIL, mapItem.getEmail())
                            .putExtra(Intent.EXTRA_SUBJECT, "Сделать заказ")
                            .putExtra(Intent.EXTRA_STREAM, "asd");
                    startActivity(Intent.createChooser(intent, "Send Email"));
                });
                root.addView(email);
            }
            dialog.show();
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.item_map, (ViewGroup) getView(), false);
                MapItem tempItem = new MapItem();
                tempItem.setId(marker.getTitle());
                int index = mapItems.indexOf(tempItem);
                MapItem mapItem = mapItems.get(index);
                TextView textView = view.findViewById(R.id.item_map_title);
                textView.setText(mapItem.getTitle());
                TextView descr = view.findViewById(R.id.item_map_description);
                descr.setText(mapItem.getDescription());
                TextView phone = view.findViewById(R.id.item_map_phone);
                phone.setText(mapItem.getPhone());
                TextView email = view.findViewById(R.id.item_map_email);
                email.setText(mapItem.getEmail());
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    @ProvidePresenter
    public AddressesPresenter getPresenter() {
        return new AddressesPresenter(getContext());
    }

    @Override
    public void onDataLoaded(ArrayList<MapItem> mapItems) {
        this.mapItems = mapItems;
        for (MapItem mapItem : mapItems) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(mapItem.getLat(), mapItem.getLng()));
            markerOptions.title(mapItem.getId());
            mMap.addMarker(markerOptions);
        }
    }
}
