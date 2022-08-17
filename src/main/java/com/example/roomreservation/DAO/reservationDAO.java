package com.example.roomreservation.DAO;

import com.example.roomreservation.Controller.HomeController;
import com.example.roomreservation.Util.HibernateUtil;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.ReservationEntity;
import com.example.roomreservation.entity.RoomsEntity;
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

public class reservationDAO implements DAOInterface<ReservationEntity> {
    @Override
    public int addData(ReservationEntity data) {
        Session s=HibernateUtil.getSession();
        Transaction t=s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(ReservationEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t =s.beginTransaction();
        s.delete(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int updateData(ReservationEntity data) {
        return 0;
    }

    @Override
    public ObservableList<ReservationEntity> getAll() {

        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query1=builder.createQuery(UserEntity.class);
        Root<UserEntity> root1=query1.from(UserEntity.class);
        Predicate p1=builder.equal(root1.get("username"), HomeController.name);
        List<UserEntity> uList =s.createQuery(query1.where(p1)).getResultList();
        int user= uList.get(0).getUserId();

        CriteriaQuery query=builder.createQuery(ReservationEntity.class);
        Root<ReservationEntity> root=query.from(ReservationEntity.class);
        Predicate p=builder.equal(root.get("CustomerId"),user);
        Predicate p3 =builder.equal(root.get("paidStatus"),"Not Paid");
        List<ReservationEntity> RList=s.createQuery(query.where(p,p3)).getResultList();

        return FXCollections.observableArrayList(RList);
    }

    @Override
    public ObservableList<ReservationEntity> get() {
        Session s= HibernateUtil.getSession();
        CriteriaBuilder builder=s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ReservationEntity.class);
        query.from(ReservationEntity.class);

        List<ReservationEntity> ReList=s.createQuery(query).getResultList();
        s.close();
        return FXCollections.observableArrayList(ReList);
    }

    @Override
    public ObservableList<BookingsEntity> getting() {
        return null;
    }


}




