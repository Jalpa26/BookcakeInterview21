package com.example.interview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.interview.R;
import com.example.interview.dto.Hit;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    Context context;
    ArrayList<Hit> hitsArrayList;
    OnClickLisner onClickListner;

    public ImagesAdapter(Context context, ArrayList<Hit> hitsArrayList,OnClickLisner onClickLisner) {
        this.context = context;
        this.hitsArrayList = hitsArrayList;
        this.onClickListner=onClickLisner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hit hit=hitsArrayList.get(position);

        if(hit.getPreviewURL()!=null && !hit.getPreviewURL().equals("")){
            Glide.with(context).load(hit.getPreviewURL()).into(holder.iv_preview);
        }
        if(hit.getUser()!=null && !hit.getUser().equals("")){
            holder.tvName.setText(hit.getUser());
        }
        if(hit.getLikes()!=null && !hit.getLikes().equals("")){
            holder.tvLike.setText("Likes: \n"+hit.getLikes().toString());
        }
        if(hit.getDownloads()!=null && !hit.getDownloads().equals("")){
            holder.tvDownload.setText("DownLoads: \n"+hit.getDownloads().toString());
        }
        if(hit.getViews()!=null && !hit.getViews().equals("")){
            holder.tvView.setText("Views: \n"+hit.getViews().toString());
        }
        if(hit.getComments()!=null && !hit.getComments().equals("")){
            holder.tvComment.setText("Comments: \n"+hit.getComments().toString());
        }

        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hitsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_preview;
        private TextView tvName,tvLike,tvComment,tvView,tvDownload;
        private RelativeLayout rlContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_preview=itemView.findViewById(R.id.iv_image);
            tvName=itemView.findViewById(R.id.tv_name);
            tvLike=itemView.findViewById(R.id.tv_like);
            tvComment=itemView.findViewById(R.id.tv_comment);
            tvDownload=itemView.findViewById(R.id.tv_downlaod);
            tvView=itemView.findViewById(R.id.tv_views);
            rlContainer=itemView.findViewById(R.id.rl_container);

        }
    }

   public interface OnClickLisner{
        public void onClick(int position);
    }
}
