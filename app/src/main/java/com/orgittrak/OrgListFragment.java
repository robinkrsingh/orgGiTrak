package com.orgittrak;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orgittrak.Adapters.OrganisationAdapter;
import com.orgittrak.Models.Organisation;
import com.orgittrak.Rest.ApiClient;
import com.orgittrak.Rest.ApiInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrgListFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    String TAG = "OrgListFragment";
    ArrayList<Organisation> organisationList;
    ListView listView;
    int TOTAL_LIST_ITEMS ;
    int NUM_ITEMS_PAGE = 10;
    private int noOfBtns;
    private Button[] btns;
    private TextView title;
    int currentPage;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.list_main, container, false);

        listView = (ListView) v.findViewById(R.id.lvOrganisation);
        title = (TextView) v.findViewById(R.id.title);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gson gson = new Gson();
                String strOrganisation = gson.toJson(organisationList.get((currentPage*NUM_ITEMS_PAGE)+position));
                onItemSelected(strOrganisation, "no");

            }
        });
        if (savedInstanceState != null) {

            Gson gson = new Gson();
            Type listOfOrganisation = new TypeToken<List<Organisation>>(){}.getType();
            organisationList = gson.fromJson(savedInstanceState.getString("strListOrganisation"), listOfOrganisation);
            currentPage = savedInstanceState.getInt("currentPage");
            TOTAL_LIST_ITEMS = organisationList.size();
            Btnfooter(v);
            loadList(currentPage);

            CheckBtnBackGroud(currentPage);

            return v;
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<Organisation>> call = apiService.getOrganisation();
        call.enqueue(new Callback<ArrayList<Organisation>>() {
            @Override
            public void onResponse(Call<ArrayList<Organisation>> call, Response<ArrayList<Organisation>> response) {

                if (response.code() == 200) {
                    organisationList = response.body();

                    TOTAL_LIST_ITEMS = organisationList.size();
                    Btnfooter(v);
                    loadList(currentPage);

                    CheckBtnBackGroud(currentPage);
                    Gson gson = new Gson();
                    String strOrganisation = gson.toJson(organisationList.get(currentPage));
                    onItemSelected(strOrganisation, "yes");

                } else {

                    Toast.makeText(getActivity(), "Error Occurred!", Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<ArrayList<Organisation>> call, Throwable t) {

                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
    private void Btnfooter(View v) {

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.btnLay);

        btns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new Button(getActivity());
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("" + (i + 1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;

            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    loadList(j);
                    CheckBtnBackGroud(j);
                    currentPage = j;
                }
            });
        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(int index) {
        title.setText("Page " + (index + 1) + " of " + noOfBtns);
        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }

    /**
     * Method for loading data in listview
     *
     * @param number
     */
    private void loadList(int number) {
        ArrayList<Organisation> sort = new ArrayList<Organisation>();

        int start = number * NUM_ITEMS_PAGE;
        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < organisationList.size()) {
                sort.add(organisationList.get(i));
            } else {
                break;
            }
        }
        // Create the adapter to convert the array to views
        OrganisationAdapter adapter = new OrganisationAdapter(getActivity(), sort);
        // Attach the adapter to a ListView

        listView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onItemSelected(String  strOrg, String init) {
        if (mListener != null) {
            mListener.onOrgSelected(strOrg, init);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        Type listOfOrganisation = new TypeToken<List<Organisation>>(){}.getType();
        String strListOrganisation = gson.toJson(organisationList, listOfOrganisation);
        outState.putString("strListOrganisation", strListOrganisation);
        outState.putInt("currentPage", currentPage);


        //Save the fragment's state here
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onOrgSelected(String strOrg, String init);
    }
}
