package android.appdevgenie.voyage;


import android.appdevgenie.voyage.database.NewEntry;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VoyageAdapter extends RecyclerView.Adapter<VoyageAdapter.EntryViewHolder> {

    private static final int MAX_INPUT_LENGTH = 50;

    private Context context;
    final private ItemClickListener itemClickListener;
    private List<NewEntry> newEntries;

    public VoyageAdapter(Context ctx, ItemClickListener listener) {
        this.context = ctx;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.entry_list_item, parent, false);

        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {

        NewEntry newEntry = newEntries.get(position);
        String date = newEntry.getEntryDate();
        String time = newEntry.getEntryTime();
        String info = newEntry.getThoughts();
        if(info.length() > MAX_INPUT_LENGTH){
            info = info.substring(0, MAX_INPUT_LENGTH).concat("...");
        }

        holder.tvDate.setText(date);
        holder.tvTime.setText(time);
        holder.tvThoughts.setText(info);
        holder.ivMood.setImageResource(newEntry.getMood());

    }

    @Override
    public int getItemCount() {
        if(newEntries == null) {
            return 0;
        }
        return newEntries.size();
    }

    class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvDate;
        private TextView tvTime;
        private TextView tvThoughts;
        private ImageView ivMood;

        public EntryViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvItemDate);
            tvTime = itemView.findViewById(R.id.tvItemTime);
            tvThoughts = itemView.findViewById(R.id.tvItemInfo);
            ivMood = itemView.findViewById(R.id.ivItemMood);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = newEntries.get(getAdapterPosition()).getId();
            itemClickListener.onItemClickListener(elementId);
        }
    }

    public void setEntries(List<NewEntry> newEntriesList){
        newEntries = newEntriesList;
        notifyDataSetChanged();
    }

    public List<NewEntry> getEntries(){
        return newEntries;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

}
