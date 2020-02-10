import java.util.Iterator;
import java.lang.Iterable;
public class FixedCapacityList<T> implements List<T>, Iterable<T> {
       protected int capacity;
       protected Object[] notreList;
       // Attribut qui nous servira dans la fonction add
       protected int tailleActuelle;

       public FixedCapacityList(int capacity){
           this.capacity = capacity;
           this.notreList = new Object[this.capacity];
           this.tailleActuelle = 0;
       }

    @Override
    public T get(int i) {
        return (T) this.notreList[i];
    }

    @Override
    public void add(T elt) {
           if(this.tailleActuelle < this.capacity){
               this.notreList[this.tailleActuelle] = elt;
               this.tailleActuelle++;
           }
    }


    @Override
    public Iterator<T> iterator() {
        return new AscendingIterator<T>(this);
    }

}

