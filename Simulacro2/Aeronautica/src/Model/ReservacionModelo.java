package Model;

import Database.CRUD;

import java.util.List;

public class ReservacionModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public List<Object> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }
}
