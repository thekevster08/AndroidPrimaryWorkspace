package edu.rose_hulman.poopatwork;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class HistoryFragment extends Fragment {
	PoopDatabaseHandler db;
	ArrayAdapter<String> listAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
      
        db = new PoopDatabaseHandler(this.getActivity());
        List<String> poopDates = db.getAllPoopDates();
		
		ListView listView = (ListView) rootView.findViewById(R.id.list);

		listAdapter = new ArrayAdapter<String> (this.getActivity(),android.R.layout.simple_list_item_1,poopDates);
		listView.setAdapter(listAdapter);
	
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				  Intent i = new Intent(getActivity(), DetailActivity.class);
				  Poop poop = db.getPoopFromDate(listAdapter.getItem(position));
				  i.putExtra("date", poop.getDate());
				  i.putExtra("duration", poop.getDuration());
				  i.putExtra("amountEarned", poop.getAmountEarned());
				  startActivity(i);
			  }
			}); 	
        return rootView;
    }
}

