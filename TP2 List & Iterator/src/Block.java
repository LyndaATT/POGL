public class Block<T> {
    protected T contents;
    protected Block<T> nextBlock;
    protected Block(T contents, Block<T> nextBlock){
        this.contents = contents;
        this.nextBlock = nextBlock;
    }
}
