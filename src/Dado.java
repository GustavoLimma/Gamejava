import java.util.*;

public class Dado {
    public int rolar() {
        return new Random().nextInt(6) + 1;
    }
}