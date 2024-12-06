    package com.example.appxemphim_final;

    import android.app.Activity;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class MainActivity extends AppCompatActivity {
        private BottomNavigationView bottomNavigationView;
        private Fragment home_frgament = new Fragment_Trangchu();
        private Fragment movies_fragment = new Fragment_Phimle();
        private Fragment movie_series_fragment = new Fragment_Phimbo();
        private Fragment tvshow_fragment = new Fragment_Truyenhinh();
        private Fragment account_fragment = new Fragment_Taikhoan();
        private Fragment activeFragment  = home_frgament;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            setContentView(R.layout.activity_main);

            String username = getIntent().getStringExtra("username");
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            account_fragment.setArguments(bundle);
            // Lưu dữ liệu vào SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("AppData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.apply();
            //Thêm 4 fragment vào fragment manager
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_Layout_ListPhim, home_frgament, "tvshow_fragment")
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_Layout_ListPhim, movies_fragment, "tvshow_fragment")
                    .hide(movies_fragment)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_Layout_ListPhim, movie_series_fragment, "tvshow_fragment")
                    .hide(movie_series_fragment)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_Layout_ListPhim, tvshow_fragment, "tvshow_fragment")
                    .hide(tvshow_fragment)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_Layout_ListPhim, account_fragment, "tvshow_fragment")
                    .hide(account_fragment)
                    .commit();
            bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.action_home){
                        showFragment(home_frgament);
                        return true;
                    }else if(item.getItemId() == R.id.action_movies){
                        showFragment(movies_fragment);
                        return true;
                    }else if(item.getItemId() == R.id.action_movie_series){
                        showFragment(movie_series_fragment);
                        return true;
                   }else if(item.getItemId() == R.id.action_tvshow){
                        showFragment(tvshow_fragment);
                        return true;
                   }else if(item.getItemId() == R.id.action_account){
                        showFragment(account_fragment);
                        return true;
                    }
                    return false;
                }
            });
        }
        private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_Layout_ListPhim,fragment);
            fragmentTransaction.commit();

        }
        private void showFragment(Fragment fragment) {
            if (fragment != activeFragment) {
                getSupportFragmentManager().beginTransaction()
                        .hide(activeFragment)
                        .show(fragment)
                        .commit();
                activeFragment = fragment;
            }
        }

    }