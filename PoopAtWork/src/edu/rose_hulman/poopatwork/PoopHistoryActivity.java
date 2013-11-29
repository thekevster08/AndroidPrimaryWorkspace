package edu.rose_hulman.poopatwork;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class PoopHistoryActivity extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    
    private PoopsDbAdapter mDbHelper;
    private Cursor mPoopsCursor;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poops_list);
		mDbHelper = new PoopsDbAdapter(this);
		mDbHelper.open();
		fillData();
		registerForContextMenu(getListView());
	}
	
	@SuppressWarnings("deprecation")
	private void fillData() {
        // Get all of the rows from the database and create the item list
        mPoopsCursor = mDbHelper.fetchAllPoops();
        startManagingCursor(mPoopsCursor);

        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{PoopsDbAdapter.KEY_DATE};

        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter poops = 
            new SimpleCursorAdapter(this, R.layout.poops_row, mPoopsCursor, from, to);
        setListAdapter(poops);
 	}
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     super.onCreateOptionsMenu(menu);
	  //add menu things within this activity
	     //   menu.add(0, INSERT_ID, 0, R.string.menu_insert);
	     return true;
	 }
	
	 @Override
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
//not needed until you have a menu
		 //	     switch(item.getItemId()) {
//	         case INSERT_ID:
//	             createPoop();
//	             return true;
//	     }
	
	     return super.onMenuItemSelected(featureId, item);
	 }
	
//contextmenu is when you longpress	 
//	 @Override
//	 public void onCreateContextMenu(ContextMenu menu, View v,
//	         ContextMenuInfo menuInfo) {
//	     super.onCreateContextMenu(menu, v, menuInfo);
//	  //   menu.add(0, DELETE_ID, 0, R.string.menu_delete);
//	 }
	
//	 @Override
//	 public boolean onContextItemSelected(MenuItem item) {
//	     switch(item.getItemId()) {
//	         case DELETE_ID:
//	             AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
//	             mDbHelper.deletePoop(info.id);
//	             fillData();
//	             return true;
//	     }
//	     return super.onContextItemSelected(item);
//	 }
	
	 private void createPoop() {
		 //TODO: write this method
	    // Intent i = new Intent(this, Edit.class);
	    // startActivityForResult(i, ACTIVITY_CREATE);
	 }
	
//	 @Override
//	 protected void onListItemClick(ListView l, View v, int position, long id) {
//	 	  super.onListItemClick(l, v, position, id);
//	 	  Intent i = new Intent(this, NoteEdit.class);
//	 	  i.putExtra(PoopsDbAdapter.KEY_ROWID, id);
//	 	  startActivityForResult(i, ACTIVITY_EDIT);
//	 }
	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	     super.onActivityResult(requestCode, resultCode, intent);
	     fillData();
	 }

}
