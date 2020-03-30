import java.util.List;

public class IntervalTreap {
    private Node root;
    private int size;
    private int height;

    public IntervalTreap() {
        this.size = 0;
        this.height = 0;
    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    //TODO
    void intervalInsert(Node z) {
        return;
    }

    //TODO
    void intervalDelete(Node z) {
        return;
    }

    //TODO
    Node intervalSearch(Interval i) {
        return null;
    }

    //TODO (Extra credit)
    Node intervalSearchExactly(Interval i) {
        return null;
    }

    //TODO (Extra credit)
    List<Interval> overlappingIntervals(Interval i) {
        return null;
    }
}

