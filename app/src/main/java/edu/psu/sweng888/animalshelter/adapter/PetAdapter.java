package edu.psu.sweng888.animalshelter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.sweng888.animalshelter.R;
import edu.psu.sweng888.animalshelter.activity.PetDetailView;
import edu.psu.sweng888.animalshelter.data.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private List<Pet> petList;
    private int selectedPosition;

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
        this.selectedPosition = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pet selected = petList.get(position);

        // determine icon to display
        holder.animalIcon.setBackgroundResource(iconConfig(selected));

        // display pet values
        holder.nameTextView.setText(selected.getName());
        holder.breedTextView.setText(selected.getBreed());
        holder.colorTextView.setText(selected.getColor());
        holder.ageTextView.setText(selected.getAge());
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

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        public CardView itemCard;
        public ImageView animalIcon;
        public TextView nameTextView, breedTextView, colorTextView, ageTextView;

        public ViewHolder(View petView) {
            super(petView);
            context = petView.getContext();

            // initialize class variables
            itemCard = petView.findViewById(R.id.itemCard);
            animalIcon = petView.findViewById(R.id.animalIcon);
            nameTextView = petView.findViewById(R.id.nameTextView);
            breedTextView = petView.findViewById(R.id.breedTextView);
            colorTextView = petView.findViewById(R.id.colorTextView);
            ageTextView = petView.findViewById(R.id.ageTextView);

            // when an item is selected, open new ProductDetailView
            itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSingleSelection(getAdapterPosition());

                    Intent intent = new Intent(context, PetDetailView.class);
                    intent.putExtra("selected_pet", petList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        private void setSingleSelection(int position) {
            if (position == RecyclerView.NO_POSITION) {
                return;
            }

            notifyItemChanged(selectedPosition);
            selectedPosition = position;
            notifyItemChanged(selectedPosition);
        }
    }
}
