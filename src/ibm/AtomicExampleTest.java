package ibm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hjy on 16-2-16.
 */
public class AtomicExampleTest {
    private MyObject obj;

    @Before
    public void setUp()
    {
        obj = new MyObject();
        obj.setWhatImReading( new Book( "Java 2 From Scratch" ) );
    }

    @Test
    public void testUpdate()
    {
        obj.setWhatImReading( new Book(
                "Pro Java EE 5 Performance Management and Optimization" ) );
        Assert.assertEquals("Incorrect book name",
                "Pro Java EE 5 Performance Management and Optimization",
                obj.getWhatImReading().getName());
    }
}
