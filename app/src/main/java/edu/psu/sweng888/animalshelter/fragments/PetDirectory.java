package edu.psu.sweng888.animalshelter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class PetDirectory extends Fragment {
    // declare class variables
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PetAdapter petAdapter;
    public PetDirectory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pet_directory, container, false);

        // initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);

        // initialize Firestore instance
        db = FirebaseFirestore.getInstance();

        // retrieve pet values from Firestore db
        db.collection("pets").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(petAdapter);
                } else {
                    Log.d("Document", "No data");
                }
            }
        });
        return view;
    }
}
