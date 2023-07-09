package com.ascending.demo.api.dao.impl.hibernate;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.util.HQLStatementUtil;
import com.ascending.demo.api.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("suppliersDaoHibernateImpl")
public class SuppliersDaoHibernateImpl implements SuppliersDao {

    private Logger logger = LoggerFactory.getLogger(SuppliersDaoHibernateImpl.class);

    @Override
    public Suppliers save(Suppliers suppliers) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
       session.persist(suppliers);
       //session.save(suppliers);
            transaction.commit();
        } catch (Exception e) {
            logger.error("fail to insert a supplier, error = {}", e.getMessage());
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return suppliers;
    }

    @Override
    public Suppliers update(Suppliers suppliers) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(suppliers); //PK是null就save，PK有值就是update
            transaction.commit();
        } catch (Exception e) {
            logger.error("fail to update a supplier, error = {}", e.getMessage());
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long supplierId) {
        return false;
    }

    @Override
    public boolean delete(Suppliers suppliers) {
        return false;
    }

//    @Override
//    public boolean deleteSuppliers(Suppliers suppliers) {
//        boolean deleteResult = false;
//        Transaction
//
//    }

    @Override
    public List<Suppliers> getSuppliers() {
       List<Suppliers> suppliersList = null;
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       Session session = sessionFactory.openSession();
       try{
           Query<Suppliers> query = session.createQuery(HQLStatementUtil.HQL_SELECT_ALL_SUPPLIERS);
           suppliersList = query.list();
       } catch (HibernateException he) {
           logger.error("fail to retrieve all majors, error = ", he.getMessage());
       } finally {
           session.close();
       }
       if (suppliersList == null)
           suppliersList = new ArrayList<Suppliers>();
       return suppliersList;
    }

    @Override
    public Suppliers getSupplierById(Long supplierId) {
        Suppliers suppliers = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Query<Suppliers> query = session.createQuery(HQLStatementUtil.HQL_SELECT_SUPPLIERS_BY_ID);
            query.setParameter("id", supplierId);
            suppliers = query.uniqueResult();
        } catch (HibernateException he) {
            logger.error("fail to retrieve the supplier with id = {}, error = {}", supplierId, he.getMessage());
        } finally {
            session.close();
        }
        return suppliers;

    }

    @Override
    public Suppliers getSuppliersAndUsersBySupplierId(Long supplierId) {
        Suppliers suppliers = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Query<Suppliers> query = session.createQuery(HQLStatementUtil.HQL_SELECT_SUPPLIER_WITH_USERS_BY_SUPPLIER_ID);
            query.setParameter("id", supplierId);
            suppliers = query.uniqueResult();
        } catch (HibernateException he) {
            logger.error("fail to retrieve the supplier with id = {}, error = {}", supplierId, he.getMessage());
        } finally {
            session.close();
        }
        return suppliers;
    }
}
