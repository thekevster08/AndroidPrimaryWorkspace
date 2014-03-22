package edu.rose_hulman.poopatwork;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SettingsDialogFragment extends DialogFragment{
	   public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("fire missle")
	               .setPositiveButton("fire", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // FIRE ZE MISSILES!
	                   }
	               })
	               .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
}
