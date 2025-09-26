package com.example.bai_tap_thymeleaf.repository;

import com.example.bai_tap_thymeleaf.entity.Product;
import com.example.bai_tap_thymeleaf.utils.ConnectionUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductRepository implements IProductRepository{


    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Session session = ConnectionUtil.sessionFactory.openSession();;
        TypedQuery<Product> query = session.createQuery("FROM Product", Product.class);
        productList = query.getResultList();
        session.close();
        return productList;
    }

    @Override
    public void save(Product product) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.persist(product);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Product findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Product product = session.find(Product.class, id);
        session.close();
        return product;
    }



    @Override
    public void remove(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            Product product = session.find(Product.class, id);
            if (product != null){
                session.remove(product);
            }
            transaction.commit();
        }catch (
                Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }
}
