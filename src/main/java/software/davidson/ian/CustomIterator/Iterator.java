package software.davidson.ian.CustomIterator;

public  interface Iterator<E> {
    boolean hasNext(); // idempotent
    E next();
}
