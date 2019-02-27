package cali.eventkalender.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cali.eventkalender.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private LinearLayout mTicketLayout;
    private LinearLayout mPhoneLayout;
    private LinearLayout mEmailLayout;
    private LinearLayout mFAQLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mTicketLayout = (LinearLayout) view.findViewById(R.id.ticket_layout);
     //   mTicketLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
        mTicketLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),
                        "Biljetter", Toast.LENGTH_LONG).show();
            }
        });

        mPhoneLayout = (LinearLayout) view.findViewById(R.id.phone_layout);
        mPhoneLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),
                        "Ändra telefonnummer", Toast.LENGTH_LONG).show();
            }
        });

        mEmailLayout = (LinearLayout) view.findViewById(R.id.email_layout);
        mEmailLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),
                        "Ändra email", Toast.LENGTH_LONG).show();
            }
        });

        mFAQLayout = (LinearLayout) view.findViewById(R.id.faq_layout);
        mFAQLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),
                        "Frågor", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
