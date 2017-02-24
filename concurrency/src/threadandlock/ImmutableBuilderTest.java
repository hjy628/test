package threadandlock;

import java.util.HashMap;

/**
 * Created by hjy on 17-2-24.
 */
public class ImmutableBuilderTest {

    public static void main(String[] args) {

        // Getting immutable class only with required parameters
        ImmutableClass immutableClass = new ImmutableClass.ImmutableClassBuilder(1, "Pankaj").build();

        HashMap hm = new HashMap();
        hm.put("Name", "Pankaj");
        hm.put("City", "San Jose");
        // Getting immutable class with optional parameters
        ImmutableClass immutableClass1 = new ImmutableClass.ImmutableClassBuilder(1, "Pankaj")
                .setCompany("Apple").setProperties(hm).build();

        //Testing immutability
        HashMap hm1 = immutableClass1.getProperties();

        //lets modify the Object passed as argument or get from the Object
        hm1.put("test", "test");

        //check that immutable class properties are not changed
        System.out.println(immutableClass1.getProperties());
    }


}
