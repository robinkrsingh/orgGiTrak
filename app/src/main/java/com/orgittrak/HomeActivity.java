package com.orgittrak;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements OrgListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (findViewById(R.id.fragment_container) != null) {


            if (savedInstanceState != null) {
                return;
            }

            OrgListFragment orgListFragment = new OrgListFragment();
            orgListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, orgListFragment).commit();




        }
    }

    @Override
    public void onOrgSelected(int pos, String init) {


        OrgDetailFragment orgDetailFragment = (OrgDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.OrgDetailFragment);

        if (orgDetailFragment != null) {


            orgDetailFragment.updateOrganisation(pos);


        } else if(init.equals("no")){

            OrgDetailFragment orgDetailFrg= new OrgDetailFragment();
            Bundle args = new Bundle();
            args.putInt("orgPos", pos);
            orgDetailFrg.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, orgDetailFrg);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }


    }
}
