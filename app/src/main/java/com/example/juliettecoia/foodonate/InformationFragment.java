package com.example.juliettecoia.foodonate;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends android.support.v4.app.Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_information, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.expList);
        elv.setAdapter(new InformationFragmentListAdapater());
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public class InformationFragmentListAdapater extends BaseExpandableListAdapter {
         private String[] groups = {"Mission","Restaurant", "Indiviudal"};

        private String[][] children = {
                {"Walking down the streets of any sized city, one is bound to see hungry, homeless people, begging for help.  Often times we are hesitant to offer our spare change for fear that they won't spend it on food. This application aims to remedy this problem by providing an easier way to connect food banks not only with individuals, but also with restaurants.  Hunger, however, isn't the only problem facing our local communities.  Although preventing the spread of hunger was our initial goal of this app, we realized that this app can also allow inidividuals to donate to other organizations beyond food banks.  Organizations can request whatever they need, such as toiletries for homeless people, volunteers for an upcoming event, or transportation services and then users can donate those goods or services."},
                {"Consistently, restaurants throw away tons of food at the end of each day, or at the least, at the end of the week.  By connecting with local food banks, as doable through this application, restaurants can coordinate to donate their leftover food with food banks near them."},
                {"Individuals are encouraged to take advantage of this app as well, and can do so in multiple ways.  Primarily, they can look at what their nearby food banks are requesting, and make donations more often.  Additionally, individuals can share the app with local restaurants and encourage them to donate their leftover food to local food banks."}
        };

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(InformationFragment.this.getActivity());
            textView.setText(getGroup(i).toString());
            textView.setTextSize(25);
            //textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(Color.BLACK);
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(InformationFragment.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            textView.setTextSize(25);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }
}


