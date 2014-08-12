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
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

        import it.gmariotti.cardslib.library.internal.Card;
        import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
        import it.gmariotti.cardslib.library.internal.CardThumbnail;
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
/*
        LinearLayout ll = (LinearLayout)view.findViewById(R.id.progress);
        TextView newText = new TextView(this.getActivity());
        newText.setText("Important text");
        ll.addView(newText);*/
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }


    private void initCards() {

        int complete;
        int planned;
        comp = (LinearLayout)getView().findViewById(R.id.progress);
       // plan = (LinearLayout)getView().findViewById(R.id.planned);

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0;i<6;i++){
            GooglePlaySmallCard card = new GooglePlaySmallCard(this.getActivity());
            card.setTitle("Project "+i);
            card.setSecondaryTitle("Task" + i);

            //complete = ((int)(Math.random()*(5)));
            //planned = 5-complete;

            //c.weight = complete;
            //p.weight = planned;
            //comp.setLayoutParams(c);
            //plan.setLayoutParams(p);
            //comp.addView(plan);

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
        //protected RatingBar mRatingBar;
        protected int count;

        protected String title;
        protected String secondaryTitle;
        protected float rating;


        public GooglePlaySmallCard(Context context) {
            this(context, R.layout.carddemo_mycard_inner_content);
        }

        public GooglePlaySmallCard(Context context, int innerLayout) {
            super(context, innerLayout);
            //init();
        }

        private void init() {

            //Only for test, some cards have different clickListeners
            if (count==12){

                setTitle(title + " No Click");
                setClickable(false);

            }else if (count==20){

                setTitle(title + " Partial Click");
                addPartialOnClickListener(Card.CLICK_LISTENER_CONTENT_VIEW,new OnCardClickListener() {
                    @Override
                    public void onClick(Card card, View view) {
                        Toast.makeText(getContext(), "Partial click Listener card=" + title, Toast.LENGTH_SHORT).show();
                    }
                });

            }else{

                //Add ClickListener
                setOnClickListener(new OnCardClickListener() {
                    @Override
                    public void onClick(Card card, View view) {
                        Intent i = new Intent(getActivity(), uploadAndDownload.class);
                        startActivity(i);
                        Toast.makeText(getContext(), "Click Listener card=" + title, Toast.LENGTH_SHORT).show();
                    }
                });

            }


            //Swipe
            if (count==10){

                setTitle(title + " Swipe enabled");
                setSwipeable(true);
                setOnSwipeListener(new OnSwipeListener() {
                    @Override
                    public void onSwipe(Card card) {
                        Toast.makeText(getContext(), "Removed card=" + title, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            //Retrieve elements
            mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
            mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
            //mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);

            if (mTitle != null)
                mTitle.setText(title);

            if (mSecondaryTitle != null)
                mSecondaryTitle.setText(secondaryTitle);

            /*if (mRatingBar != null) {
                mRatingBar.setNumStars(5);
                mRatingBar.setMax(5);
                mRatingBar.setStepSize(0.5f);
                mRatingBar.setRating(rating);
            }*/

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

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }
    }


}
