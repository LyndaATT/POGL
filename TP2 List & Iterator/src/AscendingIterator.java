import java.util.Iterator;
public class AscendingIterator<T> implements Iterator<T> {
        protected FixedCapacityList<T> list;
        protected int indiceSuivant ;

    public AscendingIterator(FixedCapacityList<T> list){
        this.list = list;
        this.indiceSuivant = 0;
    }


    @Override
    public boolean hasNext() {
        return this.list.tailleActuelle > this.indiceSuivant;
    }

    @Override
    public T next() {
        T elem = this.list.get(this.indiceSuivant);
        this.indiceSuivant++;
        return elem;
    }
}
