package com.example.utspb;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.utspb.data.Items;
import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.MyViewHolder> {
    private final List<Items> gitUser;
    public GithubUserAdapter(List<Items> gitUser) {
        this.gitUser = gitUser;
    }

    @NonNull
    @Override
    public GithubUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GithubUserAdapter.MyViewHolder holder, int position) {
        Items userItem = gitUser.get(position);

        holder.tvUsername.setText(userItem.getLogin());
        Glide.with(holder.itemView.getContext())
                .load(userItem.getAvatarUrl())
                .into(holder.ivProfile);
        holder.itemView.setOnClickListener( view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("username", userItem.getLogin());
            holder.itemView.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return gitUser.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivProfile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvusername);
            ivProfile = itemView.findViewById(R.id.ivprofile);
        }
    }
}
