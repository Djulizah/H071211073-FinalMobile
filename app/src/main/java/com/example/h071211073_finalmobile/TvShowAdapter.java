package com.example.h071211073_finalmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {
    private List<TvShow> tvShowList;
    public TvShowAdapter(List<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }
    private ClickListener clickListener;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poster_gradient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, int position) {
        TvShow tvShow = tvShowList.get(position);
        holder.setData(tvShow);
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvYear;
        ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        public void setData(TvShow tvShow) {
            tvTitle.setText(String.valueOf(tvShow.getName()));
            tvYear.setText(String.valueOf(tvShow.getFirstAirDate()));

            String posterPath = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterPath();;
            Glide.with(itemView.getContext())
                    .load(posterPath)
                    .into(imgPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("tv_show", tvShow);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
    interface ClickListener{
        void onItemClicked(TvShowResponse tvShowResponse);
    }
}
