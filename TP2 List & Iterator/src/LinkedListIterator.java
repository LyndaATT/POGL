import java.util.Iterator;
public class LinkedListIterator<T> implements Iterator<T> {
    protected LinkedList<T> list;
    protected int indiceSuivant;
    public LinkedListIterator(LinkedList<T> list){
        this.list = list;
        this.indiceSuivant = 0;
    }
    @Override
    public boolean hasNext() {
        return this.indiceSuivant < this.list.tailleActuelle;
    }

    @Override
    public T next() {
        T elem = this.list.get(this.indiceSuivant);
        this.indiceSuivant++;
        return elem;
    }
}
