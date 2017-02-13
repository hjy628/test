package cn.test;

/**
 * Created by hjy on 17-2-13.
 */
public class StringOther {

    private String name;

    public StringOther(String name)
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof StringOther))
            return false;
        StringOther so = (StringOther)obj;
        return so.getName().equals(name);
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return "["+this.name+":"+this.hashCode()+"]";
    }

}
