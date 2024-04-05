package Database;

import java.util.List;

public interface CRUD {

    public Object insert(Object obj);

    public List<Object> findAll();

    public boolean delete(Object obj);

    public boolean update(Object obj);
}
