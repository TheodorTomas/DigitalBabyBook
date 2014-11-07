package is.ru.DigitalBabyBook.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.ChecklistAdapter;
import is.ru.DigitalBabyBook.adapters.EventExpandableListAdapter;
import is.ru.DigitalBabyBook.domain.ChecklistItem;

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
    private List<String> allChecklistItems = new ArrayList<String>();

    private ChecklistAdapter checklistAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.checklist_home_tab, container, false);

        checklistAdapter = new ChecklistAdapter(V.getContext());

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
                System.out.println("update " + childPosition);
                String clickedItem = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                int checkID = getChecklistID(clickedItem);
                boolean done;
                if (clickedItem.toLowerCase().contains("✔".toLowerCase())) {
                    System.out.println("DONE");
                    done = false;
                } else {
                    System.out.println("NOT DONE");
                    done = true;
                }

                if (checkID > -1) {
                    checklistAdapter.updateChecklist(checkID + 1, done);
                    prepareListData();
                    expListView.invalidateViews();
                    listAdapter.notifyDataSetChanged();
                    V.invalidate();
                    Fragment frg = null;
                    frg = getFragmentManager().findFragmentById(R.id.realtabcontent);
                    final android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();

                }

                return false;
            }
        });

        return V;
    }

    private void prepareListData() {
        allChecklistItems = new ArrayList<String>();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("2 Months");
        listDataHeader.add("4 Months");
        listDataHeader.add("6 Months");
        listDataHeader.add("9 Months");
        listDataHeader.add("1 Year");

        // Adding child data
        List<String> twoMonth = createList("2m");
        List<String> fourMonth = createList("4m");
        List<String> sixMonth = createList("6m");
        List<String> nineMonth = createList("9m");
        List<String> oneYear = createList("1yr");


        checklistAdapter.close();

        listDataChild.put(listDataHeader.get(0), twoMonth); // Header, Child data
        listDataChild.put(listDataHeader.get(1), fourMonth);
        listDataChild.put(listDataHeader.get(2), sixMonth);
        listDataChild.put(listDataHeader.get(3), nineMonth);
        listDataChild.put(listDataHeader.get(4), oneYear);
    }

    private int getChecklistID(String description) {
        String clean = cleanUp(description);
        for (int i = 0; i < allChecklistItems.size(); i++) {
            String check = cleanUp(allChecklistItems.get(i));
            if (check.equals(clean)) {
                return i;
            }
        }
        return -1;
    }

    private List<String> createList(String type) {
        Cursor cursor = checklistAdapter.queryChecklistByType(type);
        ChecklistItem c;
        List<String> theList = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                // { "_id  0", "babyID   1", "description   2", "date   3", "type   4", "done   5"  };
                c = new ChecklistItem();
                boolean done = cursor.getInt(5) > 0;
                String toAdd = cursor.getString(2);
                if (done) {
                    //add check
                    toAdd = "✔ " + toAdd;
                } else {
                    toAdd = cleanUp(toAdd);
                }
                c.setDescription(toAdd);
                allChecklistItems.add(c.getDescription());
                theList.add(c.getDescription());

            } while (cursor.moveToNext());
        }

        return theList;
    }

    private String cleanUp(String s) {
        String skil = s;
        if (s.toLowerCase().contains("✔".toLowerCase())) {
            //remove it
            skil = s.replace("✔ ", "");
        }
        return skil;
    }


}
