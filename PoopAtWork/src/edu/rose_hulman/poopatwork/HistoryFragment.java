package edu.rose_hulman.poopatwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
 
public class HistoryFragment extends Fragment {
	PoopDatabaseHandler db;
	//ExpandableListAdapter listAdapter;
	
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
    	View rootView = inflater.inflate(R.layout.fragment_history, container, false);
    	
    	 expListView = (ExpandableListView) rootView.findViewById(R.id.list);
    	 
         // preparing list data
         prepareListData();
  
         listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listDataChild);
  
         // setting list adapter
         expListView.setAdapter(listAdapter);
         
        
      
     //   db = new PoopDatabaseHandler(this.getActivity());
     //   List<String> poopDates = db.getAllPoopDates();
		
		//ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.list);

	//	listAdapter = new ExpandableListAdapter(this.getActivity(),poopDates,poopDates);
	//	listView.setAdapter(listAdapter);
	
		
		
		
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			  @Override
//			  public void onItemClick(AdapterView<?> parent, View view,
//			    int position, long id) {
//				  Intent i = new Intent(getActivity(), DetailActivity.class);
//				  Poop poop = db.getPoopFromDate(listAdapter.getItem(position));
//				  i.putExtra("date", poop.getDate());
//				  i.putExtra("duration", poop.getDuration());
//				  i.putExtra("amountEarned", poop.getAmountEarned());
//				  startActivity(i);
//			  }
//			}); 	
        return rootView;
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");
 
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
 
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");
 
        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");
 
        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}

