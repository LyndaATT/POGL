import java.lang.Iterable;
import java.util.Iterator;

public class LinkedList<T> implements List<T>, Iterable<T>  {
   protected Block<T> firstBlock;
   protected Block<T> lastBlock;
   protected int tailleActuelle;

    public LinkedList(){
        this.firstBlock = null;
        this.lastBlock = null;
        this.tailleActuelle = 0;
    }


    @Override
    public T get(int i) {
        Block<T> monBloc = this.firstBlock;
        T elem = null;
        for(int j=0; j<i;j++){
            monBloc = monBloc.nextBlock;
            elem = (T) monBloc.contents;
        }
        return elem;
    }

    @Override
    public void add(T elt) {
        Block<T> newNextBlock = null;
        if(this.tailleActuelle==0){
            this.firstBlock = new Block<>(elt,newNextBlock);
            this.lastBlock = this.firstBlock;
            this.tailleActuelle++;
        }else{
            Block<T> monBlock = new Block<>(elt,newNextBlock);
            this.lastBlock = monBlock;
            this.lastBlock.nextBlock = monBlock;
            this.tailleActuelle++;
        }
    }

    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }
}
