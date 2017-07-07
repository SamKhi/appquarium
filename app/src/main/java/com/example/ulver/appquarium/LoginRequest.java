package com.example.ulver.appquarium;

/**
 * Created by Edenn on 07/07/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://86.245.172.56/appquarium/login.php";
    private Map<String, String> params;

    public LoginRequest(String login, String mdp, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mail", login);
        params.put("mdp", mdp);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
