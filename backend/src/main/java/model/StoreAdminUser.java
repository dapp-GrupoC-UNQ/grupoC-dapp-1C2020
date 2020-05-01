package model;

public class StoreAdminUser extends User {

    private Comercio store;

    public StoreAdminUser(String username, String password, Comercio store) {
        super(username, password);
        this.store = store;
    }

    public Comercio store() {
        return this.store;
    }

    @Override
    public Boolean isAdminOfStore() { return true;}
}
