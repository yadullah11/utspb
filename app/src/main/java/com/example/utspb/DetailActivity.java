package com.example.utspb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;
import com.example.utspb.data.Api;
import com.example.utspb.data.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvUsername, tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivProfile = findViewById(R.id.ivprofile);
        tvUsername = findViewById(R.id.tvusername);
        tvBio = findViewById(R.id.tvbio);

        String username = getIntent().getStringExtra("username");

        Call<Items> itemsCall = Api.getApi().callDetailUser(username);
        itemsCall.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(@NonNull Call<Items> call, @NonNull Response<Items> response) {
                if (response.isSuccessful()){
                    Items items = response.body();
                    tvUsername.setText(items.getLogin());
                    tvBio.setText(items.getBio());
                    Glide.with(DetailActivity.this).load(items.getAvatarUrl()).into(ivProfile);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Items> call, @NonNull Throwable t) {
                Log.e("DetailActivity", t.toString());
            }
        });
    }
}
