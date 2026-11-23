import Exceptions.EmptyException;
import Exceptions.NotAvailableException;
import Exceptions.NotIncreasingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public class TestRow {
    Row<Integer> row = new Row<Integer>();

    private void fillRow() throws NotIncreasingException{
        row.addItem(20);
        row.addItem(21);
        row.addItem(26);
        row.addItem(32);
        row.addItem(34);
        row.addItem(36);
    }

    private void emptyRow() throws EmptyException{
        while(row.nbItems() > 0){
            row.remove();
        }
    }

    private int nbElements(Comparable[] tab){
        int cpt = 0;
        for(int i = 0; i < tab.length; i++){
            if(tab[i] != null){
                cpt ++;
            }
        }
        return cpt;
    }

    @Test
    void testAddItem() throws NotIncreasingException {
        Assertions.assertThrows(NullPointerException.class, () -> {row.addItem(null);});
        row.addItem(26);
        Assertions.assertEquals(1, row.nbItems());
        Assertions.assertThrows(NotIncreasingException.class, () -> {row.addItem(20);});
    }

    @Test
    void testRemoveArray() throws NotAvailableException, NotIncreasingException, EmptyException{
        Comparable[] tab = new Comparable[6]; //en mettant Integer[] j'ai un ClassCastException car la fonction retourne un tableau Comparable qui ne peut être stocké dans un table Integer
        //il faudrait que la fonction remove(int nb) puisse savoir d'avancer qu'elle type est attendu à la sortie.
        //J'ai vu que je pouvais lui passer la classe attendu dans le constructeur mais je pense que ca dépasse le scope de l'exercice
        fillRow();
        Assertions.assertThrows(NotAvailableException.class, ()-> {row.remove(10);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {row.remove(-1);});
        row.remove(0);
        int nbElements = nbElements(tab);
        Assertions.assertEquals(0, nbElements);
        tab = row.remove(3);
        Assertions.assertEquals(3, tab.length);
        Assertions.assertEquals(20, tab[0]);
        Assertions.assertEquals(3, row.nbItems());
        emptyRow();
    }

    @Test
    void testRemoveElement() throws EmptyException, NotIncreasingException{
        Assertions.assertThrows(EmptyException.class, () -> {row.remove();});
        fillRow();
        Assertions.assertEquals(20, row.remove());
        Assertions.assertEquals(5, row.nbItems());
        emptyRow();
        row.addItem(25);
        Assertions.assertEquals(25,row.getHead().getData());
        row.remove();
        Assertions.assertEquals(null, row.getHead());//je pouvais aussi utiliser remove() et getLastItem()
        Assertions.assertEquals(null, row.getQueue());
    }

    @Test
    void testNbItems()throws NotIncreasingException, EmptyException{
        Assertions.assertEquals(0, row.nbItems());
        fillRow();
        Assertions.assertEquals(6, row.nbItems());
        emptyRow();
    }

    @Test
    void testGetLastItem() throws EmptyException, NotIncreasingException{
        Assertions.assertThrows(EmptyException.class, () -> {row.getLastItem();});
        fillRow();
        Assertions.assertEquals(36, row.getLastItem());
        emptyRow();
    }

    @Test
    void testSumToInt()throws NotIncreasingException, EmptyException{
        fillRow();
        int somme = 0;
        int t = row.nbItems();
        for(int i = 0; i < t; i++){
            Integer nombre = row.remove().intValue();
            somme += nombre.intValue();
        }
        ToIntFunction<Integer> converter = Integer::intValue;
        emptyRow();
        fillRow();
        Assertions.assertEquals(somme, row.sumToInt(converter));
        Assertions.assertThrows(NullPointerException.class, () -> {row.sumToInt(null);});
        emptyRow();
    }

}
