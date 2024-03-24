package edu.psu.sweng888.animalshelter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import edu.psu.sweng888.animalshelter.R;
import edu.psu.sweng888.animalshelter.adapter.PetAdapter;

public class FindNewHome extends Fragment implements AdapterView.OnItemSelectedListener {
    private FirebaseFirestore db;
    private EditText nameEditText, breedEditText, colorEditText, ageEditText;
    private Button addButton;
    private Spinner dropdownMenu;
    private String type;

    public FindNewHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_new_home, container, false);

        // initialize Firestore instance
        db = FirebaseFirestore.getInstance();

        // initialize UI elements
        nameEditText = view.findViewById(R.id.nameEditText);
        breedEditText = view.findViewById(R.id.breedEditText);
        colorEditText = view.findViewById(R.id.colorEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        addButton = view.findViewById(R.id.addButton);
        dropdownMenu = view.findViewById(R.id.new_home_drop_down);

        // configure Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdownMenu.setAdapter(adapter);
        dropdownMenu.setOnItemSelectedListener(this);

        // when addButton is clicked
        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();

                Map<String, Object> pet = new HashMap<>();
                pet.put("name", name);
                pet.put("type", type);
                pet.put("breed", breedEditText.getText().toString());
                pet.put("color", colorEditText.getText().toString());
                pet.put("age", ageEditText.getText().toString());
                pet.put("daysInShelter", 0);
                pet.put("adoptionFee", 100); // default valuation until animal is seen-on-site

                db.collection("pets").document(name).set(pet)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "Thank you for putting " + name + " up for adoption! We'll review your application and get back to you shortly.", Toast.LENGTH_SHORT).show();
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Adoption application for " + name + " failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        type = parent.getItemAtPosition(pos).toString();
        Log.d("Selected", type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // by default display Pets of type "Dog"
    }
}
