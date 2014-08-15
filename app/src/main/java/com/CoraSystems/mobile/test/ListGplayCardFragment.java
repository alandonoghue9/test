package com.CoraSystems.mobile.test;

/**
 * Created by eoghanmartin on 08/08/2014.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * List of Google Play cards Example
 *
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class ListGplayCardFragment extends Fragment {

    String strtext;
    public String ok;
    LinearLayout comp;
    LinearLayout plan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//      strtext = getArguments().getString("edttext");
        View view = inflater.inflate(R.layout.demo_fragment_list_gplaycard, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }


    private void initCards() {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0;i<6;i++){
            GooglePlaySmallCard card = new GooglePlaySmallCard(this.getActivity());
            card.setTitle("Project "+i);
            card.setSecondaryTitle("Task" + i);

            card.count=i;

            card.init();

            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_gplaycard);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    /**
     * This class provides a simple card as Google Play
     *
     * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
     */
    public class GooglePlaySmallCard extends Card {

        protected TextView mTitle;
        protected TextView mSecondaryTitle;
        protected int count;

        protected String title;
        protected String secondaryTitle;

        public GooglePlaySmallCard(Context context) {
            this(context, R.layout.carddemo_mycard_inner_content);
        }

        public GooglePlaySmallCard(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        private void init() {

            //Add ClickListener
            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Intent i = new Intent(getActivity(), Timesheet.class);
                    startActivity(i);
                    }
            });

            //Swipe
            /*if (count==10){

                setTitle(title + " Swipe enabled");
                setSwipeable(true);
                setOnSwipeListener(new OnSwipeListener() {
                    @Override
                    public void onSwipe(Card card) {
                        Toast.makeText(getContext(), "Removed card=" + title, Toast.LENGTH_SHORT).show();
                    }
                });
            }*/

        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            int complete;
            int planned;

            //Retrieve elements
            mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
            mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);

            if (mTitle != null)
                mTitle.setText(title);

            if (mSecondaryTitle != null)
                mSecondaryTitle.setText(secondaryTitle);

            comp = (LinearLayout)parent.findViewById(R.id.progress);
            plan = (LinearLayout)parent.findViewById(R.id.planned);

            LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            complete = ((int)(Math.random()*(5)));
            planned = 5-complete;

            c.weight = complete;
            p.weight = planned;
            comp.setLayoutParams(c);
            plan.setLayoutParams(p);

        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSecondaryTitle() {
            return secondaryTitle;
        }

        public void setSecondaryTitle(String secondaryTitle) {
            this.secondaryTitle = secondaryTitle;
        }
    }


}
