package com.example.bloc_salle_app.ui.bloc;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bloc_salle_app.R;
import com.example.bloc_salle_app.VolleySingleton;
import com.example.bloc_salle_app.adapter.RecyclerBlocAdapter;
import com.example.bloc_salle_app.beans.Bloc;
import com.example.bloc_salle_app.databinding.FragmentBlocBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class BlocFragment extends Fragment {

    private BlocViewModel blocViewModel;
    private FragmentBlocBinding binding;
    private Button btn_bloc;
    private String url = "https://bloc-salle-app.herokuapp.com/api/blocs";
    private RecyclerBlocAdapter recyclerBlocAdapter;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        blocViewModel = new ViewModelProvider(this).get(BlocViewModel.class);
        binding = FragmentBlocBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycler_bloc);
        ArrayList<Bloc> blocs = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int counter = 0;
                while (counter < response.length()){
                    try {
                        JSONObject jsonObject = response.getJSONObject(counter);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        Bloc bloc = new Bloc(id,name);
                        blocs.add(bloc);
                        counter ++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onResponse: "+ blocs);
                recyclerBlocAdapter = new RecyclerBlocAdapter(getContext(),blocs);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerBlocAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQue(jsonArrayRequest);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}