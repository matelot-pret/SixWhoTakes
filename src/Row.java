import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;

import java.util.function.ToIntFunction;

public class Row<T extends Comparable<T>> implements RowInterface<T> {

    private Cell head; // premier élément de la ligne
    private Cell queue;// dernier élément de la ligne
    private int nbItems;// nombre d'éléments dans la ligne. nbItems >= 0. Si nbItems == 0 : head == null ET queue == null Si nbItems > 0 : head != null, si nbItems > 1 : head != null ET head != queue, si nbItems == 1 : head == queue

    public Row(){
        head = null;
        queue = null;
        nbItems = 0;
    }
    /**
     * adds an item at the end of the row preserving the natural ordering of its elements
     * @param item the item to add, the item must not be null
     * @throws NotIncreasingException if the item is not greater than the last item of the row
     * @throws NullPointerException if the item is null
     */
    @Override
    public void addItem(T item) throws NotIncreasingException{
        if(item == null){//garantir le respect de la promesse de la javadoc
            throw new NullPointerException();
        }
        if(nbItems > 0) {
            if (item.compareTo(queue.data) <= 0) {
                throw new NotIncreasingException();
            }
            Cell cell = new Cell(item);
            queue.next = cell;
            queue = queue.next;
        }else{
            Cell cell = new Cell(item);
            queue = cell;
            head = cell;
        }

        nbItems ++;

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
        if(nb < 0){
            throw new IllegalArgumentException();
        }
        if(nb > nbItems){
            throw new NotAvailableException();
        }
        T[] items = (T[]) new Comparable[nb];

        for (int i = 0; i < nb; i++) {
            items[i] = this.remove();
        }

        return items;
    }

    /**
     * removes and returns the first item of the row
     * @return the removed item
     * @throws EmptyException if the row is empty
     */
    @Override
    public T remove() throws EmptyException{
        if(nbItems == 0){
            throw new EmptyException();
        }
        T item = head.data;
        head = head.next;


        nbItems --;
        if(nbItems < 1){
            queue = null;
        }
        return item;
    }

    /**
     * Returns the number of items currently in the row.
     * @return the number of items in the row
     */
    @Override
    public int nbItems(){
        return nbItems;
    }

    /**
     * gets the last item of the row (without removing)
     * @return the last item of the row
     * @throws EmptyException if the row is empty
     */
    @Override
    public T getLastItem() throws EmptyException{
        if(nbItems == 0){
            throw new EmptyException();
        }

        return queue.data;
    }

    /**
     * @param mapper the function used to convert each item to an int,
     * must not be null
     * @return the sum of the row according mapper
     * @throws NullPointerException if mapper is null
     */
    @Override
    public int sumToInt(ToIntFunction<? super T> mapper){
        if(mapper == null){
            throw new NullPointerException();//exception lancée dans tous les cas.
        }
        int sum = 0;

        Cell current = head;
        while(current != null) {
            sum += mapper.applyAsInt(current.data);
            current = current.next;
        }

        return sum;
    }

    public class Cell{
        private T data;
        private Cell next;

        public Cell(T data){
            this.data = data;
        }
    }
}
