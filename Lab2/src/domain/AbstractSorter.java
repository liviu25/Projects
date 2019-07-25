package domain;

public abstract class AbstractSorter {
    protected int v[];

    public AbstractSorter(int[] v) {
        this.v = v;
    }
    public abstract void sort();
}
