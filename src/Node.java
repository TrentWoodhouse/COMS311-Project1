public class Node {
    private Node parent;
    private Node left;
    private Node right;
    private Interval interv;
    private int getIMax;
    private int priority;

    public Node(Interval i) {
        this.interv = i;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Interval getInterv() {
        return interv;
    }

    public int getGetIMax() {
        return getIMax;
    }

    public int getPriority() {
        return priority;
    }
}
