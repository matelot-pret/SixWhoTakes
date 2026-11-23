import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;

import java.util.function.ToIntFunction;

public class RowArray<T extends Comparable<T>> implements RowInterface<T>{

    private T[] items;
    private int nbItems;// nombre d'éléments dans le tableau. Si nbItems == 0 : le tableau est vide.
    //le dernier éléments du tableau se trouve toujours à nbItems -1. nbItems <= items.length

    public RowArray(){
        items = null;
        this.nbItems = 0;
    }

    /**
     * adds an item at the end of the row preserving the natural ordering of its elements
     * @param item the item to add, the item must not be null
     * @throws NotIncreasingException if the item is note greater than the last item of the row
     * @throws NullPointerException if the item is null
     */
    @Override
    public void addItem(T item) throws NotIncreasingException{
        if(item == null){
            throw new NullPointerException();
        }//O(1)

        if(nbItems > 0) {
            //si le tableau est vide item sera le premier élément
            // donc pas besoin de verifier si il est plus grand que le dernier élément
            if (item.compareTo(items[nbItems - 1]) <= 0) {
                throw new NotIncreasingException();
            }//O(1)

            if(nbItems >= items.length) {
                T[] tab = (T[]) new Comparable[items.length * 2];//O(1)

                for(int i = 0; i < nbItems; i++){
                    tab[i] = items[i];
                }//O(n)

                items = tab;//O(1)
            }//O(1)
        }//O(1)

        if(items == null){
            items = (T[]) new Comparable[6];
        }

        //un élément n'est ajouté que s'il est plus grand que le dernier élément
        items[nbItems] = item;//O(1)
        nbItems++;//O(1)
    }//O(1) si  item == null OU nbItems == 0
    // O(1) * n seulement dans le cas où le table est plein

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

        T[] tab = (T[]) new Comparable[nb];//O(1)
        for(int i = 0; i < nb; i++){
            try {
                tab[i] = this.remove();
            }catch (EmptyException ex){
                throw new NotAvailableException();
            }
        }////O(1) si nb == 0 sinon O(n)

        return tab;//O(1)
    }//O(1) si nb =< 0 ou nb > nbItems sinon //O(1) * n dû à la boucle sur le tableau

    /**
     * removes and returns the first item of the row
     * @return the removed item
     * @throws EmptyException if the row is empty
     */
    @Override
    public T remove() throws EmptyException{
        if(nbItems == 0){//O(1)
            throw new EmptyException();
        }

        T to_remove = items[0];//O(1)

        for(int i = 0; i < nbItems-1; i++){
            items[i] = items[i+1];
        }//O(n)

        items[nbItems-1] = null;//O(1)

        nbItems --;//O(1)

        return to_remove;//O(1)
    }//O(1) si nbItems == 0 ou si nbItems == 1, sinon //O(1) * n dû à la boucle for

    /**
     * Returns the number of items currently in the row.
     * @return the number of items in the row
     */
    @Override
    public int nbItems(){
        return nbItems;//O(1)
    }//O(1) temps constant, une seule opération dans tous les cas

    /**
     * gets the last item of the row (without removing)
     * @return the last item of the row
     * @throws EmptyException if the row is empty
     */
    @Override
    public T getLastItem() throws EmptyException{
        if(nbItems == 0){//O(1)
            throw new EmptyException();
        }

        return items[nbItems - 1];//O(1)
    }//O(1) temps constant, une seule opération dans tous les cas

    /**
     * @param mapper the function used to convert each item to an int,
     * must not be null
     * @return the sum of the row according mapper
     * @throws NullPointerException if mapper is null
     */
    @Override
    public int sumToInt(ToIntFunction<? super T> mapper){
        if(mapper == null){
            throw new NullPointerException();
        }//O(1)

        int sum = 0;//O(1)
        for(int i = 0; i < nbItems; i++){
            sum += mapper.applyAsInt(items[i]);
        }//O(n)
        return sum;//O(1)
    }//O(1) si nbItems == 0 ou mapper == null sinon 0(1) * n à cause de la boucle sur le tableau
}
