import java.util.Iterator;
public class DescendingIterator<T> implements Iterator<T> {
        protected FixedCapacityList<T> list;
        protected int indiceSuivant ;

    protected DescendingIterator(FixedCapacityList<T> list){
        this.list = list;
        this.indiceSuivant = list.tailleActuelle - 1;
    }


    @Override
        public boolean hasNext() {
            return this.indiceSuivant>=0;
        }

        @Override
        public T next() {
            T elem = (T) this.list.get(this.indiceSuivant);
            this.indiceSuivant--;
            return elem;
        }
}