package Cell;

import java.io.Serializable;

/**
 * Klasa reprezentujÄ…ca abstrakcyjnÄ… komĂłrkÄ™.
 * @author wojboj
 */
public class Cell implements Serializable{

    /**
     * Stan komĂłrki. 0 - pusta, 1 - przewodnik, 2 - ogon elektronu, 3- gĹ‚owa elektronu.
     */
    protected  int value;

    /**
     * wspĂłĹ‚rzÄ™dna x komĂłrki
     */
    protected  int x;

    /**
     * wspĂłĹ‚rzÄ™dna y komĂłrki
     */
    protected  int y;

    /**
     *
     * @param y wspĂłĹ‚rzÄ™dna y komĂłrki
     * @param x wspĂłĹ‚rzÄ™dna x komĂłrki
     */
    public Cell(int y, int x) {
        this.y=y;
        this.x=x;
        value = 0;
    }


    /**
     * Ustawia stan komĂłrki. 0 - pusta, 1 - przewodnik, 2 - ogon elektronu, 3- gĹ‚owa elektronu.
     * @param v stan komĂłrki
     */

    public  void setValue(int v){
        this.value = v;
    }

    /**
     * Zwraca stan komĂłrki. 0 - pusta, 1 - przewodnik, 2 - ogon elektronu, 3- gĹ‚owa elektronu.
     * @return liczba oznaczajÄ…ca stan komĂłrki
     */
    public int getValue(){
        return value;
    }

    /**
     * Zwraca wspĂłĹ‚rzÄ™dnÄ… Y komĂłrki.
     * @return wspĂłĹ‚rzÄ™dna Y komĂłrki.
     */
    public int getY() {
        return y;
    }

    /**
     * Zwraca wspĂłĹ‚rzÄ™dnÄ… X komĂłrki.
     * @return wspĂłĹ‚rzÄ™dna X komĂłrki.
     */
    public int getX() {
        return x;
    }

    /**
     * Ustawia wspĂłĹ‚rzÄ™dnÄ… Y komĂłrki.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Ustawia wspĂłĹ‚rzÄ™dnÄ… X komĂłrki.
     */
    public void setX(int x) {
        this.x = x;
    };

}
