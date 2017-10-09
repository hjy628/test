package chap6;

/**
 * Created by hjy on 17-10-9.
 */
public enum Ensemble {
    SOLO,DUET,TRIO,QUARTET,QUINTET,
    SEXTET,SEPTET,OCTET,NONET,DECTET;

    public int numberOfMusicians(){
        return ordinal()+1;
    }
}
