package cali.eventkalender.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.activity.NationActivity;
import cali.eventkalender.model.Event;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private static final String ARG_EVENT_ID = "event_id";
    private static final String ARG_HOST_NATION_ID = "host_nation_id";
    private static final String SAVED_NATION = "SAVED_NATION";
    private static final String SAVED_EVENT = "SAVED_EVENT";

    private Nation mNation;
    private Event mEvent;
    private TextView mDateTextView;
    private TextView mTitleTextView;
    private TextView mHostNationTextView;
    private TextView mEventSummaryTextView;
    private TextView mEventAtmosphere;
    private TextView mNationAddress;

    private Button mPrePurchaseButton;

    public static EventFragment newInstance(UUID eventId, UUID hostNationId) {
            Bundle args = new Bundle();
            args.putSerializable(ARG_EVENT_ID, eventId);
            args.putSerializable(ARG_HOST_NATION_ID, hostNationId);
            EventFragment fragment = new EventFragment();
            fragment.setArguments(args);
            return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            mNation = savedInstanceState.getParcelable(SAVED_NATION);
            mEvent = savedInstanceState.getParcelable(SAVED_EVENT);
            for(Event event: mNation.getEvents()) {
                if(event.getId() == mEvent.getId()){
                    mEvent.setNation(mNation);
                }
            }
        }
        else {
            UUID hostNationId = (UUID) getArguments().getSerializable(ARG_HOST_NATION_ID);
            UUID eventId = (UUID) getArguments().getSerializable(ARG_EVENT_ID);
            mNation = NationLab.get(getActivity()).getNation(hostNationId);
            mEvent = mNation.getEvent(eventId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        mTitleTextView = (TextView) view.findViewById(R.id.event_title);
        mTitleTextView.setText(mEvent.getTitle());

        mHostNationTextView = (TextView) view.findViewById(R.id.event_host_nation);
        mHostNationTextView.setText(mNation.getName());
        mHostNationTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = NationActivity.newIntent(getContext(), mNation.getId());
                startActivity(intent);
            }
        });


        // Extrahera önskvärd information av datumet och visa snyggare i uppkommande prototyp
        mDateTextView = (TextView) view.findViewById(R.id.event_date);
        mDateTextView.setText(mEvent.getDate().toString());

        mEventSummaryTextView = (TextView) view.findViewById(R.id.event_summary);
        mEventSummaryTextView.setText(mEvent.getSummary());

        mEventAtmosphere = (TextView) view.findViewById(R.id.event_atmosphere);
        mEventAtmosphere.setText("Typ av Evenemang: \n" + mEvent.getEventType());

        mNationAddress = (TextView) view.findViewById(R.id.event_address);
        mNationAddress.setText("Adress: \n" + mNation.getAddress());

        mPrePurchaseButton = (Button) view.findViewById(R.id.event_prepurchase);
        mPrePurchaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Snart så, snart slipper vi köa.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_NATION, mNation);
        outState.putParcelable(SAVED_EVENT, mEvent);
    }

}
