import java.util.List;
import java.util.Random;

/**
 * @author Trent Woodhouse
 * @author Vismay Gehlot
 */
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
        while (needsRotatingUp(z)) {
            rotateWithParent(z);
        }
    }

    public void intervalDelete(Node z){
        size--;
        Node parent = z.getParent();
        Node right = z.getRight();
        Node left = z.getLeft();
        if (left == null) { //Replace z with right child
            if (parent == null) {
                root = right;
            }
            else {
                if (parent.getRight() == z) {
                    parent.setRight(right);
                }
                else {
                    parent.setLeft(right);
                }
            }
            if (right != null) {
                right.setParent(parent);
            }
        }
        else if (right == null) { //replace z with left child
            if (parent == null) {
                root = left;
            }
            else {
                if (parent.getRight() == z) {
                    parent.setRight(left);
                }
                else {
                    parent.setLeft(left);
                }
            }
            left.setParent(parent);

        }
        else { //swap z with successor and remove z
            Node y = minimum(right);

            //Stitch nodes around y together
            if (y.getParent() != z) {
                //Assume y is left child because of minimum
                y.getParent().setLeft(y.getRight());
                if (y.getRight() != null) {
                    y.getRight().setParent(y.getParent());
                    updateIMaxAndHeight(y.getRight());
                }
                else {
                    updateIMaxAndHeight(y.getParent());
                }
            }

            //Inject y into where z was
            if (parent == null) {
                root = y;
            }
            else {
                if (parent.getRight() == z) {
                    parent.setRight(y);
                }
                else {
                    parent.setLeft(y);
                }
            }
            y.setParent(parent);

            //Check if minimum is not right child of z
            if (y != right) {
                y.setRight(right);
                right.setParent(y);
            }
            y.setLeft(left);
            left.setParent(y);

            updateIMaxAndHeight(y);

            //Rotate y downwards to keep priority rule
            while (needsRotatingDown(y)) {
                if (y.getLeft() == null) {
                    rotateWithParent(y.getRight());
                }
                else if (y.getRight() == null) {
                    rotateWithParent(y.getLeft());
                }
                else {
                    if (y.getLeft().getPriority() < y.getRight().getPriority()) {
                        rotateWithParent(y.getLeft());
                    }
                    else {
                        rotateWithParent(y.getRight());
                    }
                }
            }
        }
    }

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
        return a.getLow() <= b.getHigh() && b.getLow() <= a.getHigh();
    }

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
        if (a.getLow() == b.getLow() && b.getHigh() == a.getHigh()) {
            return true;
        }
        return false;
    }

    //TODO (Extra credit)
    public List<Interval> overlappingIntervals(Interval i) {
        return null;
    }

    private Node minimum(Node z) {
        Node y = null;
        while (z != null) {
            y = z;
            z = z.getLeft();
        }

        return y;
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

    private boolean needsRotatingUp(Node z) {
        return z.getParent() != null && z.getPriority() < z.getParent().getPriority();
    }

    private boolean needsRotatingDown(Node z) {
        return (z.getRight() != null && z.getPriority() > z.getRight().getPriority())
                || (z.getLeft() != null && z.getPriority() > z.getLeft().getPriority());
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

    public void checkForSelfReference() {
        Node ret = checkForSelfReferenceRec(root);
        if (ret != null) {
            System.out.println("Self reference at: " + ret.toString());
        }
        else {
            System.out.println("No self references detected");
        }
    }

    private Node checkForSelfReferenceRec(Node n) {
        if (n == null) {
            return null;
        }
        if (n.getLeft() == n || n.getRight() == n || n.getParent() == n) {
            return n;
        }
        Node ret = checkForSelfReferenceRec(n.getRight());
        if (ret != null) {
            return ret;
        }
        return checkForSelfReferenceRec(n.getLeft());
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

