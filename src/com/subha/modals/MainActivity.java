package com.subha.modals;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	int mStackLevel = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showDialog(View v) {
	    mStackLevel++;

	    // DialogFragment.show() will take care of adding the fragment
	    // in a transaction.  We also want to remove any currently showing
	    // dialog, so make our own transaction and take care of that here.
	    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
	    if (prev != null) {
	        ft.remove(prev);
	    }
	    ft.addToBackStack(null);

	    // Create and show the dialog.
	    DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
	    newFragment.show(ft, "dialog");
	    getSupportFragmentManager().executePendingTransactions();
	    
	    prettifyDialog(newFragment);
	}
	
	// This method fixes up the dimensions of the dialog and appropriately sets the title
	private void prettifyDialog(DialogFragment dialogFragment) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		Dialog dialog = dialogFragment.getDialog();
		Log.d("subha", "is dialog null? " + dialog);
		Log.d("subha", "is window null? " + dialog.getWindow());
		dialog.getWindow().setLayout((4 * width)/ 5, (4 * height)/ 5);
		
		dialog.setTitle("Add a new run.");
	}
	
	public void callback() {
		Toast.makeText(this, "GOT CALLBACK", Toast.LENGTH_SHORT).show();
	}
}
