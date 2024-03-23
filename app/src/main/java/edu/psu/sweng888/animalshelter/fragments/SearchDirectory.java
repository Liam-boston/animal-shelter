package edu.psu.sweng888.animalshelter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.psu.sweng888.animalshelter.R;
import edu.psu.sweng888.animalshelter.adapter.PetAdapter;
import edu.psu.sweng888.animalshelter.data.Pet;

public class SearchDirectory extends Fragment implements AdapterView.OnItemSelectedListener {
    // declare class variables
    private FirebaseFirestore db;
    private RecyclerView searchRecyclerView;
    private LinearLayoutManager layoutManager;
    private PetAdapter petAdapter;
    private Spinner dropdownMenu;


    public SearchDirectory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_directory, container, false);

        // initialize Firestore instance
        db = FirebaseFirestore.getInstance();

        // initialize UI elements
        searchRecyclerView = view.findViewById(R.id.search_recycler_view);
        dropdownMenu = view.findViewById(R.id.search_dropdown);

        // configure Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdownMenu.setAdapter(adapter);
        dropdownMenu.setOnItemSelectedListener(this);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String type = parent.getItemAtPosition(pos).toString();
        Log.d("Selected", type);

        // retrieve Pet values of selected type from Firestore db
        db.collection("pets").whereEqualTo("type", type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                // initialize empty list
                List<Pet> petList = new ArrayList<>();

                if (task.isSuccessful()) {
                    // add each Pet from Firestore to the list
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Pet p = document.toObject(Pet.class);
                        petList.add(p);
                    }

                    // populate RecyclerView with values from Firestore db
                    petAdapter = new PetAdapter(petList);
                    layoutManager = new LinearLayoutManager(view.getContext());
                    searchRecyclerView.setLayoutManager(layoutManager);
                    searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    searchRecyclerView.setAdapter(petAdapter);
                } else {
                    Log.d("Document", "No data");
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // by default display Pets of type "Dog"
    }

}
