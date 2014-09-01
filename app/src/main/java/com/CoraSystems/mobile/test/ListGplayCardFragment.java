package com.CoraSystems.mobile.test;

/**
 * Created by eoghanmartin on 08/08/2014.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.database.DatabaseReader;
import com.CoraSystems.mobile.test.Timesheet;

import java.sql.Time;
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
    ArrayList<Task> task;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//      strtext = getArguments().getString("edttext");
        View view = inflater.inflate(R.layout.demo_fragment_list_gplaycard, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //initCards();
        FetchTask fetchask = new FetchTask();
        fetchask.execute();

    }
    public class FetchTask extends AsyncTask<Void, Void, Void> {
        LinearLayout linlaHeaderProgress = (LinearLayout) getActivity().findViewById(R.id.linlaHeaderProgress);

        ArrayList<Card> cards;
        ArrayList<Task> task = new ArrayList<Task>();
        Bundle hello;
        @Override
        protected void onPreExecute() {
            linlaHeaderProgress.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                DatabaseReader databaseReader = new DatabaseReader();
                databaseReader.DataSource(getActivity());
                databaseReader.reOpen();
                task = databaseReader.getProjectsTasks();
                cards = new ArrayList<Card>();
                for (int i = 0; i < task.size(); i++) {
                    if (i==0) {
                        Gap card = new Gap(getActivity());
                        card.setShadow(false);

                        cards.add(card);
                    }
                    else if (i!=0) {
                        GooglePlaySmallCard card = new GooglePlaySmallCard(getActivity());
                        String strI = Integer.toString(i-1);
                        card.setTitle(task.get(i).getProject());
                        card.setSecondaryTitle(task.get(i).getTask());
                        card.setComplete(task.get(i).getCompletion());
                        card.setPlanned(task.get(i).getPlanned());

                        card.count = i - 1;

                        card.init();

                        cards.add(card);
                    }
                }
                return null;
            }
            catch(Exception e){}
             /*catch (IOException ) {
                //e.printStackTrace();
}*/
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

            CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_gplaycard);
            linlaHeaderProgress.setVisibility(View.GONE);
            if (listView != null) {

                listView.setAdapter(mCardArrayAdapter);

                // SET THE ADAPTER TO THE LISTVIEW
                //lv.setAdapter(adapter);

                // CHANGE THE LOADINGMORE STATUS TO PERMIT FETCHING MORE DATA
                //tloadingMore = false;

                // HIDE THE SPINNER AFTER LOADING FEEDS

            }

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
        protected String completePer;
        protected String plannedPer;

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

            //complete = ((int)(Math.random()*(5)));
            //planned = 5-complete;

            c.weight = Integer.valueOf(completePer);
            p.weight = Integer.valueOf(plannedPer);
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
        public void setComplete(String complete) {
            this.completePer = complete;
        }
        public void setPlanned(String planned) {
            this.plannedPer = planned;
        }
    }


    public class Gap extends Card {

        public Gap(Context context) {
            this(context, R.layout.gap);
        }

        public Gap(Context context, int innerLayout) {
            super(context, innerLayout);
        }
    }

}
