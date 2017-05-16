package com.orgittrak;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orgittrak.Models.Organisation;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;


public class OrgDetailFragment extends Fragment {


    TextView tvOrgName, tvDescription;
    ImageView imgAvatar;
    Organisation organisation;
    String strOrg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_organisation, container, false);


        tvOrgName = (TextView) v.findViewById(R.id.tvOrganisationVal);
        tvDescription = (TextView) v.findViewById(R.id.tvDescriptionVal);

        imgAvatar = (ImageView) v.findViewById(R.id.imgAvatar);
        if (savedInstanceState != null) {

            strOrg = savedInstanceState.getString("strOrg");

            updateOrganisation(strOrg);
        }

        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onStart() {
        super.onStart();


        Bundle args = getArguments();
        if (args != null) {

            strOrg = args.getString("strOrg");
            updateOrganisation(strOrg);

        } else if (strOrg != null) {

            updateOrganisation(strOrg);

        }
    }

    public void updateOrganisation(String strOrg) {

        this.strOrg = strOrg;
        Gson gson = new Gson();
        Type typeOrganisation = new TypeToken<Organisation>() {
        }.getType();
        organisation = gson.fromJson(strOrg, typeOrganisation);

        if (organisation != null) {
            tvOrgName.setText(organisation.getLogin());
            tvDescription.setText(organisation.getDescription());
            Picasso.with(getActivity())
                    .load(organisation.getavatar_url())
                    .into(imgAvatar);
        }




    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current Organisation
        outState.putString("strOrg", strOrg);

    }


}
