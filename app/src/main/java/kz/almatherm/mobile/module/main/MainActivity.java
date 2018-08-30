package kz.almatherm.mobile.module.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.module.addresses.AddressesFragment;
import kz.almatherm.mobile.module.cart.CartFragment;
import kz.almatherm.mobile.module.category.CategoryFragment;
import kz.almatherm.mobile.module.certificates.CertificatesFragment;
import kz.almatherm.mobile.module.orders.OrdersFragment;

public class MainActivity extends MvpAppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;


    private MvpAppCompatFragment currentFragment;
    private int cartPosition;
    private boolean notificationVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNavigation();

    }

    private void setCartTotalElements(int count) {
        if (count > 0) {
            AHNotification notification = new AHNotification.Builder()
                    .setText(String.valueOf(count))
                    .setBackgroundColor(Color.RED)
                    .setTextColor(Color.WHITE)
                    .build();
            bottomNavigation.setNotification(notification, cartPosition);
            notificationVisible = true;
        }
    }

    private void removeCardTotalElements() {
        bottomNavigation.setNotification(new AHNotification(), cartPosition);
    }

    private void initBottomNavigation() {
        bottomNavigation.addItems(createBottomNavigationItems());
        cartPosition = bottomNavigation.getItemsCount() - 1;
        bottomNavigation.setOnTabSelectedListener(this);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
    }

    private List<AHBottomNavigationItem> createBottomNavigationItems() {
        List<AHBottomNavigationItem> items = new LinkedList<>();
        items.add(new AHBottomNavigationItem(R.string.home_title, R.drawable.ic_home, R.color.colorPrimary));
        items.add(new AHBottomNavigationItem(R.string.certificates_title, R.drawable.ic_certificates, R.color.colorPrimary));
        items.add(new AHBottomNavigationItem(R.string.address_title, R.drawable.ic_map, R.color.colorPrimary));
        items.add(new AHBottomNavigationItem(R.string.cart_title, R.drawable.ic_cart, R.color.colorPrimary));
        return items;
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (currentFragment != null && String.valueOf(position).equals(currentFragment.getTag())) {
            return true;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        MvpAppCompatFragment fragmentByTag = (MvpAppCompatFragment) fragmentManager.findFragmentByTag(String.valueOf(position));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentByTag == null) {
            selectCurrentFragment(position);
            if (currentFragment != null) {
                fragmentTransaction
                        .add(R.id.content, currentFragment, String.valueOf(position))
                        .addToBackStack(null)
                        .commit();
            } else {
                throw new IllegalArgumentException();
            }
            return true;
        } else {
            fragmentTransaction.hide(currentFragment);
            fragmentTransaction.show(fragmentByTag);
            fragmentTransaction.commit();
            currentFragment = fragmentByTag;
            return true;
        }
    }

    private void selectCurrentFragment(int position) {
        switch (position) {
            case 0:
                currentFragment = new CategoryFragment();
                break;
            case 1:
                currentFragment = new OrdersFragment();
                break;
            case 2:
                currentFragment = new CertificatesFragment();
                break;
            case 3:
                currentFragment = new AddressesFragment();
                break;
            case 4:
                currentFragment = new CartFragment();
                if (notificationVisible) {
                    removeCardTotalElements();
                }
                break;
        }
    }
}
