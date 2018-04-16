package cr.ac.unadeca.prfinal.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cr.ac.unadeca.prfinal.R;
import cr.ac.unadeca.prfinal.activities.bigpic;
import cr.ac.unadeca.prfinal.subclases.ImageViewHolder;
import cr.ac.unadeca.prfinal.subclases.RunPic;

/**
 * Created by Freddy on 4/15/2018.
 */

public class PicAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final List<RunPic> listRunImage;
    private final LayoutInflater inflater;
    private Context context;//manda el contexto

    public PicAdapter(List<RunPic> listRunImages, Context contexto) {
        this.context= contexto;
        this.inflater = LayoutInflater.from(this.context);
        this.listRunImage = listRunImages;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.picholder, parent, false);
        return new ImageViewHolder(view);
    }

    public void animateTo(List<RunPic> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<RunPic> newModels) {
        for (int i = listRunImage.size() - 1; i >= 0; i--) {
            final RunPic model = listRunImage.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<RunPic> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final RunPic model = newModels.get(i);
            if (!listRunImage.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<RunPic> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final RunPic model = newModels.get(toPosition);
            final int fromPosition = listRunImage.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public RunPic removeItem(int position) {
        final RunPic model = listRunImage.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, RunPic model) {
        listRunImage.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final RunPic model = listRunImage.remove(fromPosition);
        listRunImage.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        final RunPic current = listRunImage.get(position);
        holder.image.setVisibility(View.VISIBLE);
        if(current.url != null){
            if(current.url.isEmpty()){
                Functions.loadImage(holder.image, context );
            }else {
                Functions.loadImage(current.url, holder.image, context);
            }
        }else {
            Functions.loadImage(holder.image, context);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, bigpic .class);
                intent.putExtra("name", current.name);
                intent.putExtra("author", current.author);
                intent.putExtra("url",current.url);
                context.startActivity(intent);
            }
        });
        holder.bindTo ();
    }



    @Override
    public int getItemCount() {
        return listRunImage.size();
    }




}

