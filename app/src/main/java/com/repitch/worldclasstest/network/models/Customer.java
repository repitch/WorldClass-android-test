package com.repitch.worldclasstest.network.models;

import android.content.Context;

import com.repitch.worldclasstest.R;

import java.util.Date;

/**
 * Created by repitch on 23.05.16.
 */
public class Customer {
    /*"cid": "000755685", // идентификатор клиента
        "customerType": "customer",  // тип клиента: customer, employee - сотрудник
        "customerStatus": "active",
        "login": "000755685",
        "firstName": "Винни",
        "lastName": "Пух",
        "secondName": "",
        "birthdayDate": "1986-02-05T00:00:00",
        "phoneNumber": "+79995122234",
        "email": "gusalexey@yandex.ru",
        "subscriptionEmail": false,
        "subscriptionSms": true,
        "gymId": "2731a15a-4d1a-4b67-8177-b98985bcd824", // идентификатор клуба
        "passwordExpirationDate": null,
        "homeAddress": null,
        "canRecommend": true, // пока не нужно
        "manager": {
            "name": "Гусев Алексей Викторович",
            "phoneNumber": "",
            "email": "GusevAVi@wclass.ru"
        }*/

    public static final String TYPE_CUSTOMER = "customer";
    public static final String TYPE_EMPLOYEE = "employee";

    public String getReadableType(Context context) {
        if (customerType == null) {
            return null;
        }
        switch (customerType) {
            case TYPE_CUSTOMER:
                return context.getString(R.string.type_customer);
            case TYPE_EMPLOYEE:
                return context.getString(R.string.type_employee);
        }
        return "";
    }

    public String cid;
    public String customerType;
    public String customerStatus;
    public String login;
    public String firstName;
    public String lastName;
    public String secondName;
    public Date birthdayDate;
    public String phoneNumber;
    public String email;
    public boolean subscriptionEmail;
    public boolean subscriptionSms;
    public String gymId;
    public String passwordExpirationDate;
    public String homeAddress;
    public boolean canRecommend;
    public Manager manager;

    public Customer() {

    }

    public String getFullName() {
        String fname = firstName == null ? "" : firstName;
        String lname = lastName == null ? "" : lastName;
        StringBuilder fullname = new StringBuilder();
        if (!fname.isEmpty()) {
            fullname.append(fname);
            fullname.append(" ");
        }
        if (!lname.isEmpty()) {
            fullname.append(lname);
        }
        return fullname.toString();
    }
}
