package pl.nikitko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import pl.nikitko.dao.api.DAO;
import pl.nikitko.model.Brand;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class BrandDAO implements DAO<Brand, Long> {
    private final SessionFactory factory;

    public BrandDAO(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(Brand brand) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(brand);
            session.getTransaction().commit();
        }
    }

    @Override
    public Brand read(Long id) {
        try (Session session = factory.openSession()) {
            Brand result = session.get(Brand.class, id);
            return result;
        }
    }

    @Override
    public void update(Brand brand) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(brand);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Brand read = read(id);
            if (read != null) {
                session.delete(read(id));
                session.getTransaction().commit();
            } else {
                System.out.println("Brand not found, id = " + id);
            }
        }
    }

    public List<Brand> readSqlQuery(String sqlQuery) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            NativeQuery result = session.createSQLQuery(sqlQuery);
            result.addEntity(Brand.class);
            List<Brand> list = result.getResultList();
            return list;
        }
    }

    public List<Brand> criteriaExample() {
        Query<Brand> query;
        List<Brand> results;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
            Root<Brand> root = criteriaQuery.from(Brand.class);
            Predicate predicate = criteriaBuilder.between(root.get("id"), 1, 3);
            criteriaQuery.select(root).where(predicate);
            query = session.createQuery(criteriaQuery);
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
            predicates[0] = criteriaBuilder.like(root.get("name"),"Brand2");
            predicates[1] = criteriaBuilder.ge(root.get("id"), 3);

            criteriaQuery.select(root).where(predicates);
            query = session.createQuery(criteriaQuery);
            results = query.getResultList();
        }
        return results;
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


}
