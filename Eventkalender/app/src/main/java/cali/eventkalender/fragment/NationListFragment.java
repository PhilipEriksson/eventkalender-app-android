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
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import cali.eventkalender.R;
import cali.eventkalender.activity.NationActivity;
import cali.eventkalender.model.Nation;
import cali.eventkalender.model.NationLab;

/**
 * A simple {@link Fragment} subclass.
 */
public class NationListFragment extends Fragment {

    private RecyclerView mNationRecyclerView;
    private NationAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nation_list, container, false);
        mNationRecyclerView = (RecyclerView) view.findViewById(R.id.nation_recycler_view);
        mNationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        NationLab nationLab = NationLab.get(getActivity());
        List<Nation> nations = nationLab.getNations();
        mAdapter = new NationAdapter(nations);
        mNationRecyclerView.setAdapter(mAdapter);

    }

    private class NationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Nation mNation;
        private TextView mNameTextView;
        private ImageView mNationLogo;
        private ImageView mNationBackground;


        public NationHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_nation, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.nation_name);
            mNationLogo = (ImageView) itemView.findViewById(R.id.nation_logo);
            mNationBackground = (ImageView) itemView.findViewById(R.id.nation_background);
        }

        public void bind(Nation nation) {
            mNation = nation;
            mNameTextView.setText(mNation.getName());

            setImages(nation, mNationLogo, mNationBackground);
        }

        @Override
        public void onClick(View view) {
            Intent intent = NationActivity.newIntent(getActivity(), mNation.getId());
            startActivity(intent);
        }

    }

    private class NationAdapter extends RecyclerView.Adapter<NationHolder> {

        private List<Nation> mNationList;

        public NationAdapter(List<Nation> nations) {
            mNationList = nations;
        }

        @Override
        public NationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new NationHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(NationHolder holder, int position) {
            Nation nation = mNationList.get(position);
            holder.bind(nation);
        }

        @Override
        public int getItemCount() {
            return mNationList.size();
        }
    }

    /**
     *
     * @param nation
     * @param imageViewLogo
     * @param imageViewBackground Används ej atm. används senare om man dynamisk vill ändra nationsbakgrunden, nu är det en placeholder
     * @return
     */
    public boolean setImages(Nation nation, ImageView imageViewLogo, ImageView imageViewBackground) {

        UUID id = nation.getId();
        String name = nation.getName();

        switch (name) {

            case "Malmö Nation" :
                imageViewLogo.setImageResource(R.drawable.malmologo);
             //   imageViewBackground.setBackgroundResource(R.mipmap.malmo);
                return true;
            case "Lunds Nation" :
                imageViewLogo.setImageResource(R.drawable.lundslogo);

                return true;
            case "Göteborgs Nation" :
                imageViewLogo.setImageResource(R.drawable.goteborgslogo);

                return true;
            case "Hallands Nation" :
                imageViewLogo.setImageResource(R.drawable.hallandslogo);

                return true;
            case "Blekingska Nation" :
                imageViewLogo.setImageResource(R.drawable.blekingskalogo);

                return true;
            case "Kristianstad Nation" :
                imageViewLogo.setImageResource(R.drawable.kristianstadlogo);

                return true;
            case "Östgöta Nation" :
                imageViewLogo.setImageResource(R.drawable.ostgotalogo);

                return true;
            case "Helsingkrona Nation" :
                imageViewLogo.setImageResource(R.drawable.helsingkronalogo);

                return true;
            case "Sydskånska Nation" :
                imageViewLogo.setImageResource(R.drawable.sydskanskalogo);

                return true;
            case "Wermlands Nation" :
                imageViewLogo.setImageResource(R.drawable.wermlandslogo);

                return true;
            case "Kalmar Nation" :
                imageViewLogo.setImageResource(R.drawable.kalmarlogo);
                return true;
            case "Västgöta Nation" :
                imageViewLogo.setImageResource(R.drawable.vastgotalogo);
                return true;

            default :
                return false;

        }

    }
}
