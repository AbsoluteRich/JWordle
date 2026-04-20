import javax.naming.OperationNotSupportedException;

public class Wordle {
    private Wordle() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This is a class used to group methods, do not instantiate");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
    }
}
