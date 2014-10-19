package is.ru.DigitalBabyBook.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.EventExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arnif on 10/18/14.
 */
public class EventListFragment extends Fragment {

    private EventExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.event_list, container, false);
        System.out.println("eventlist");

        // get the listview
        expListView = (ExpandableListView) V.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new EventExpandableListAdapter(V.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.expandGroup(0);
        expListView.expandGroup(1);
        expListView.expandGroup(2);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                    V.getContext(),
                    listDataHeader.get(groupPosition)
                    + " : "
                    + listDataChild.get(
                    listDataHeader.get(groupPosition)).get(
                    childPosition), Toast.LENGTH_SHORT)
                    .show();
                Intent i = null;
                if(listDataHeader.get(groupPosition) == "Holiday") {
                    i = new Intent(v.getContext(), CreateHoliday.class);


                }
                else if(listDataHeader.get(groupPosition) == "First"){
                    i = new Intent(v.getContext(), CreateFirst.class);
                }
                i.putExtra("group", listDataHeader.get(groupPosition).toString());
                i.putExtra("type", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).toString());

                startActivity(i);
                    return false;
            }
        });

        return V;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Holiday");
        listDataHeader.add("First");
        listDataHeader.add("Favorite");

        // Adding child data
        List<String> holidays = new ArrayList<String>();
        holidays.add("Birthday");
        holidays.add("Christmas");
        holidays.add("Easters");
        holidays.add("Vacation");

        List<String> firsts = new ArrayList<String>();
        firsts.add("Smile");
        firsts.add("Laughter");
        firsts.add("Steps");
        firsts.add("Tooth");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Food");
        comingSoon.add("Drink");
        comingSoon.add("Toy");
        comingSoon.add("Color");

        listDataChild.put(listDataHeader.get(0), holidays); // Header, Child data
        listDataChild.put(listDataHeader.get(1), firsts);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
