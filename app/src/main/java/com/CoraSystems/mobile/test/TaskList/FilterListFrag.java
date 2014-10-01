package com.CoraSystems.mobile.test.TaskList;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.CoraSystems.mobile.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterListFrag extends Fragment {

    /** FILTER LIST FRAGMENT THAT DROPS DOWN WITH FILTER OPTIONS **/

    View view;

    FilterFragmentAnimationEndListener mListener;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.filter_list, container, false);
        ((TaskList)getActivity()).check(view);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        expListView.setGroupIndicator(null);
        expListView.setChildIndicator(null);
        expListView.setChildDivider(getResources().getDrawable(R.color.white));
        expListView.setDivider(getResources().getDrawable(R.color.text_grey));
        expListView.setDividerHeight(1);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                View divider = view.findViewById(R.id.div);
                divider.setVisibility(View.VISIBLE);
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                View divider = view.findViewById(R.id.div);
                divider.setVisibility(View.GONE);
            }
        });

        return view;
    }

    //Animating drop down options list
    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim)
    {
        int id = enter ? R.animator.slide_fragment_in : R.animator.slide_fragment_out;
        final Animator anim = AnimatorInflater.loadAnimator(getActivity(), id);
        if (enter) {
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mListener.onAnimationEnd();
                }
            });
        }
        return anim;
    }

    public void setOnTextFragmentAnimationEnd(FilterFragmentAnimationEndListener listener)
    {
        mListener = listener;
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("projects");

        // Adding child data
        List<String> projects = new ArrayList<>();
        projects.add("project 1");
        projects.add("project 2");
        projects.add("project 3");
        projects.add("project 4");
        projects.add("project 5");
        projects.add("project 6");
        projects.add("project 7");

        listDataChild.put(listDataHeader.get(0), projects); // Header, Child data
    }

}
