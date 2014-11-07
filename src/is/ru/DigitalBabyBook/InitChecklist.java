package is.ru.DigitalBabyBook;

import android.content.Context;
import is.ru.DigitalBabyBook.adapters.ChecklistAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 11/7/14.
 */
public class InitChecklist {

    private List<String> twoMonth;
    private List<String> fourMonth;
    private List<String> sixMonth;
    private List<String> nineMonth;
    private List<String> oneYear;

    private Context context;

    public InitChecklist(Context c) {
        context = c;
        twoMonth = new ArrayList<String>();
        fourMonth = new ArrayList<String>();
        sixMonth = new ArrayList<String>();
        nineMonth = new ArrayList<String>();
        oneYear = new ArrayList<String>();
    }

    public void init(long babyId) {
        ChecklistAdapter checklistAdapter = new ChecklistAdapter(context);

        twoMonth.add("Pays attention to faces");
        twoMonth.add("Begins to follow things with eyes and recognize people at a distance");
        twoMonth.add("Begins to act bored (cries, fussy) if activity doesn’t change");
        twoMonth.add("Can hold head up and begins to push up when lying on tummy");
        twoMonth.add("Makes smoother movements with arms and legs");

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

        sixMonth.add("Looks around at things nearby");
        sixMonth.add("Brings things to mouth");
        sixMonth.add("Shows curiosity about things and tries to get things that are out of reach");
        sixMonth.add("Begins to pass things from one hand to the other");
        sixMonth.add("Rolls over in both directions (front to back, back to front)");
        sixMonth.add("Begins to sit without support");
        sixMonth.add("When standing, supports weight on legs and might bounce");
        sixMonth.add("Rocks back and forth, sometimes crawling backward before moving forward");

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

        insertToDb(babyId, checklistAdapter);
    }

    private void insertToDb(long babyId, ChecklistAdapter checklistAdapter) {
        for (String s : twoMonth) {
            checklistAdapter.insertChecklist(babyId, s, "2m");
        }
        for (String s : fourMonth) {
            checklistAdapter.insertChecklist(babyId, s, "4m");
        }
        for (String s : sixMonth) {
            checklistAdapter.insertChecklist(babyId, s, "6m");
        }
        for (String s : nineMonth) {
            checklistAdapter.insertChecklist(babyId, s, "9m");
        }
        for (String s : oneYear) {
            checklistAdapter.insertChecklist(babyId, s, "1yr");
        }
    }


}
