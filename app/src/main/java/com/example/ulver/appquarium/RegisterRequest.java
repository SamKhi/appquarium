package com.example.ulver.appquarium;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edenn on 07/07/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://86.245.172.56/appquarium/register_user.php";
    private Map<String, String> params;

    public RegisterRequest(String mail, String mdp,String confirmMdp, String pseudo, String pays, String address, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mail", mail);
        params.put("mdp", mdp);
        params.put("confirmMdp", confirmMdp);
        params.put("pseudo", pseudo);
        params.put("pays", pays);
        params.put("address", address);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
