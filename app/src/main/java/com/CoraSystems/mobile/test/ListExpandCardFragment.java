package com.CoraSystems.mobile.test;

/*
 * ******************************************************************************
 *   Copyright (c) 2013-2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */


        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import java.io.IOException;
        import java.util.ArrayList;

        //import it.gmariotti.cardslib.demo.R;
        import com.CoraSystems.mobile.test.CustomExpandCard;
        import com.CoraSystems.mobile.test.Services.SoapWebService;

        import it.gmariotti.cardslib.library.internal.Card;
        import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
        import it.gmariotti.cardslib.library.internal.CardHeader;
        import it.gmariotti.cardslib.library.view.CardListView;

/**
 * List of expandable cards Example
 *
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class ListExpandCardFragment extends BaseFragment {
    String strtext;
    public String ok;
    @Override
    public int getTitleResourceId() {
        return R.string.title_expandablelistcard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         strtext = getArguments().getString("edttext");
        return inflater.inflate(R.layout.fragment_list_expand, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }


    private void initCards() {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0;i<1;i++){
            Card card = init_standard_header_with_expandcollapse_button_custom_area(strtext,i);
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_expand);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }


    /**
     * This method builds a standard header with a custom expand/collpase
     */
    private Card init_standard_header_with_expandcollapse_button_custom_area(String titleHeader,int i) {

        //Create a Card
        Card card = new Card(getActivity());

        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());

        //Set the header title
        header.setTitle(titleHeader);

        //Set visible the expand/collapse button
        header.setButtonExpandVisible(true);

        //Add Header to card
        card.addCardHeader(header);

        //This provides a simple (and useless) expand area
        CustomExpandCard expand = new CustomExpandCard(getActivity(),i);
        //Add Expand Area to Card
        card.addCardExpand(expand);

        //Just an example to expand a card
        if (i==2 || i==7 || i==9)
            card.setExpanded(true);


        //Animator listener
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {
                Intent i = new Intent(getActivity(), uploadAndDownload.class);
                startActivity(i);
               /* SoapWebService soapWebService = new SoapWebService("alan", "password",getActivity());
                try {
                    ok = soapWebService.SendThisData("hello",
                            200000, "pat-pc/ProjectVision/services/projectapi.asmx/GetTaskRequestJSON?SecurityKey=cora&RequestString=pat");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                Toast.makeText(getActivity(),"Expand "+card.getCardHeader().getTitle(),Toast.LENGTH_SHORT).show();

            }
        });

        card.setOnCollapseAnimatorEndListener(new Card.OnCollapseAnimatorEndListener() {
            @Override
            public void onCollapseEnd(Card card) {
                Toast.makeText(getActivity(),"Collpase " +card.getCardHeader().getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

        return card;
    }

}
