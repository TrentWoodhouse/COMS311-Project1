import java.util.List;
import java.util.Random;

public class IntervalTreap {
    private Node root;
    private int size;

    public IntervalTreap() {
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        if (root == null) {
            return -1;
        }
        else {
            return root.getHeight();
        }
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void intervalInsert(Node z) {

        //Initialize
        Random rand = new Random();
        z.setPriority(rand.nextInt());
        z.setHeight(0);
        size++;

        //Set root node if null
        if (root == null) {
            root = z;
            updateIMaxAndHeight(z);
            System.out.println(toString());
            return;
        }

        //BST insert downwards
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (z.getInterv().getLow() >= y.getInterv().getLow()) {
                x = x.getRight();
            }
            else {
                x = x.getLeft();
            }
        }
        z.setParent(y);
        if (z.getInterv().getLow() >= y.getInterv().getLow()) {
            y.setRight(z);
        }
        else {
            y.setLeft(z);
        }
        updateIMaxAndHeight(z);

        //Rotate upwards
        while (needsRotating(z)) {
            rotateWithParent(z);
        }

        System.out.println(toString());
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
        while (x != null && !overlaps(x.getInterv(), i)) {
            if (x.getLeft() != null && x.getLeft().getIMax() >= i.getLow()) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return x;

    }

    private boolean overlaps(Interval a, Interval b) {
        if (a.getLow() <= b.getHigh() && b.getLow() <= a.getHigh()) {
            return true;
        }
        return false;
    }

    //TODO
    public Node intervalSearchExactly(Interval i){
        Node x = root;
        while (x != null && !OverlapsExactly(x.getInterv(), i)) {
            if (x.getLeft() != null && x.getLeft().getIMax() >= i.getLow()) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return x;

    }

    private boolean OverlapsExactly(Interval a, Interval b) {
        if (a.getLow() == b.getHigh() && b.getLow() == a.getHigh()) {
            return true;
        }
        return false;
    }

    //TODO (Extra credit)
    public List<Interval> overlappingIntervals(Interval i) {
        return null;
    }

    private void rotateWithParent(Node z) {
        if (z.getParent() == null) {
            return;
        }
        Node grandparent = z.getParent().getParent();
        Node parent = z.getParent();
        Node subtreeA = null;
        Node subtreeB = null;
        Node subtreeC = null;

        boolean rotateLeft= parent.getRight() == z;
        //else, rotate right

        if (rotateLeft) {
            subtreeA = parent.getLeft();
            subtreeB = z.getLeft();
            subtreeC = z.getRight();

            z.setLeft(parent);
            parent.setRight(subtreeB);
        }
        else {
            subtreeA = z.getLeft();
            subtreeB = z.getRight();
            subtreeC = parent.getRight();

            z.setRight(parent);
            parent.setLeft(subtreeB);
        }

        if (grandparent == null) {
            root = z;
        }
        else if (grandparent.getRight() == parent) {
            grandparent.setRight(z);
        }
        else {
            grandparent.setLeft(z);
        }

        z.setParent(grandparent);
        parent.setParent(z);
        if (subtreeB != null) {
            subtreeB.setParent(parent);
        }

        updateIMaxAndHeight(parent);
    }

    private boolean needsRotating(Node z) {
        return z.getParent() != null && z.getPriority() < z.getParent().getPriority();
    }

    private void updateIMaxAndHeight(Node z) {
        if (z == null) {
            return;
        }
        if (z.getLeft() == null && z.getRight() == null) {
            z.setIMax(z.getInterv().getHigh());
            z.setHeight(0);
            updateIMaxAndHeight(z.getParent());
            return;
        }
        if (z.getLeft() == null) {
            z.setIMax(Math.max(z.getInterv().getHigh(), z.getRight().getIMax()));
            z.setHeight(z.getRight().getHeight() + 1);
            updateIMaxAndHeight(z.getParent());
            return;
        }
        if (z.getRight() == null) {
            z.setIMax(Math.max(z.getInterv().getHigh(), z.getLeft().getIMax()));
            z.setHeight(z.getLeft().getHeight() + 1);
            updateIMaxAndHeight(z.getParent());
            return;
        }
        z.setIMax(Math.max(z.getInterv().getHigh(), Math.max(z.getLeft().getIMax(), z.getRight().getIMax())));
        z.setHeight(Math.max(z.getRight().getHeight(), z.getLeft().getHeight()) + 1);
        updateIMaxAndHeight(z.getParent());
    }

    public String toString() {
        return "Size: " + getSize() + "\nHeight: " + getHeight() + "\n" + toStringRec(root, 0);
    }

    private String toStringRec(Node n, int level) {
        String ret = "";
        for (int i = 0; i < level; i++) {
            ret += "\t";
        }
        if (n == null) {
            return ret + "<null>\n";
        }

        ret += n.toString() + "\n" + toStringRec(n.getLeft(), level + 1) + toStringRec(n.getRight(), level + 1);
        return ret;
    }
}

