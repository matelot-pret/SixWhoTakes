/**
 * @author Dony Isabelle
 */
import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * We define a board with several rows according to the specification of RowInterface
 * @param <T>
 */

public interface BoardInterface <T extends Comparable<T>>{

	/**
	 * Selects the smallest element in the list 
	 * and attempts to place it on the board according to the game rules.
	 * Among the rows in which the element can be placed, the row with
	 * the smallest distance between its last element and the selected
	 * element is chosen.
	 * 
	 * The method checks the preconditions in the following order: 
	 * 1- list must not be null; 
	 * 2- the list must not be empty; 
	 * 3- the board must not contain any empty row. 
	 * 4- the method attempt to place the smallest element.
	 * @param list the list whose smallest element is to be placed
	 *        on the board; 
	 *        must not be null or empty
	 * @return an array containing the items that the player must collect
	 * @throws NullPointerException if list is null
	 * @throws EmptyException if the board contains an empty row or if the list is empty
	 * @throws NotIncreasingException if the smallest element in the list
	 *         cannot be placed in any row of the board
	 */
	T[] setMinimum(LinkedList<T> list)throws NotIncreasingException;
	//démarche: 
	//trie les rangées avec la méthode static et essaye de placer l'element sur la plus petite rangée et 
	//poursuit sur les rangées suivantes si ça ne marche pas 

	/**
	 * Creates and returns a new ArrayList containing the elements 
	 * of the specified list, sorted according to the given comparator.
	 * the original list is not modified
	 * @param <E> the type of elements in the list
	 * @param a the list whose elements are to be sorted
	 * @param c the comparator used to determine the order of the elements
	 * @return a new ArrayList containing a permutation of the same elements as the input
	 * @throws NullPointerException if a or c is null
	 */
	static <E> ArrayList<E> sort(ArrayList<E> a, Comparator<E> c){
		//à compléter
		return null;
	}
	
	
	/**
	 * Returns all rows whose sum is equal to the minimum sum
	 * among the rows on the board. 
	 * @return a list containing all rows with the minimum sum
	 */

	List<Row<T>> getMinimal();
	//utilise la méthode Collections.min

	/**
	 * Adds the first item to an empty row.
	 * @param i the index of the row to initialize, the index must be valid
	 * @param x the item to add to the row
	 * @throws IndexOutOfBoundsException if i is outside the valid range
	 * @throws NotAvailableException if the specified row is not empty
	 * @throws NullPointerException if x is null
	 */
	void startRow(int i, T x) throws NotAvailableException;


}
