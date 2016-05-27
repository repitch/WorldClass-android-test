package com.repitch.worldclasstest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repitch.worldclasstest.Constants;
import com.repitch.worldclasstest.DataManager;
import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.network.models.Customer;
import com.repitch.worldclasstest.ui.activities.BaseActivity;

import java.text.SimpleDateFormat;

/**
 * Created by repitch on 25.05.16.
 */
public class PersonalAreaFragment extends BaseFragment {

    private TextView txtCid, txtCustomerType, txtFullName, txtBirthdayDate,
            txtPhoneNumber, txtEmail;
    private Customer mCustomer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal_area, container, false);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Личный кабинет");
        findViews(rootView);
        initViews();

        return rootView;
    }

    private void initViews() {
        mCustomer = DataManager.getInstance().mCustomer;
        txtCid.setText(mCustomer.cid);
        txtCustomerType.setText(mCustomer.getReadableType(getActivity()));
        txtFullName.setText(mCustomer.getFullName());
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_BIRTHDAY,
                getResources().getConfiguration().locale);
        txtBirthdayDate.setText(df.format(mCustomer.birthdayDate));
        txtPhoneNumber.setText(mCustomer.phoneNumber);
        txtEmail.setText(mCustomer.email);
    }

    private void findViews(View rootView) {
        txtCid = (TextView) rootView.findViewById(R.id.txt_cid);
        txtCustomerType = (TextView) rootView.findViewById(R.id.txt_customer_type);
        txtFullName = (TextView) rootView.findViewById(R.id.txt_fullname);
        txtBirthdayDate = (TextView) rootView.findViewById(R.id.txt_birthday_date);
        txtPhoneNumber = (TextView) rootView.findViewById(R.id.txt_phone_number);
        txtEmail = (TextView) rootView.findViewById(R.id.txt_email);
    }
}
