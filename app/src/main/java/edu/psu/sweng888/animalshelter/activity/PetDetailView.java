package edu.psu.sweng888.animalshelter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.psu.sweng888.animalshelter.R;
import edu.psu.sweng888.animalshelter.data.Pet;

public class PetDetailView extends AppCompatActivity {
    private ImageView animalIcon;
    private TextView petIntroduction;
    private Button backButton, adoptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_detail_view);

        // initialize class variables
        this.animalIcon = findViewById(R.id.animalIcon);
        this.petIntroduction = findViewById(R.id.petIntroduction);
        this.backButton = findViewById(R.id.backButton);
        this.adoptButton = findViewById(R.id.adoptButton);

        // get the selected Product from the Intent extras
        Pet selected = (Pet) getIntent().getSerializableExtra("selected_pet");
        assert selected != null;

        // determine icon to display
        this.animalIcon.setBackgroundResource(iconConfig(selected));

        // write and display introduction
        this.petIntroduction.setText(writeIntroduction(selected));

        // when backButton is clicked
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetDetailView.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Returned successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        // when adoptButton is clicked
        this.adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove from db
            }
        });
    }

    /**
     * determines which icon to display based on the selected Pet's type (e.g., Dog, Cat, Bunny, or Hamster)
     *
     * @param selected
     * @return resource id of the desired icon
     */
    private int iconConfig(Pet selected) {
        if (selected.getType().equals("Dog") || selected.getType().equals("Puppy")) {
            return R.drawable.dog_icon;
        } else if (selected.getType().equals("Cat") || selected.getType().equals("Kitten")) {
            return R.drawable.cat_icon;
        } else if (selected.getType().equals("Bunny")) {
            return R.drawable.bunny_icon;
        } else if (selected.getType().equals("Hamster")) {
            return R.drawable.hamster_icon;
        }
        return -1;
    }

    /**
     * generates a unique introduction for each Pet type (e.g., Dog, Cat, Bunny, or Hamster) with the selected Pet's values
     *
     * @param selected
     * @return short introductory paragraph for the selected Pet
     */
    private String writeIntroduction(Pet selected) {
        if (selected.getType().equals("Dog")) {
            return ("Woof! My name is " + selected.getName() + "! I'm a playful " + selected.getBreed() + " with a luscious " + selected.getColor() + " coat that shines in the sunlight. At " + selected.getAge() + " years old, I'm bursting with energy and ready to be someone's loyal companion. I've been at the shelter for " + selected.getDaysInShelter() + " days now, wagging my tail in anticipation of finding my forever home. My adoption fee is $" + selected.getAdoptionFee() + ", but I promise to give you endless love, tail wags, and sloppy kisses in return!");
        } else if (selected.getType().equals("Puppy")) {
            return ("Woof woof! I'm " + selected.getName() + ", an adorable " + selected.getBreed() + " puppy with soft " + selected.getColor() + " fur and big, soulful eyes that will melt your heart. At just " + ((int)(Math.random() * 12) + 1) + " months old, I'm a bundle of boundless energy, always ready to play fetch or go on adventures. I've only been at the shelter for " + selected.getDaysInShelter() + " days, but I'm already eager to find a loving family to call my own. My adoption fee is $" + selected.getAdoptionFee() + ", but I promise to fill your days with puppy kisses, tail wags, and endless cuddles!");
        } else if (selected.getType().equals("Cat")) {
            return ("Meow! I'm " + selected.getName() + ", a gorgeous " + selected.getBreed() + " cat with " + selected.getColor() + " fur that will captivate your heart. At " + selected.getAge() + " years old, I'm still a playful kitten at heart, but I also love curling up in warm laps for long naps. I've been patiently waiting at the shelter for a loving family to take me home for about " + selected.getDaysInShelter() + "days now. My adoption fee is $" + selected.getAdoptionFee() + ", but I come with purrs, cuddles, and a whole lot of love!");
        } else if (selected.getType().equals("Kitten")) {
            return("Meow! I'm " + selected.getName() + ", a fluffy " + selected.getColor() + " " + selected.getBreed() + " kitten with striking eyes that sparkle with mischief. At " + ((int)(Math.random() * 12) + 1) + " months old, I'm still learning the ropes of being a cat, but I already excel at chasing feather toys and taking cozy catnaps in sunny spots. I've been at the shelter for " + selected.getDaysInShelter() + " days now, patiently waiting for someone to scoop me up and give me a warm lap to curl up on. My adoption fee is $" + selected.getAdoptionFee() + ", but I'll repay you with purrs, headbutts, and a lifetime of love and companionship!");
        } else if (selected.getType().equals("Bunny")) {
            return("Hop, hop! I'm " + selected.getName() + ", a sweet " + selected.getBreed() + " bunny with the softest " + selected.getColor() + " fur you've ever felt. At " + selected.getAge() + " year old, I've already mastered the art of binkying and love hopping around the shelter, spreading joy wherever I go. I've been here for about " + selected.getDaysInShelter() + " days now, waiting for the perfect family to take me home and give me all the love and attention I deserve. My adoption fee is $" + selected.getAdoptionFee() + ", but I'll repay you with endless cuddles, gentle nose boops, and adorable bunny antics!");
        } else if (selected.getType().equals("Hamster")) {
            return("Squeak! I'm " + selected.getName() + ", a tiny " + selected.getBreed() + " hamster with soft " + selected.getColor() + " fur that's as fluffy as a cloud. Despite my small size, I've got a big personality and love to explore every nook and cranny of my cage. At just " + selected.getAge() + " years old, I'm still a curious little critter eager to find a loving owner to shower me with attention. I've been at the shelter for " + selected.getDaysInShelter() + " days, patiently waiting for someone to scoop me up. My adoption fee is $" + selected.getAdoptionFee() + ", but I'll bring endless entertainment and adorable squeaks into your life!");
        }
        return ("");
    }
}