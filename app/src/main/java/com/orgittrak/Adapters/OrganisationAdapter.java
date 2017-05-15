package com.orgittrak.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orgittrak.Models.Organisation;
import com.orgittrak.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by robin on 15/05/17.
 */

public class OrganisationAdapter extends ArrayAdapter<Organisation> {
    public OrganisationAdapter(Context context, ArrayList<Organisation> Organisations) {
        super(context, 0, Organisations);
        this.context = context;
    }
    Context context;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Organisation Organisation = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_organisation, parent, false);
        }

        TextView tvOrgName = (TextView) convertView.findViewById(R.id.tvOrgName);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);

        tvOrgName.setText(Organisation.getLogin());
        tvDescription.setText(Organisation.getDescription());
        Picasso.with(context)
                .load(Organisation.getavatar_url())
                .into(imgAvatar);

        return convertView;
    }
}