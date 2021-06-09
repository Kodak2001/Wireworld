package Cell;

/**
 * Ogon elektronu
 * @author wojboj
 */
public class Tail extends Cell{

    /**
     * Tworzy komĂłrkÄ™ ze stanem "Ogon elektronu"
     * @param y wspĂłĹ‚rzÄ™dna Y komĂłrki
     * @param x wspĂłĹ‚rzÄ™dna X komĂłrki
     */
    public Tail(int y, int x){
        super(y,x);
        value = 2;
    }
}
