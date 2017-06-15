package com.example.administrator.anzhuo_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button button;
    TextView txt;
    EditText user;
    EditText pass;
    String url="http://192.168.43.156:8081/AndroidSever/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=(Button)findViewById(R.id.login);
         txt=(TextView)findViewById(R.id.register);
         user=(EditText)findViewById(R.id.user);
         pass=(EditText)findViewById(R.id.passwd);
       /* JSONObject params=new JSONObject();
            params.put("user","sgzx");
            params.put("pass",10086);*/
        button.setOnClickListener(new MyClick());
        txt.setOnClickListener(new MyClick());
    }
    class MyClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    RequestQueue mQueue= Volley.newRequestQueue(getBaseContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                    if(response.equals("user legal"))
                                    {
                                        String usr=user.getText().toString();
                                        String paw=pass.getText().toString();
                                        Bundle bundle=new Bundle();
                                        bundle.putString("name",usr);

                                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                        //ntent.putExtra("name",usr);
                                        intent.putExtras(bundle);
                                       // intent.putExtra("")
                                        LoginActivity.this.setResult(RESULT_OK,intent);
                                        LoginActivity.this.finish();
                                        //startActivity(intent);
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
                            map.put("username", usr);
                            map.put("password", paw);
                            return map;
                        }
                    };
                    mQueue.add(stringRequest);


                    break;
                case R.id.register:
                    Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);

                    startActivity(intent);
                    break;

            }

        }
    }
    }

