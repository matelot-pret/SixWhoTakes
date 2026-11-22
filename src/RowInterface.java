/**
 * @author Dony Isabelle 
 */
import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;

import java.util.function.ToIntFunction;

/**
 * We define a row as a sequence of items 
 * maintained in strictly increasing natural order.
 * @param <T>
 */
public interface RowInterface<T extends Comparable<T>> {

	/**
	 * adds an item at the end of the row preserving the natural ordering of its elements
	 * @param item the item to add, the item must not be null
	 * @throws NotIncreasingException if the item is note greater than the last item of the row
	 * @throws NullPointerException if the item is null
	 */
	void addItem(T item) throws NotIncreasingException;

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
	T[] remove(int nb) throws NotAvailableException;

	/**
	 * removes and returns the first item of the row
	 * @return the removed item 
	 * @throws EmptyException if the row is empty
	 */
	T remove() throws EmptyException;

	/**
	 * Returns the number of items currently in the row.
	 * @return the number of items in the row
	 */
	int nbItems();

	/**
	 * gets the last item of the row (without removing)
	 * @return the last item of the row
	 * @throws EmptyException if the row is empty
	 */
	T getLastItem() throws EmptyException;

	/**
	 * @param mapper the function used to convert each item to an int, 
	 * must not be null
	 * @return the sum of the row according mapper 
	 * @throws NullPointerException if mapper is null
	 */
	int sumToInt(ToIntFunction<? super T> mapper);
}
