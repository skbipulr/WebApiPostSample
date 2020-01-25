package org.bitm.webapipostsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.bitm.webapipostsample.models.ProfileRequest;
import org.bitm.webapipostsample.models.ProfileResponse;
import org.bitm.webapipostsample.models.UserCollectionResponse;
import org.bitm.webapipostsample.webapi.ProfileApi;
import org.bitm.webapipostsample.webapi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProfileApi profileApi;
    String url="api/users?page=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileApi=RetrofitClient.getClient().create(ProfileApi.class);
    }

    public void post(View view) {
        ProfileRequest profileRequest=new ProfileRequest();
        profileRequest.setName("Elias");
        profileRequest.setJob("Student");
        Call<ProfileResponse>profileResponseCall=profileApi.getProfileInfo(profileRequest);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse=response.body();
                Toast.makeText(MainActivity.this, ""+profileResponse.getCreatedAt(), Toast.LENGTH_SHORT).show();
                url=url+3;
                Call<UserCollectionResponse>userCollectionResponseCall=profileApi.getUserCollection(url);
                userCollectionResponseCall.enqueue(new Callback<UserCollectionResponse>() {
                    @Override
                    public void onResponse(Call<UserCollectionResponse> call, Response<UserCollectionResponse> response) {
                        UserCollectionResponse userCollectionResponse=response.body();
                        Toast.makeText(MainActivity.this, ""+userCollectionResponse.getPage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserCollectionResponse> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
