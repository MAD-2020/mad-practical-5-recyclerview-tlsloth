package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    final String TAG = "To-Do List";
    ArrayList<String> data = new ArrayList<>();
    EditText editText;
    Button Add;
    Adaptor mAdaptor;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Add = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);
        //populate Data with information
        data.add("Buy Milk");
        data.add("Send Postage");
        data.add("Buy Android Development Book");

        mAdaptor = new Adaptor(data);
        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdaptor);

        mAdaptor.setOnItemClickListener(new Adaptor.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                Log.v(TAG,"Entry clicked!");
                removeEntry(position);
            }
        });



    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
        editText.getText().clear();
    }

    //Function to add new entry
    public void addEntry(View v){
        String reply = editText.getText().toString();
        data.add(reply);
        //to refresh data when adding
        mAdaptor.notifyDataSetChanged();
        showNewEntry(recyclerView,data);
        Log.v(TAG,"Added new entry!");
    }
    private void removeEntry(final int position){
        Log.v(TAG,"Building dialog");
        //get text
        String text = data.get(position);
        //make dialog box
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Delete");
        b.setMessage("Are you sure you want to delete "+text+"?");
        b.setCancelable(true);
        //set buttons
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //remove item
                data.remove(position);
                Log.v(TAG,"Data removed!");
                mAdaptor.notifyDataSetChanged();
                Log.v(TAG,"User has selected yes!");

                };
            });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User has selected no");
            }
        });
        AlertDialog alert = b.create();
        alert.show();
        }


    }

