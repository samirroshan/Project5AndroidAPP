/**
 * Custom ArrayAdapter with the ability to highlight a selected item in the list.
 *
 * @param <String> The type of data in the adapter.
 * @see ArrayAdapter
 * @see Context
 * @see View
 * @see ViewGroup
 */
package com.example.project5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**

 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class HighlightArrayAdapter extends ArrayAdapter<String> {
    private int selectedPosition = -1;
    /**
     * Constructs a new HighlightArrayAdapter.
     *
     * @param context  The context in which the adapter is created.
     * @param resource The resource ID for a layout file containing a TextView to use when instantiating views.
     * @param objects  The objects to represent in the ListView.
     * @see ArrayAdapter#ArrayAdapter(Context, int, List)
     */
    public HighlightArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    /**
     * Sets the position of the currently selected item in the list.
     *
     * @param position The position to set as the selected item.
     */

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    /**
     * Retrieves the position of the currently selected item in the list.
     *
     * @return The position of the selected item.
     */
    public int getSelectedPosition(){return selectedPosition;}

    /**
     * Overrides the default getView method to provide custom highlighting for the selected item.
     *
     * @param position    The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to.
     * @return The view associated with the specified position.
     * @see ArrayAdapter#getView(int, View, ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position == selectedPosition) {
            view.setBackgroundColor(Color.LTGRAY); // Highlight color
        } else {
            view.setBackgroundColor(Color.TRANSPARENT); // Default color
        }
        return view;
    }
}
