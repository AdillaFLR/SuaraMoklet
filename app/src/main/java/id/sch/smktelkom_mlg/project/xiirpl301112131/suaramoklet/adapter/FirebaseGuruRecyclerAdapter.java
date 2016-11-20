package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by nabila on 11/20/2016.
 */
public abstract class FirebaseGuruRecyclerAdapter<ViewHolder extends RecyclerView.ViewHolder, T>
        extends RecyclerView.Adapter<ViewHolder> {

    private Query mQuery;
    private Class<T> mItemClass;
    private ArrayList<T> mItems;
    private ArrayList<String> mKeys;
    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            String key = dataSnapshot.getKey();

            if (!mKeys.contains(key)) {
                T item = dataSnapshot.getValue(FirebaseGuruRecyclerAdapter.this.mItemClass);
                int insertedPosition;
                if (previousChildName == null) {
                    mItems.add(0, item);
                    mKeys.add(0, key);
                    insertedPosition = 0;
                } else {
                    int previousIndex = mKeys.indexOf(previousChildName);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mItems.size()) {
                        mItems.add(item);
                        mKeys.add(key);
                    } else {
                        mItems.add(nextIndex, item);
                        mKeys.add(nextIndex, key);
                    }
                    insertedPosition = nextIndex;
                }
                notifyItemInserted(insertedPosition);
                itemAdded(item, key, insertedPosition);
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();

            if (mKeys.contains(key)) {
                int index = mKeys.indexOf(key);
                T oldItem = mItems.get(index);
                T newItem = dataSnapshot.getValue(FirebaseGuruRecyclerAdapter.this.mItemClass);

                mItems.set(index, newItem);

                notifyItemChanged(index);
                itemChanged(oldItem, newItem, key, index);
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();

            if (mKeys.contains(key)) {
                int index = mKeys.indexOf(key);
                T item = mItems.get(index);

                mKeys.remove(index);
                mItems.remove(index);

                notifyItemRemoved(index);
                itemRemoved(item, key, index);
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            String key = dataSnapshot.getKey();

            int index = mKeys.indexOf(key);
            T item = dataSnapshot.getValue(FirebaseGuruRecyclerAdapter.this.mItemClass);
            mItems.remove(index);
            mKeys.remove(index);
            int newPosition;
            if (previousChildName == null) {
                mItems.add(0, item);
                mKeys.add(0, key);
                newPosition = 0;
            } else {
                int previousIndex = mKeys.indexOf(previousChildName);
                int nextIndex = previousIndex + 1;
                if (nextIndex == mItems.size()) {
                    mItems.add(item);
                    mKeys.add(key);
                } else {
                    mItems.add(nextIndex, item);
                    mKeys.add(nextIndex, key);
                }
                newPosition = nextIndex;
            }
            notifyItemMoved(index, newPosition);
            itemMoved(item, key, index, newPosition);
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }


    };

    /**
     * @param query     The Firebase location to watch for data changes.
     *                  Can also be a slice of a location, using some combination of
     *                  <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>.
     * @param itemClass The class of the items.
     */
    public FirebaseGuruRecyclerAdapter(Query query, Class<T> itemClass) {
        this(query, itemClass, null, null);
    }


    /**
     * @param query     The Firebase location to watch for data changes.
     *                  Can also be a slice of a location, using some combination of
     *                  <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>.
     * @param itemClass The class of the items.
     * @param items     List of items that will load the adapter before starting the listener.
     *                  Generally null or empty, but this can be useful when dealing with a
     *                  configuration change (e.g.: reloading the adapter after a device rotation).
     *                  Be careful: keys must be coherent with this list.
     * @param keys      List of keys of items that will load the adapter before starting the listener.
     *                  Generally null or empty, but this can be useful when dealing with a
     *                  configuration change (e.g.: reloading the adapter after a device rotation).
     *                  Be careful: items must be coherent with this list.
     */
    public FirebaseGuruRecyclerAdapter(Query query, Class<T> itemClass,
                                       @Nullable ArrayList<T> items,
                                       @Nullable ArrayList<String> keys) {
        this.mQuery = query;
        if (items != null && keys != null) {
            this.mItems = items;
            this.mKeys = keys;
        } else {
            mItems = new ArrayList<T>();
            mKeys = new ArrayList<String>();
        }
        this.mItemClass = itemClass;
        query.addChildEventListener(mListener);
    }

    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(ViewHolder holder, final int position);

    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    /**
     * Clean the adapter.
     * ALWAYS call this method before destroying the adapter to remove the listener.
     */
    public void destroy() {
        mQuery.removeEventListener(mListener);
    }

    /**
     * Returns the list of items of the adapter: can be useful when dealing with a configuration
     * change (e.g.: a device rotation).
     * Just save this list before destroying the adapter and pass it to the new adapter (in the
     * constructor).
     *
     * @return the list of items of the adapter
     */
    public ArrayList<T> getItems() {
        return mItems;
    }

    /**
     * Returns the list of keys of the items of the adapter: can be useful when dealing with a
     * configuration change (e.g.: a device rotation).
     * Just save this list before destroying the adapter and pass it to the new adapter (in the
     * constructor).
     *
     * @return the list of keys of the items of the adapter
     */
    public ArrayList<String> getKeys() {
        return mKeys;
    }

    /**
     * Returns the item in the specified position
     *
     * @param position Position of the item in the adapter
     * @return the item
     */
    public T getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Returns the position of the item in the adapter
     *
     * @param item Item to be searched
     * @return the position in the adapter if found, -1 otherwise
     */
    public int getPositionForItem(T item) {
        return mItems != null && mItems.size() > 0 ? mItems.indexOf(item) : -1;
    }

    /**
     * Check if the searched item is in the adapter
     *
     * @param item Item to be searched
     * @return true if the item is in the adapter, false otherwise
     */
    public boolean contains(T item) {
        return mItems != null && mItems.contains(item);
    }

    /**
     * ABSTRACT METHODS THAT MUST BE IMPLEMENTED BY THE EXTENDING ADAPTER.
     */

    public abstract void onBindViewHolder(GuruAdapter.ViewHolder holder, int position);

    /**
     * Called after an item has been added to the adapter
     *
     * @param item     Added item
     * @param key      Key of the added item
     * @param position Position of the added item in the adapter
     */
    protected abstract void itemAdded(T item, String key, int position);

    /**
     * Called after an item changed
     *
     * @param oldItem  Old version of the changed item
     * @param newItem  Current version of the changed item
     * @param key      Key of the changed item
     * @param position Position of the changed item in the adapter
     */
    protected abstract void itemChanged(T oldItem, T newItem, String key, int position);

    /**
     * Called after an item has been removed from the adapter
     *
     * @param item     Removed item
     * @param key      Key of the removed item
     * @param position Position of the removed item in the adapter
     */
    protected abstract void itemRemoved(T item, String key, int position);

    /**
     * Called after an item changed position
     *
     * @param item        Moved item
     * @param key         Key of the moved item
     * @param oldPosition Old position of the changed item in the adapter
     * @param newPosition New position of the changed item in the adapter
     */
    protected abstract void itemMoved(T item, String key, int oldPosition, int newPosition);

}


