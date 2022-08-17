package com.example.roomreservation.DAO;


import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.RoomsEntity;
import com.example.roomreservation.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class RoomsDAO implements DAOInterface<RoomsEntity>{
    @Override
    public int addData(RoomsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(RoomsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.delete(data);
        t.commit();
        s.close();
        return 0;

    }

    @Override
    public int updateData(RoomsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();

        return 0;
    }


    @Override
    public ObservableList<RoomsEntity> getAll() {
        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(RoomsEntity.class);

        query.from(RoomsEntity.class);

        List<RoomsEntity> rList=s.createQuery(query).getResultList();
        s.close();

//        Session s= HibernateUtil.getSession();
//        CriteriaBuilder builder=s.getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(RoomsEntity.class);
//
////        query.from(RoomsEntity.class);
//        Root<RoomsEntity> root=query.from(RoomsEntity.class);
//        Predicate p=builder.equal(root.get("roomNo"),5);
//
//        List<RoomsEntity> rList=s.createQuery(query.where(p)).getResultList();
//        s.close();

        return FXCollections.observableArrayList(rList);
    }

    @Override
    public List<RoomsEntity> get() {
        return null;
    }

    @Override
    public ObservableList<BookingsEntity> getting() {
        return null;
    }


}
