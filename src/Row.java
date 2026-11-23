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
        }//O(1)
        if(nbItems > 0) {
            if (item.compareTo(queue.data) <= 0) {
                throw new NotIncreasingException();
            }//O(1)
            Cell cell = new Cell(item);//O(1)
            queue.next = cell;//O(1)
            queue = queue.next;//O(1)
        }else{
            Cell cell = new Cell(item);//O(1)
            queue = cell;//O(1)
            head = cell;//O(1)
        }//O(1)

        nbItems ++;//O(1)
    }//O(1)

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
        }//O(1)
        if(nb > nbItems){
            throw new NotAvailableException();
        }//O(1)
        T[] items = (T[]) new Comparable[nb];//O(1)

        for (int i = 0; i < nb; i++) {
            items[i] = this.remove();
        }//O(n)

        return items;//O(1)
    }//si nb =< 0 on est en O(1) Ou si nb > nbItems. on est en O(n)
    //si ce n'est n'est aucun de ces deux cas on est en O(n)

    /**
     * removes and returns the first item of the row
     * @return the removed item
     * @throws EmptyException if the row is empty
     */
    @Override
    public T remove() throws EmptyException{
        if(nbItems == 0){
            throw new EmptyException();
        }//O(1)
        T item = head.data;//O(1)
        head = head.next;//O(1)


        nbItems --;//O(1)
        if(nbItems < 1){
            queue = null;
        }//O(1)
        return item;//O(1)
    }//O(1)

    /**
     * Returns the number of items currently in the row.
     * @return the number of items in the row
     */
    @Override
    public int nbItems(){
        return nbItems;
    }//O(1)

    /**
     * gets the last item of the row (without removing)
     * @return the last item of the row
     * @throws EmptyException if the row is empty
     */
    @Override
    public T getLastItem() throws EmptyException{
        if(nbItems == 0){
            throw new EmptyException();
        }//O(1)

        return queue.data;//O(1)
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
        }//O(1)
        int sum = 0;//O(1)

        Cell current = head;//O(1)
        while(current != null) {
            sum += mapper.applyAsInt(current.data);
            current = current.next;
        }//O(n)

        return sum;//O(1)
    }//O(n)

    public class Cell{
        private T data;
        private Cell next;

        public Cell(T data){
            this.data = data;
        }
    }
}
