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

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void intervalInsert(Node z) {
        return;
    }

    //TODO
    public void intervalDelete(Node z) {
        return;
    }

    //TODO
    public Node intervalSearch(Interval i) {
        return null;
    }

    //TODO (Extra credit)
    public Node intervalSearchExactly(Interval i) {
        return null;
    }

    //TODO (Extra credit)
    public List<Interval> overlappingIntervals(Interval i) {
        return null;
    }
}

