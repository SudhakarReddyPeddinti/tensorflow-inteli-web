/**
 * Created by Naga on 13-03-2017.
 */
public  class Data {

    private static Data instance = null;
    String data="";
    Boolean flag = true;

    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
