package io.github.yesalam.urdudictionary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.github.yesalam.urdudictionary.adapter.FragmentAdapter;
import io.github.yesalam.urdudictionary.data.DictionaryOpenHelper;
import io.github.yesalam.urdudictionary.ui.FavFragment;
import io.github.yesalam.urdudictionary.ui.HistoryFragment;
import io.github.yesalam.urdudictionary.ui.HomeFragment;

public class DefinitionActivity extends AppCompatActivity {

    protected static final String EXTRA_KEY_VERSION = "version";
    protected static final String EXTRA_KEY_THEME = "theme";
    private static final String EXTRA_KEY_VERSION_MARGINS = "version_margins";
    private static final String EXTRA_KEY_TEXT = "text";
    protected static final int NAV_ITEM_INVALID = -1;


    private Toolbar mToolbar ;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout ;
    private SearchView mSearchView ;
    private FloatingActionButton mFab ;

    private SearchHistoryTable mHistoryDatabase;


    private int[] tabIcons = {
            R.drawable.ic_home_white_36dp,
            R.drawable.ic_favorite_white_36dp,
            R.drawable.ic_history_white_36dp
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);




        getToolbar();


        // mSearchView.inflateOverflowMenu(R.menu.menu_main);

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }

        setFab();


        // -----------------------------------------------------------------------------------------
        setSearchView();
        mSearchView.setOnMenuClickListener(new SearchView.OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                mDrawerLayout.openDrawer(GravityCompat.START); // finish();
                perm(Manifest.permission.RECORD_AUDIO, 0);
            }
        });
        // -----------------------------------------------------------------------------------------

        setTitle(null);



    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else //noinspection StatementWithEmptyBody
            if (mSearchView != null && mSearchView.isSearchOpen()) { // TODO
                // mSearchView.close(true);
            } else {
                super.onBackPressed();
            }
    }





    private void setFab() {
        mFab = (FloatingActionButton) findViewById(R.id.fab_delete);
        if (mFab != null) {
            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    Intent intent = new Intent(getApplicationContext(), DefinitionActivity.class) ;
                    startActivity(intent);
                    /*if(!mSearchView.isSearchBarFocused()){
                        mSearchView.setSearchFocused(true);
                    }*/
                }
            });
        }
    }


   /* private void setViewPager() {
        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), getString(R.string.home));
        adapter.addFragment(new FavFragment(), getString(R.string.fav));
        adapter.addFragment(new HistoryFragment(),getString(R.string.history));

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        if (mViewPager != null) {
            mViewPager.setAdapter(adapter);
        }

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);

            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        }
    }*/

  /*  @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mViewPager.setCurrentItem(0);
            *//*Intent intent = new Intent(this, ToolbarActivity.class);
            intent.putExtra(EXTRA_KEY_VERSION, SearchView.VERSION_TOOLBAR);
            intent.putExtra(EXTRA_KEY_VERSION_MARGINS, SearchView.VERSION_MARGINS_TOOLBAR_SMALL);
            intent.putExtra(EXTRA_KEY_THEME, SearchView.THEME_LIGHT);
            startActivity(intent);
            finish();*//*
        }

        if (id == R.id.nav_fav) {
            mViewPager.setCurrentItem(1);
          *//*  Intent intent = new Intent(this, ToolbarActivity.class);
            intent.putExtra(EXTRA_KEY_VERSION, SearchView.VERSION_TOOLBAR);
            intent.putExtra(EXTRA_KEY_VERSION_MARGINS, SearchView.VERSION_MARGINS_TOOLBAR_SMALL);
            intent.putExtra(EXTRA_KEY_THEME, SearchView.THEME_DARK);
            startActivity(intent);
            finish();*//*
        }

        if (id == R.id.nav_history) {
            mViewPager.setCurrentItem(2);
            *//*Intent intent = new Intent(this, MenuItemActivity.class);
            intent.putExtra(EXTRA_KEY_VERSION, SearchView.VERSION_MENU_ITEM);
            intent.putExtra(EXTRA_KEY_VERSION_MARGINS, SearchView.VERSION_MARGINS_MENU_ITEM);
            intent.putExtra(EXTRA_KEY_THEME, SearchView.THEME_LIGHT);
            startActivity(intent);
            finish();*//*
        }



        if (id == R.id.nav_about) {
            *//*Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            finish();*//*
        }

        mDrawerLayout.closeDrawer(GravityCompat.START); // mDrawer.closeDrawers();
        return true;
    }*/


   /* private void setNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            if (getNavItem() > -1) {
                navigationView.getMenu().getItem(getNavItem()).setChecked(true);
            }
        }
    }*/


   /* private int getNavItem() {
        return NAV_ITEM_INVALID;
    }*/

   /* private void setDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (mDrawerLayout != null) {
            mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() { // new DrawerLayout.DrawerListener();
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                    if (mSearchView != null && mSearchView.isSearchOpen()) {
                        mSearchView.close(true);
                    }
                    if (mFab != null) {
                        mFab.hide();
                    }
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                    if (mFab != null) {
                        mFab.show();
                    }
                }
            });
        }
    }*/

    private void getToolbar(){
        if(mToolbar == null ) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar) ;
            if(mToolbar != null) {
                mToolbar.setNavigationContentDescription(getString(R.string.app_name));
                setSupportActionBar(mToolbar);
            }
        }
    }

    protected void setSearchView() {
        mHistoryDatabase = new SearchHistoryTable(this);

        mSearchView = (SearchView) findViewById(R.id.searchView);
        if (mSearchView != null) {
            mSearchView.setVersion(SearchView.VERSION_MENU_ITEM);
            mSearchView.setVersionMargins(SearchView.VERSION_MARGINS_MENU_ITEM);
            mSearchView.setHint(R.string.search_hint);
            mSearchView.setTextSize(16);
            mSearchView.setDivider(false);
            mSearchView.setVoice(true);
            mSearchView.setVoiceText("Set permission on Android 6+ !");
            mSearchView.setAnimationDuration(SearchView.ANIMATION_DURATION);
            mSearchView.setShadowColor(ContextCompat.getColor(this, R.color.search_shadow_layout));
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    getData(query, 0);
                    // mSearchView.close(false);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            mSearchView.setOnOpenCloseListener(new SearchView.OnOpenCloseListener() {
                @Override
                public void onOpen() {
                    if (mFab != null) {
                        mFab.hide();
                    }
                }

                @Override
                public void onClose() {
                    if (mFab != null) {
                        mFab.show();
                    }
                }
            });

            List<SearchItem> suggestionsList = new ArrayList<>();
            suggestionsList.add(new SearchItem("search1"));
            suggestionsList.add(new SearchItem("search2"));
            suggestionsList.add(new SearchItem("search3"));

            SearchAdapter searchAdapter = new SearchAdapter(this, suggestionsList);
            searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
                    String query = textView.getText().toString();
                    getData(query, position);
                    // mSearchView.close(false);
                }
            });
            mSearchView.setAdapter(searchAdapter);
        }
    }

    private void getData(String text, int position) {
        mHistoryDatabase.addItem(new SearchItem(text));

        Intent intent = new Intent(getApplicationContext(), DefinitionActivity.class);
        intent.putExtra(EXTRA_KEY_VERSION, SearchView.VERSION_TOOLBAR_ICON);
        intent.putExtra(EXTRA_KEY_VERSION_MARGINS, SearchView.VERSION_MARGINS_TOOLBAR_SMALL);
        intent.putExtra(EXTRA_KEY_THEME, SearchView.THEME_LIGHT);
        intent.putExtra(EXTRA_KEY_TEXT, text);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), text + ", position: " + position, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("SameParameterValue")
    private void perm(String permission, int permission_request) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, permission_request);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                mSearchView.open(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_definition, menu);
        return true;
    }

}
