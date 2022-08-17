package com.example.roomreservation.DAO;


import com.example.roomreservation.Controller.HomeController;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.RoomsEntity;
import com.example.roomreservation.Util.HibernateUtil;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class BookingsDAO implements DAOInterface<BookingsEntity>{
    @Override
    public int addData(BookingsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(BookingsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.delete(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int updateData(BookingsEntity data) {
        Session s= HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.update(data);
        t.commit();
        s.close();
        return 0;
    }



    public ObservableList<BookingsEntity> getAll(){
        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query1=builder.createQuery(UserEntity.class);
        Root<UserEntity> root1=query1.from(UserEntity.class);
        Predicate p1=builder.equal(root1.get("username"), HomeController.name);
        List<UserEntity> uList =s.createQuery(query1.where(p1)).getResultList();
        int user= uList.get(0).getUserId();

        CriteriaQuery query=builder.createQuery(BookingsEntity.class);
        Root<BookingsEntity> root=query.from(BookingsEntity.class);
        Predicate p=builder.equal(root.get("UserId"),user);
        List<BookingsEntity> bList=s.createQuery(query.where(p)).getResultList();

        s.close();

        return FXCollections.observableArrayList(bList);
    }

    public ObservableList<BookingsEntity> get(){
        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query1=builder.createQuery(UserEntity.class);
        Root<UserEntity> root1=query1.from(UserEntity.class);
        Predicate p1=builder.equal(root1.get("username"), HomeController.name);
        List<UserEntity> uList =s.createQuery(query1.where(p1)).getResultList();
        int user= uList.get(0).getUserId();

        CriteriaQuery query=builder.createQuery(BookingsEntity.class);
        Root<BookingsEntity> root=query.from(BookingsEntity.class);
        Predicate p=builder.equal(root.get("UserId"),user);            // <<<<<----------------------------------------
        Predicate p2=builder.equal(root.get("reservedStatus"),"Not Booked");
        List<BookingsEntity> BList=s.createQuery(query.where(p,p2)).getResultList();

        s.close();

        return FXCollections.observableArrayList(BList);
    }

    public ObservableList<BookingsEntity> getting(){
        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(BookingsEntity.class);
        query.from(BookingsEntity.class);

        List<BookingsEntity> BookList=s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(BookList);
    }
}
