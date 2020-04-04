public class Node {
    private Node parent;
    private Node left;
    private Node right;
    private Interval interv;
    private int imax;
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

    public int getIMax() {
        return imax;
    }

    public int getPriority() {
        return priority;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setInterv(Interval interv) {
        this.interv = interv;
    }

    public void setIMax(int imax) {
        this.imax = imax;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return "<Interv: " + this.interv.toString() + ", Imax: " + this.imax + ", Priority: " + this.priority + ">";
    }
}
