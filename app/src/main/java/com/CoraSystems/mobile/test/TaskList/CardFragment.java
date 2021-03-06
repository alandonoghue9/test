package com.CoraSystems.mobile.test.TaskList;

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

import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.R;
import com.CoraSystems.mobile.test.Timesheet.Timesheet;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class CardFragment extends Fragment {

    LinearLayout comp;
    LinearLayout plan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cards_list, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fillCards();
    }

    public void fillCards(){
        ArrayList<Card> cards = new ArrayList<>();
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        int j=0;
        if(TaskGlobal.task != null){
          for (int i = 0; i  <  (TaskGlobal.task.size()+1); i++) {
            if (i == 0) {
                Gap card = new Gap(getActivity());
               card.setShadow(false);

                cards.add(card);
            } else {
                GooglePlaySmallCard card = new GooglePlaySmallCard(getActivity());
                card.setTitle(TaskGlobal.task.get(j).getTask());
                card.setSecondaryTitle(TaskGlobal.task.get(j).getProject()+" ("+(TaskGlobal.task.get(j).getProjectId())+")");
                card.setComplete(Double.toString(TaskGlobal.task.get(j).getCompletion()));
                card.setPlanned(Double.toString(TaskGlobal.task.get(j).getPlanned()));

                card.count = i - 1;
                card.init();
                cards.add(card);
                j++;
            }
            CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_gplaycard);
            if (listView != null) {
                listView.setAdapter(mCardArrayAdapter);
            }
          }
        }
    }

    public class GooglePlaySmallCard extends Card {

        protected int count;

        protected TextView mTitle;
        protected TextView mSecondaryTitle;
        protected String title;
        protected String secondaryTitle;
        protected String completePer;
        protected String plannedPer;

        public GooglePlaySmallCard(Context context) {
            this(context, R.layout.card);
        }

        public GooglePlaySmallCard(Context context, int innerLayout) {
            super(context, innerLayout);
        }
        private void init() {

            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    //start timesheet activity and bundle required variables
                    Intent i = new Intent(getActivity(), Timesheet.class);

                    i.putExtra("project", TaskGlobal.task.get(count).getProject());
                    i.putExtra("projectID", TaskGlobal.task.get(count).getProjectId());
                    i.putExtra("task desc", TaskGlobal.task.get(count).getTask());
                    i.putExtra("task", TaskGlobal.task.get(count).getTaskId());
                    i.putExtra("complete", TaskGlobal.task.get(count).getCompletion());
                    i.putExtra("planned", TaskGlobal.task.get(count).getPlanned());
                    startActivity(i);
                    }
            });
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            double complete;
            double planned;

            //Retrieve elements
            mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
            mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);

            if (mTitle != null)
                mTitle.setText(title);

            if (mSecondaryTitle != null)
                mSecondaryTitle.setText(secondaryTitle);

            comp = (LinearLayout)parent.findViewById(R.id.complete);
            plan = (LinearLayout)parent.findViewById(R.id.planned);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            planned = Double.parseDouble(plannedPer);
            complete = Double.parseDouble(completePer);
            complete = (complete/planned)*100;

            if(complete<100) {
                c.weight = (float) complete;
                p.weight = (float) (100 - complete);
            }
            else if(complete>100){
                comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_red));
                c.weight = 100;
                p.weight = 0;
            }
            comp.setLayoutParams(c);
            plan.setLayoutParams(p);
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
            this(context, R.layout.gap_tasklist);
        }

        public Gap(Context context, int innerLayout) {
            super(context, innerLayout);
        }
    }
}
