package cali.eventkalender.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import cali.eventkalender.R;
import cali.eventkalender.activity.EventActivity;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.NationLab;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private static final String SAVED_EVENTS = "SAVED_EVENTS";
    private static final String SAVED_ADAPTER = "SAVED_ADAPTER";
    private static final String SAVED_CONTEXT = "SAVED_CONTEXT";

    private List<Event> mEvents;
    private ArrayAdapter<Event> mAdapter;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);

        if(savedInstanceState != null) {
            mEvents = savedInstanceState.getParcelable(SAVED_EVENTS);
            mContext = savedInstanceState.getParcelable(SAVED_CONTEXT);
            mAdapter = savedInstanceState.getParcelable(SAVED_ADAPTER);
        }
        else {
            NationLab nationLab = NationLab.get(getActivity());
            mEvents = nationLab.findAllEvents();
            mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mEvents);
        }

        populateList(mAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        Event event = (Event) listView.getAdapter().getItem(position);
        if (getActivity() instanceof OnItem1SelectedListener) {
            ((OnItem1SelectedListener) getActivity()).OnItem1SelectedListener(event);
        }

        Intent intent = EventActivity.newIntent(getActivity(), event.getId());
        startActivity(intent);

        getFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        ListView listView = (ListView) layout.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Sök");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        List<Event> filteredValues = new ArrayList<Event>(mEvents);
        for (Event value : mEvents) {
            if (!value.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);
        setListAdapter(mAdapter);

        return false;
    }

    public void resetSearch() {
        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mEvents);
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(Event event);
    }

    private void populateList(ArrayAdapter<Event> adapter){
        setListAdapter(adapter);
    }

}
