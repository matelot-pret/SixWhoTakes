import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;

import java.util.function.ToIntFunction;

public class Row<T extends Comparable<T>> implements RowInterface<T> {

    private Cell head;
    private int nbItems;
    private final static int NB_ITEMS_MAX = 5;

    /**
     * adds an item at the end of the row preserving the natural ordering of its elements
     * @param item the item to add, the item must not be null
     * @throws NotIncreasingException if the item is note greater than the last item of the row
     * @throws NullPointerException if the item is null
     */
    @Override
    public void addItem(T item) throws NotIncreasingException{

    }

    /**
     * Removes the first nb items from the row and returns them
     * in a new array, preserving their original order
     * If not enough items to remove, the row is not modified.
     * @param nb the number of items to remove, the number must be non-negative
     * @return an array completely initialized with the first nb items
     * removed
     * @throws NotAvailableException if nb is strictly greater than
     *         the number of items in the row
     * @throws IllegalArgumentException if nb is negative
     */
    @Override
    public T[] remove(int nb) throws NotAvailableException{
        return null;
    }

    /**
     * removes and returns the first item of the row
     * @return the removed item
     * @throws EmptyException if the row is empty
     */
    @Override
    public T remove() throws EmptyException{
        return null;
    }

    /**
     * Returns the number of items currently in the row.
     * @return the number of items in the row
     */
    @Override
    public int nbItems(){
        return 0;
    }

    /**
     * gets the last item of the row (without removing)
     * @return the last item of the row
     * @throws EmptyException if the row is empty
     */
    @Override
    public T getLastItem() throws EmptyException{
        return null;
    }

    /**
     * @param mapper the function used to convert each item to an int,
     * must not be null
     * @return the sum of the row according mapper
     * @throws NullPointerException if mapper is null
     */
    @Override
    public int sumToInt(ToIntFunction<? super T> mapper){
        return 0;
    }

    public class Cell{
        private T x;
        private Cell next;
    }
}
