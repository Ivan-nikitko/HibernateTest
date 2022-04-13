package pl.nikitko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.nikitko.dao.api.DAO;
import pl.nikitko.model.Car;
import pl.nikitko.model.Owner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class OwnerDAO implements DAO<Owner, Long> {
    private final SessionFactory factory;

    public OwnerDAO(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(Owner owner) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(owner);
            session.getTransaction().commit();
        }
    }

    @Override
    public Owner read(Long id) {
        try (Session session = factory.openSession()) {
            Owner result = session.get(Owner.class, id);
            return result;
        }
    }

    @Override
    public void update(Owner owner) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(owner);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Owner read = read(id);
            if (read != null) {
                session.delete(read(id));
                session.getTransaction().commit();
            } else {
                System.out.println("Owner not found, id = " + id);
            }
        }
    }


//    public List<Brand> readSqlQuery(String sqlQuery) {
//        try (Session session = factory.openSession()) {
//            session.beginTransaction();
//            NativeQuery result = session.createSQLQuery(sqlQuery);
//            result.addEntity(Brand.class);
//            List<Brand> list = result.getResultList();
//            return list;
//        }
//    }
//
//    public Brand readSqlWithParameter(Long id) {
//        try (Session session = factory.openSession()) {
//            NativeQuery sqlQuery = session.createSQLQuery("select * from application.car_brand where id = :id");
//            sqlQuery.setParameter("id", id);
//            sqlQuery.addEntity(Brand.class);
//            Brand singleResult = (Brand) sqlQuery.getSingleResult();
//            return singleResult;
//        }
//    }
//
//    public List readHQL() {
//        try (Session session = factory.openSession()) {
//            Query sqlQuery = session.createQuery("select distinct B.name  from Brand B" );
//            sqlQuery.setFirstResult(0);
//            sqlQuery.setMaxResults(3);
//
//            List resultList = sqlQuery.getResultList();
//
//            return resultList;
//        }
//    }

//    public void readHQ1() {
//        try (Session session = factory.openSession()) {
//            Query sqlQuery = session.createQuery("select count (id) from Brand as B " );
//            Object singleResult = sqlQuery.getSingleResult();
//            System.out.println(singleResult);
//
//
//        }
//
//
//    }
//
//    public void readHQ2() {
//        try (Session session = factory.openSession()) {
//            Query sqlQuery = session.getNamedQuery("brand.findAll" );
//            List resultList = sqlQuery.getResultList();
//            for (Object o : resultList) {
//                System.out.println(o);
//            }
//        }
//
//
//    }


    public List<Owner> criteriaExample() {
        List<Owner> results;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
            Root<Owner> root = criteriaQuery.from(Owner.class);
            Predicate predicate = criteriaBuilder.equal(root.get("id"),  1);
            criteriaQuery.select(root).where(predicate);
            Query<Owner> query = session.createQuery(criteriaQuery);
            results = query.getResultList();
        }
        return results;
    }

//    public List<Brand> criteriaPredicatesArrayExample() {
//        Query<Brand> query;
//        List<Brand> results;
//        try (Session session = factory.openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
//            Root<Brand> root = criteriaQuery.from(Brand.class);
//
//            Predicate[] predicates = new Predicate[2];
//            predicates[0] = criteriaBuilder.like(root.get("name"),"Brand5");
//            predicates[1] = criteriaBuilder.lessThan(root.get("id"), 16);
//
//            criteriaQuery.select(root).where(predicates);
//            query = session.createQuery(criteriaQuery);
//            results = query.getResultList();
////            Query from_brand = session.createQuery("from Brand");
////            results = from_brand.getResultList();
//        }
//        return results;
//    }





}
