package com.orgittrak;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orgittrak.Models.Organisation;
import com.squareup.picasso.Picasso;


public class OrgDetailFragment extends Fragment {




    int orgPos;
    TextView tvOrgName, tvDescription;
    ImageView imgAvatar;
    Organisation organisation;

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
            orgPos = savedInstanceState.getInt("orgPos");
            updateOrganisation(orgPos);
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
        if (args != null ) {

            orgPos = args.getInt("orgPos");
            organisation = OrgListFragment.organisationList.get(orgPos);
            updateOrganisation(orgPos);

        } else if (orgPos != 0 && organisation != null) {

            updateOrganisation(orgPos);

        }
    }

    public void updateOrganisation(int pos) {
        orgPos = pos;
        organisation = OrgListFragment.organisationList.get(orgPos);
        if(organisation != null) {
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

        // Save the current Organisation selection
        outState.putInt("orgPos", orgPos);
    }


}
