package pl.nikitko;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.nikitko.dao.BrandDAO;
import pl.nikitko.model.Brand;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        BrandDAO brandDAO = new BrandDAO(sessionFactory);

//        for (int i = 0; i < 10; i++) {
//            Brand brand = new Brand();
//            brand.setName("Brand"+i);
//            brand.setCountry("Country"+i);
//            brandDAO.create(brand);
//        }

        System.out.println("*************** readSqlQuery **************");

        String sqlQuery = "SELECT * FROM application.car_brand";
        List<Brand> brands = brandDAO.readSqlQuery(sqlQuery);
        for (Brand brand : brands) {
            System.out.println(brand);
        }

        System.out.println("*************** criteriaExample **************");

        List<Brand> list = brandDAO.criteriaExample();
        for (Brand currentBrand : list) {
            System.out.println(currentBrand);
        }


        System.out.println("*************** criteriaPredicatesArrayExample **************");

        List<Brand> brandList = brandDAO.criteriaPredicatesArrayExample();
        for (Brand currentBrand : brandList) {
            System.out.println(currentBrand);
        }


        System.out.println("*************** readSqlWithParameter **************");

        Brand brand = brandDAO.readSqlWithParameter(2L);
        System.out.println(brand);


    }
}
