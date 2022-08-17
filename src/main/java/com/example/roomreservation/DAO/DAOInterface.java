package com.example.roomreservation.DAO;


import com.example.roomreservation.entity.BookingsEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface DAOInterface<T> {

    public int addData(T data);
    public int deleteData(T data);
    public int updateData(T data);

    List<T> getAll();
    List<T> get();


    ObservableList<BookingsEntity> getting();
}
