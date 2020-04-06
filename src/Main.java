/**
 * @author Trent Woodhouse
 * @author Vismay Gehlot
 */
public class Main {

    public static void main(String[] args) {
        IntervalTreap t = new IntervalTreap();
        Node n = new Node(new Interval(0, 34));
        t.intervalInsert(n);

        t.intervalInsert(new Node(new Interval(1, 5)));
        t.intervalInsert(new Node(new Interval(-44, 20)));
        t.intervalInsert(new Node(new Interval(6, 100)));
        t.intervalInsert(new Node(new Interval(77, 666)));
        t.intervalInsert(new Node(new Interval(-3, 255)));
        t.intervalInsert(new Node(new Interval(13, 94)));
        t.intervalInsert(new Node(new Interval(73, 237)));
        t.intervalInsert(new Node(new Interval(1, 33)));
        t.intervalInsert(new Node(new Interval(278, 555)));
        t.intervalInsert(new Node(new Interval(452, 1000)));



        System.out.println(t.toString());

        t.intervalDelete(n);

//        t.checkForSelfReference();

        System.out.println(t.toString());
    }
}
