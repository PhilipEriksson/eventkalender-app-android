package cali.eventkalender.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.activity.EventActivity;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsListFragment extends Fragment {

    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;

    public EventsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        NationLab nationLab = NationLab.get(getActivity());
        List<Event> events = nationLab.findAllEvents();

        mAdapter = new EventAdapter(events);
        mEventRecyclerView.setAdapter(mAdapter);

    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Event mEvent;
        private TextView mNameTextView;
        private TextView mEventNationTextView;
        private ImageView mNationBackground;


        public EventHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_event, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.event_name);
            mEventNationTextView = (TextView) itemView.findViewById(R.id.event_host_nation);
            mNationBackground = (ImageView) itemView.findViewById(R.id.nation_background);
        }

        public void bind(Event event) {
            mEvent = event;
            mNameTextView.setText(mEvent.getTitle());
            mEventNationTextView.setText(mEvent.getNation().getName());

          //  setImages(event.getNation(), mNationBackground); // Används senare när man ska sätta unika bilder.
        }

        @Override
        public void onClick(View view) {
            Intent intent = EventActivity.newIntent(getActivity(), mEvent.getId());
            startActivity(intent);
        }

    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {

        private List<Event> mEventList;

        public EventAdapter(List<Event> events) {
            mEventList = events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new EventHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Event event = mEventList.get(position);
            holder.bind(event);
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }
    }

    /**
     *
     * @param nation
     * @param imageViewBackground Används ej atm.används senare om man dynamisk vill ändra eventssbakgrunden, nu är det en placeholder
     * @return
     */
    public boolean setImages(Nation nation, ImageView imageViewBackground) {

        UUID id = nation.getId();
        String name = nation.getName();

        switch (name) {

            case "Malmö Nation" :
                //   imageViewBackground.setBackgroundResource(R.mipmap.malmo);
                return true;
            case "Lunds Nation" :

                return true;
            case "Göteborgs Nation" :

                return true;
            case "Hallands Nation" :

                return true;
            case "Blekingska Nation" :

                return true;
            case "Kristianstad Nation" :

                return true;
            case "Östgöta Nation" :

                return true;
            case "Helsingkrona Nation" :

                return true;
            case "Sydskånska Nation" :

                return true;
            case "Wermlands Nation" :

                return true;
            case "Kalmar Nation" :
                return true;
            case "Västgöta Nation" :
                return true;

            default :
                return false;

        }

    }
}
