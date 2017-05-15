package com.orgittrak.Rest;

/**
 * Created by robin on 15/05/17.
 */

import com.orgittrak.Models.Organisation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("organizations")
    Call<ArrayList<Organisation>> getOrganisation();

}

