package pl.nikitko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import pl.nikitko.dao.api.DAO;
import pl.nikitko.model.Brand;
import pl.nikitko.model.api.BrandId;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.criteria.*;
import java.util.List;





public class BrandDAO  {
    private final SessionFactory factory;

    public BrandDAO(SessionFactory factory) {
        this.factory = factory;
    }



    public void create(Brand brand) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(brand);
            session.getTransaction().commit();
        }
    }





    public Brand read(BrandId id) {
        try (Session session = factory.openSession()) {
            return session.get(Brand.class, id);
        }
    }


    public void update(Brand brand) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(brand);
            session.getTransaction().commit();
        }
    }


//    public void delete(Long id) {
//        try (Session session = factory.openSession()) {
//            session.beginTransaction();
//            Brand read = read(id);
//            if (read != null) {
//                session.delete(read(id));
//                session.getTransaction().commit();
//            } else {
//                System.out.println("Brand not found, id = " + id);
//            }
//        }
//    }

    public List<Brand> readSqlQuery(String sqlQuery) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            NativeQuery result = session.createSQLQuery(sqlQuery);
            result.addEntity(Brand.class);
            List<Brand> list = result.getResultList();
            return list;
        }
    }

    public Brand readSqlWithParameter(Long id) {
        try (Session session = factory.openSession()) {
            NativeQuery sqlQuery = session.createSQLQuery("select * from application.car_brand where id = :id");
            sqlQuery.setParameter("id", id);
            sqlQuery.addEntity(Brand.class);
            Brand singleResult = (Brand) sqlQuery.getSingleResult();
            return singleResult;
        }
    }

    public List readHQL() {
        try (Session session = factory.openSession()) {
            Query sqlQuery = session.createQuery("select distinct B.name  from Brand B" );
            sqlQuery.setFirstResult(0);
            sqlQuery.setMaxResults(3);

            List resultList = sqlQuery.getResultList();

            return resultList;
        }
    }

    public void readHQ1() {
        try (Session session = factory.openSession()) {
            Query sqlQuery = session.createQuery("select count (id) from Brand as B " );
            Object singleResult = sqlQuery.getSingleResult();
            System.out.println(singleResult);


        }


    }




    public List<Brand> criteriaExample() {
        List<Brand> results;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> root = criteriaQuery.from(Brand.class);
            Predicate predicate = criteriaBuilder.between(root.get("id"), 1, 3);
            criteriaQuery.select(root).where(predicate);
            Query<Brand> query = session.createQuery(criteriaQuery);
            results = query.getResultList();
        }
        return results;
    }

    public List<Brand> criteriaPredicatesArrayExample() {
        Query<Brand> query;
        List<Brand> results;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> root = criteriaQuery.from(Brand.class);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.like(root.get("name"),"Brand5");
            predicates[1] = criteriaBuilder.lessThan(root.get("id"), 16);

            criteriaQuery.select(root).where(predicates);
            query = session.createQuery(criteriaQuery);
            results = query.getResultList();
//            Query from_brand = session.createQuery("from Brand");
//            results = from_brand.getResultList();
        }
        return results;
    }




}
