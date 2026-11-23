import Exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TestRowArray {
    RowArray<Integer> row = new RowArray<>();

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
    void testAddItem() throws NotIncreasingException, EmptyException{
        Assertions.assertThrows(NullPointerException.class, () -> {row.addItem(null);});
        row.addItem(25);
        Assertions.assertEquals(1,row.nbItems());
        Assertions.assertEquals(25, row.remove().intValue());
        row.addItem(52);
        Assertions.assertThrows(NotIncreasingException.class, () -> {row.addItem(50);});
    }

    @Test
    void testRemoveArray() throws NotAvailableException, NotIncreasingException, EmptyException{
        Comparable[] tab = new Comparable[6]; //en mettant Integer[] j'ai un ClassCastException car la fonction retourne un tableau Comparable qui ne peut être stocké dans un table Integer
        //il faudrait que la fonction remove(int nb) puisse savoir d'avancer qu'elle type est attendu à la sortie.
        //J'ai vu que je pouvais lui passer la classe attendu dans le constructeur mais je pense que ca dépasse le scope de l'exercice
        fillRow();
        Assertions.assertThrows(NotAvailableException.class, ()-> {row.remove(10);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {row.remove(-1);});
        tab = row.remove(0);
        int nbElements = nbElements(tab);
        Assertions.assertEquals(0, nbElements);
        tab = row.remove(3);
        nbElements = nbElements(tab);
        Assertions.assertEquals(3, nbElements);
        Assertions.assertEquals(20, tab[0]);
        Assertions.assertEquals(3, row.nbItems());
        emptyRow();
    }
}
