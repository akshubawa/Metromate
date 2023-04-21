package com.example.amigos.metromate;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    private ArrayList<String> interchange;
    Context context;
    ArrayList<CardModel> arrCard;

    RecyclerCardAdapter(Context context, ArrayList<CardModel> arrCard) {
        this.context = context;
        this.arrCard = arrCard;
        this.interchange = interchange;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.path_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, interchange);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.path_name.setText(arrCard.get(position).name);
        holder.path_line.setText(arrCard.get(position).line);
        holder.show_interchange.setText(arrCard.get(position).show_interchange);

        /*String line = arrCard.get(position).line;
        String nextLine = "";

        if (position + 1 < arrCard.size()) {
            nextLine = arrCard.get(position + 1).line;
        }

        if (!line.equals(nextLine) & line.equals("Red")) {
            Drawable myDrawable = context.getResources().getDrawable(R.drawable.baseline_train_24_red);
            holder.train_color.setImageDrawable(myDrawable);
        }*/

        String line = arrCard.get(position).line;

       /* if (interchange != null) {
            //String line = arrCard.get(getAdapterPosition()).line;
            if (interchange.contains(line)) {
                show_interchange.setText("Interchange");
            } else {
                show_interchange.setText("");
            }
        }*/


        if (line.equals("Blue")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroBlue));
        } else if (line.equals("Bluebranch")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroBlueBranch));
        } else if (line.equals("Magenta")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroMagenta));
        } else if (line.equals("Yellow")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroYellow));
        } else if (line.equals("Red")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroRed));
        } else if (line.equals("Green")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroGreen));
        } else if (line.equals("Greenbranch")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroGreenBranch));
        } else if (line.equals("Violet")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroViolet));
        } else if (line.equals("Pink")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroPink));
        } else if (line.equals("Pinkbranch")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroPinkBranch));
        } else if (line.equals("Orange")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroOrange));
        } else if (line.equals("Aqua") || line.equals("300m Walkway/Free-e-Rickshaw")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroAqua));
        } else if (line.equals("Grey")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroGrey));
        } else if (line.equals("Rapidmetro") || line.equals("Rapid")) {
            holder.metro_color.setBackgroundColor(context.getResources().getColor(R.color.metroRapid));
        }

    }

    @Override
    public int getItemCount() {
        return arrCard.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        String letsShow;
        TextView path_name, path_line, show_interchange;
        LinearLayout metro_color;

        public ViewHolder(View itemView, ArrayList<String> interchange) {
            super(itemView);

            path_name = itemView.findViewById(R.id.path_name);
            path_line = itemView.findViewById(R.id.path_line);
            metro_color = itemView.findViewById(R.id.metro_color);
            show_interchange = itemView.findViewById(R.id.show_interchange);

        }
    }
}