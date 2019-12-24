package by.imsha.domain;


public class LocalizedParish extends LocalizedBaseInfo {
    private String name;
    private String address;
    public LocalizedParish(String lang, String originObjId) {
        super(lang, originObjId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
