import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Iterator;

public class Question2  {
    public static void main(String[] args) {
            ArrayList<String> vent = new ArrayList<String>();
            vent.add("’ , , << - >>.");
            vent.add(" , , ’ , ’ , ’ .");
            vent.add(" , , .");
            Iterator<String> it = vent.iterator();
            while (it.hasNext()) {
            // Question 2 part 1
              /*  String elem = it.next();
                System.out.println(elem);
                System.out.println(elem);*/
            // Question 2 part 2
                System.out.println(it.next());
                if(it.hasNext()){
                    it.next();
                }
            }

    }
}
