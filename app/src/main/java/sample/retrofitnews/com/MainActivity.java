package sample.retrofitnews.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Api api;
    Retrofit retrofit;

    private static RecyclerView.Adapter adapter;
    private LinearLayoutManager mLayoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ArticlesItem> articlesList;
    private static ArrayList<Source> sourcesList;
    ArticlesItem articlesItem;
    Call<Results> call;
    String apiKey = "be234d8f91b14144884b6e4514609b9c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Global News");
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        articlesList = new ArrayList<>();

        try {
            call = api.getComments("health", apiKey);

        } catch (Exception e) {
            e.printStackTrace();

        }


        getNewsApi();
        //    adapter = new CustomAdapter(this,articlesList);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.global) {
            call = api.getComments("health", apiKey);

            getNewsApi();

        } else if (id == R.id.business) {
            call = api.getComments("business", apiKey);

            getNewsApi();
        } else if (id == R.id.entainment) {
            call = api.getComments("entertainment", apiKey);

            getNewsApi();
        } else if (id == R.id.general) {
            call = api.getComments("general", apiKey);

            getNewsApi();

        } else if (id == R.id.health) {
            call = api.getComments("health", apiKey);
            getNewsApi();
        } else if (id == R.id.sports) {
            call = api.getComments("sports", apiKey);
            getNewsApi();
        } else if (id == R.id.technolgy) {
            call = api.getComments("technology", apiKey);

            getNewsApi();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getNewsApi() {

        String url = call.request().url().toString();
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                for (ArticlesItem list : response.body().getArticles()) {
                    articlesItem = new ArticlesItem();
                    String title = list.getTitle();
                    String description = list.getDescription();
                    String publishedAt = list.getPublishedAt();
                    String imgUrl = list.getUrlToImage();
                    String newsUrl = list.getUrl();
                    articlesItem.setPublishedAt(publishedAt);
                    articlesItem.setTitle(title);
                    articlesItem.setDescription(description);
                    articlesItem.setUrlToImage(imgUrl);
                    articlesItem.setUrl(newsUrl);

                    articlesList.add(articlesItem);

                    try {
                        // Log.e("@@tit", title);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                adapter = new CustomAdapter(articlesList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {


            }
        });

    }


}
