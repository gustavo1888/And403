package co.edu.aulamatriz.reviewapplication.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.aulamatriz.reviewapplication.R;
import co.edu.aulamatriz.reviewapplication.fragments.ItemFragment.OnListFragmentInteractionListener;
import co.edu.aulamatriz.reviewapplication.models.Joke;
import co.edu.aulamatriz.reviewapplication.utilities.Constantes;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Joke} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CursorRecyclerViewAdapter extends RecyclerView.Adapter<CursorRecyclerViewAdapter.ViewHolder> {

    private Cursor mValues;
    private final OnListFragmentInteractionListener mListener;

    public CursorRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mValues.moveToPosition(position);

        holder.mItem = new Joke();
        holder.mItem.id = mValues.getInt(mValues.getColumnIndex(Constantes.COLUMN_SERVER_ID));
        holder.mItem.joke = mValues.getString(mValues.getColumnIndex(Constantes.COLUMN_JOKE));

        holder.mIdView.setText(String.valueOf(holder.mItem.id));
        holder.mContentView.setText(holder.mItem.joke);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null)
            return mValues.getCount();
        return 0;
    }

    public void swap(Cursor cursor) {
        mValues = cursor;
        notifyDataSetChanged();
    }

    public void swap() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Joke mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
