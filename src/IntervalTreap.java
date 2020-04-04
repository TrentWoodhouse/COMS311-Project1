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
    public void intervalDelete(Node z){
        if (z.getLeft() == null) {
            z = z.getRight();
        }
        else if (z.getLeft() != null && z.getRight() == null) {
            z = z.getLeft();
        }
        else {
            //replace z with its successor, not sure how to find this rn
            //but the doc says something like y = Minimum(z.right) on p. 4
        }
        //TODO after this we have to rotate accordingly

    }

    //TODO
    public Node intervalSearch(Interval i){
        Node x = root;
        while (x != null) { //need to figure out the "i does not overlap x.interval" condition
            if (x.getLeft() != null && x.getLeft().getIMax() >= i.getLow()) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return x;

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

