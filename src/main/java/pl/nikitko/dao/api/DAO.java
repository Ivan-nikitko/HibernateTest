package pl.nikitko.dao.api;

public interface DAO <Entity, Key> {
    void create (Entity entity);
    Entity read (Key key);
    void update (Entity entity);
    void delete (Key key);

}
