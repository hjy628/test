package ibm;

/**
 * Created by hjy on 16-2-16.
 */
public class Book {
    private String name;

    public Book()
    {
    }

    public Book( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
