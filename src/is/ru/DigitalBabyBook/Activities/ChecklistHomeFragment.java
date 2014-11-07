package is.ru.DigitalBabyBook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.EventExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arnif on 10/18/14.
 */
public class ChecklistHomeFragment extends Fragment {

    private EventExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.checklist_home_tab, container, false);

        // get the listview
        expListView = (ExpandableListView) V.findViewById(R.id.chExp);

        // preparing list data
        prepareListData();

        listAdapter = new EventExpandableListAdapter(V.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        return V;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("2 Months");
        listDataHeader.add("4 Months");
        listDataHeader.add("6 Months");
        listDataHeader.add("9 Months");
        listDataHeader.add("1 Year");

        // Adding child data
        List<String> twoMonth = new ArrayList<String>();
        twoMonth.add("Pays attention to faces");
        twoMonth.add("Begins to follow things with eyes and recognize people at a distance");
        twoMonth.add("Begins to act bored (cries, fussy) if activity doesn’t change");
        twoMonth.add("Can hold head up and begins to push up when lying on tummy");
        twoMonth.add("Makes smoother movements with arms and legs");

        List<String> fourMonth = new ArrayList<String>();

        fourMonth.add("Lets you know if she is happy or sad");
        fourMonth.add("Responds to affection");
        fourMonth.add("Reaches for toy with one hand");
        fourMonth.add("Uses hands and eyes together, such as seeing a toy and reaching for it");
        fourMonth.add("Follows moving things with eyes from side to side");
        fourMonth.add("Watches faces closely");
        fourMonth.add("Recognizes familiar people and things at a distance");
        fourMonth.add("Holds head steady, unsupported");
        fourMonth.add("Pushes down on legs when feet are on a hard surface");
        fourMonth.add("May be able to roll over from tummy to back");
        fourMonth.add("Can hold a toy and shake it and swing at dangling toys");
        fourMonth.add("Brings hands to mouth");
        fourMonth.add("When lying on stomach, pushes up to elbows");

        List<String> sixMonth = new ArrayList<String>();

        sixMonth.add("Looks around at things nearby");
        sixMonth.add("Brings things to mouth");
        sixMonth.add("Brings things to mouth");
        sixMonth.add("Shows curiosity about things and tries to get things that are out of reach");
        sixMonth.add("Begins to pass things from one hand to the other");
        sixMonth.add("Rolls over in both directions (front to back, back to front)");
        sixMonth.add("Begins to sit without support");
        sixMonth.add("When standing, supports weight on legs and might bounce");
        sixMonth.add("Rocks back and forth, sometimes crawling backward before moving forward");

        List<String> nineMonth = new ArrayList<String>();

        nineMonth.add("Watches the path of something as it falls");
        nineMonth.add("Looks for things he sees you hide");
        nineMonth.add("Plays peek-a-boo");
        nineMonth.add("Puts things in her mouth");
        nineMonth.add("Moves things smoothly from one hand to the other");
        nineMonth.add("Picks up things like cereal o’s between thumb and index finger");
        nineMonth.add("Stands, holding on");
        nineMonth.add("Can get into sitting position");
        nineMonth.add("Sits without support");
        nineMonth.add("Pulls to stand");
        nineMonth.add("Crawls");

        List<String> oneYear = new ArrayList<String>();

        oneYear.add("Explores things in different ways, like shaking, banging, throwing");
        oneYear.add("Finds hidden things easily");
        oneYear.add("Looks at the right picture or thing when it’s named");
        oneYear.add("Copies gestures");
        oneYear.add("Starts to use things correctly, for example, drinks from a cup, brushes hair");
        oneYear.add("Bangs two things together");
        oneYear.add("Puts things in a container, takes things out of a container");
        oneYear.add("Lets things go without help");
        oneYear.add("Pokes with index (pointer) finger");
        oneYear.add("Follows simple directions like “pick up the toy”");
        oneYear.add("Gets to a sitting position without help");
        oneYear.add("Pulls up to stand, walks holding on to furniture (“cruising”)");
        oneYear.add("May take a few steps without holding on");
        oneYear.add("May stand alone");

        listDataChild.put(listDataHeader.get(0), twoMonth); // Header, Child data
        listDataChild.put(listDataHeader.get(1), fourMonth);
        listDataChild.put(listDataHeader.get(2), sixMonth);
        listDataChild.put(listDataHeader.get(3), nineMonth);
        listDataChild.put(listDataHeader.get(4), oneYear);
    }
}
