import java.util.List;
import java.util.Random;

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

        //BST insert downwards
        Random rand = new Random();
        z.setPriority(rand.nextInt());
        if (this.root == null) {
            this.root = z;
            return;
        }
        Node y = null;
        Node x = this.root;
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

        //Rotate upwards
        while (needsRotating(z)) {
            rotateWithParent(z);
        }
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
            this.root = z;
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
    }

    private boolean needsRotating(Node z) {
        return z.getParent() != null && z.getPriority() < z.getParent().getPriority();
    }

    public String toString() {
        return toStringRec(this.root, 0);
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

