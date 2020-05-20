package sg.edu.np.mad.mad_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> data;
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener o) {
        this.onItemClickListener = o;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);


    }

    public Adaptor(ArrayList<String> input){
        data = input;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox_layout,parent,false);
        return new ViewHolder(item,onItemClickListener);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        String s = data.get(position);
        holder.txt.setText(s);

    }

    public int getItemCount(){
        return data.size();
    }
}
