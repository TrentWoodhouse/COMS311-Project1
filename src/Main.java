public class Main {

    public static void main(String[] args) {
        IntervalTreap t = new IntervalTreap();
        t.intervalInsert(new Node(new Interval(0, 5)));
        t.intervalInsert(new Node(new Interval(-44, 20)));
        t.intervalInsert(new Node(new Interval(6, 100)));
        t.intervalInsert(new Node(new Interval(77, 666)));
        t.intervalInsert(new Node(new Interval(-3, 255)));
        t.intervalInsert(new Node(new Interval(13, 94)));

        //System.out.println(t.toString());
    }
}
