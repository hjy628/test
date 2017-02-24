package threadandlock;

import java.util.HashMap;

/**
 * Created by hjy on 17-2-24.
 */
public class ImmutableClass {

    //required fields
    private int id;
    private String name;

    //optional fields
    private HashMap properties;
    private String company;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap getProperties() {
        //return cloned object to avoid changing it by the client application
        return (HashMap) properties.clone();
    }

    public String getCompany() {
        return company;
    }

    private ImmutableClass(ImmutableClassBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.properties = builder.properties;
        this.company = builder.company;
    }

    //Builder class
    public static class ImmutableClassBuilder{
        //required fields
        private int id;
        private String name;

        //optional fields
        private HashMap properties;
        private String company;

        public ImmutableClassBuilder(int i, String nm){
            this.id=i;
            this.name=nm;
        }

        public ImmutableClassBuilder setProperties(HashMap hm){
            this.properties = (HashMap) hm.clone();
            return this;
        }

        public ImmutableClassBuilder setCompany(String comp){
            this.company = comp;
            return this;
        }

        public ImmutableClass build(){
            return new ImmutableClass(this);
        }
    }

}
