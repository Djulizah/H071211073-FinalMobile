package com.example.h071211073_finalmobile;

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

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private final List<Bookmark> bookmarkList;
    public BookmarkAdapter(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }
    private ClickListener clickListener;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_marked, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.setData(bookmark);
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvYear;
        ImageView imgPoster, imgIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            imgPoster = itemView.findViewById(R.id.img_poster);
            imgIcon = itemView.findViewById(R.id.img_icon);
        }

        public void setData(Bookmark bookmark) {
            tvTitle.setText(String.valueOf(bookmark.getTitle()));
            tvYear.setText(String.valueOf(bookmark.getDate()));

            String posterPath = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + bookmark.getPosterPath();;
            Glide.with(itemView.getContext())
                    .load(posterPath)
                    .into(imgPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("bookmark", bookmark);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
    interface ClickListener{
        void onItemClicked(Bookmark bookmark);
    }
}
