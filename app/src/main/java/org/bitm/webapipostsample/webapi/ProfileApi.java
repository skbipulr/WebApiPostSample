package org.bitm.webapipostsample.webapi;

import org.bitm.webapipostsample.models.ProfileRequest;
import org.bitm.webapipostsample.models.ProfileResponse;
import org.bitm.webapipostsample.models.UserCollectionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Mobile App Develop on 4/30/2018.
 */

public interface ProfileApi {
    @POST("api/users")
    Call<ProfileResponse> getProfileInfo(@Body ProfileRequest profileRequest);

    @GET
    Call<UserCollectionResponse>getUserCollection(@Url String remainingUrl);

}
