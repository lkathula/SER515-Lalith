package dp;

public abstract class ItemIterator {
    public abstract boolean hasNext();

    public abstract Object next();

    public abstract void moveToHead();

    public abstract void remove();
}
