package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    public ViewHolder(View itemView, final Adaptor.OnItemClickListener onItemClickListener){
        super(itemView);
        txt = itemView.findViewById(R.id.textView);
        //set onclick listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }
}
