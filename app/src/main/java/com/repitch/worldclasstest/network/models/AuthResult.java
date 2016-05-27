package com.repitch.worldclasstest.network.models;

import android.content.Context;

import com.repitch.worldclasstest.R;

/**
 * Created by repitch on 23.05.16.
 */
public class AuthResult {
    /*"result": "NotRegistered",
            "customer": null,
            "authToken": null*/

    public static final String RESULT_OK = "Ok";
    public static final String RESULT_NOTREGISTERED = "NotRegistered";
    public static final String RESULT_INCORRECT_CID = "IncorrectCidLogin";
    public static final String RESULT_INCORRECT_PASSWORD = "IncorrectPassword";
    public static final String RESULT_LOGIN_OR_PASS_INCORRECT = "LoginOrPasswordIncorrect";
    public static final String RESULT_PASS_EXPIRED = "PasswordExpired";
    public static final String RESULT_PASS_HAS_EXPIRATION = "PasswordHasExpirationDate";

    public String getResultReadable(Context context) {
        if (result == null) {
            return null;
        }
        switch (result) {
            case RESULT_OK:
                return context.getString(R.string.result_ok);
            case RESULT_NOTREGISTERED:
                return context.getString(R.string.result_notregistered);
            case RESULT_INCORRECT_CID:
                return context.getString(R.string.result_incorrect_cid);
            case RESULT_INCORRECT_PASSWORD:
                return context.getString(R.string.result_incorrect_pass);
            case RESULT_LOGIN_OR_PASS_INCORRECT:
                return context.getString(R.string.result_login_or_pass_incorrect);
            case RESULT_PASS_EXPIRED:
                return context.getString(R.string.result_pass_expired);
            case RESULT_PASS_HAS_EXPIRATION:
                return context.getString(R.string.result_has_expiration);
        }
        return context.getString(R.string.result_unknown);
    }

    public boolean isOk() {
        if (result == null) {
            return false;
        }
        return result.equalsIgnoreCase(RESULT_OK);
    }

    public String result;
    public Customer customer;
    public AuthToken authToken;
}
