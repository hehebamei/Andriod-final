package com.example.administrator.anzhuo_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button button;
    EditText user;
    EditText pass;
    EditText rpass;
    String url="http://192.168.43.156:8081/AndroidSever/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button=(Button)findViewById(R.id.register);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.passwd);
        rpass=(EditText)findViewById(R.id.rpasswd);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mQueue= Volley.newRequestQueue(getBaseContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                if(response.equals("registration succeed"))
                                {
                                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                Log.d("TAG", response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
                        Log.e("TAG", error.getMessage(), error);
                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        String usr=user.getText().toString();
                        String paw=pass.getText().toString();
                        String rpaw=rpass.getText().toString();
                        map.put("username", usr);
                        map.put("password", paw);
                        map.put("rpassword",rpaw);
                        return map;
                    }
                };
                mQueue.add(stringRequest);
            }
        });
    }
}
