package com.example.bloc_salle_app.ui.salle;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloc_salle_app.MainActivity;
import com.example.bloc_salle_app.R;
import com.example.bloc_salle_app.VolleySingleton;
import com.example.bloc_salle_app.adapter.RecyclerAdapter;
import com.example.bloc_salle_app.beans.Salle;
import com.example.bloc_salle_app.databinding.FragmentSalleBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalleFragment extends Fragment {
    Button btn_send;
    RecyclerView recyclerView;
    EditText Id,Salle,Bloc;
    AlertDialog.Builder builder;
    String url = "http://bloc-salle-app.herokuapp.com/insertsalle";
    String url2 = "http://bloc-salle-app.herokuapp.com/api/salles";
    private SalleViewModel salleViewModel;
    private FragmentSalleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        salleViewModel = new ViewModelProvider(this).get(SalleViewModel.class);
        binding = FragmentSalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btn_send = root.findViewById(R.id.btn_send);
        Salle = root.findViewById(R.id.salle);
        Bloc = root.findViewById(R.id.bloc);
        recyclerView = root.findViewById(R.id.recycler_salle);
        ArrayList <Salle> salles = new ArrayList<>();
        builder = new AlertDialog.Builder(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int counter = 0;
                while (counter < response.length()){
                    try {
                        JSONObject jsonObject = response.getJSONObject(counter);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String bloc = jsonObject.getString("bloc");
                        String etat = "disponible";
                        Salle salle = new Salle(id,name,bloc,etat);
                        salles.add(salle);
                        counter++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onResponse: "+salles);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),salles);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                 error.printStackTrace();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQue(jsonArrayRequest);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salle,bloc;
                salle = Salle.getText().toString();
                bloc = Bloc.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                int counter = 0;
                                while (counter < response.length()){
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(counter);
                                        int id = jsonObject.getInt("id");
                                        String name = jsonObject.getString("name");
                                        String bloc = jsonObject.getString("bloc");
                                        String etat = "disponible";
                                        Salle salle = new Salle(id,name,bloc,etat);
                                        salles.add(salle);
                                        counter++;

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Log.d(TAG, "onResponse: "+salles);
                                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),salles);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(recyclerAdapter);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
                        VolleySingleton.getInstance(getContext()).addToRequestQue(jsonArrayRequest);
                        builder.setTitle("response from server");
                        builder.setMessage("salle added successufelly");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Salle.setText("");
                                Bloc.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name",salle);
                        params.put("bloc",bloc);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getContext()).addToRequestQue(stringRequest);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}