package web.response;


/**
 * Created by rupam on 26/6/16.
 */
public class NameValuePair {
    String name;
    Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameValuePair(String name, Integer value){
        this.name = name;
        this.value = value;
    }

}
