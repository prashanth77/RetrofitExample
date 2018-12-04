package sample.retrofitnews.com;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    //https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=be234d8f91b14144884b6e4514609b9c
    String BASE_URL1="https://newsapi.org/v2/";
    String BASE_URL="https://newsapi.org/v2/";
  //  https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=be234d8f91b14144884b6e4514609b9c

//https://newsapi.org/v2/top-headlines?country=in&apiKey=be234d8f91b14144884b6e4514609b9c

@GET("top-headlines")
/*Call<Results> getComments1(@Query("country") String country,
                                    @Query("apiKey") String apikey);*/
    Call<Results> getComments(@Query("category") String category, @Query("apiKey") String apikey);

   // Call<ModelClass> getInfo();
}
