package org.cherchi.reversi.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * Represents a confirmation dialog
 * @author Fernando Cherchi
 *
 */
public class ConfirmationDialog {
	
	// ///////////////////////// CONSTANTS /////////////////////////////////
	/**
	 * The yes button 
	 */
	public static int YES_BUTTON = 0;
	
	/**
	 * The no button 
	 */
	public static int NO_BUTTON = 1;
	
	// ///////////////////////// FIELDS ///////////////////////////////////
	
	/**
	 * The result
	 */
	private boolean result;
	
	/**
	 * The listener of the response
	 */
	private OnClickListener listener = null;
	
	
	// ///////////////////////// LIFETIME ///////////////////////////////////
	
	/**
	 * The constructor
	 * @param listener
	 */
	public ConfirmationDialog(OnClickListener listener) {
		this.listener = listener;
	}
	
	
	// ///////////////////////// PUBLIC METHODS ////////////////////////////
	
	/**
	 * Opens a confirmation message
	 * @param context
	 * @param msg
	 * @return
	 */
	public void showConfirmation(Context context, String msg) {
		
		CharSequence[] options = new CharSequence[2];
		options[0] = context.getString(R.string.yes_caption);
		options[1] = context.getString(R.string.no_caption);
		
		Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.confirm_title);
		builder.setItems(options, this.listener);
		builder.show();
	}
	
	
	

}
