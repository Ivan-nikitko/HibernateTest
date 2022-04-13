package pl.nikitko;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.nikitko.dao.BrandDAO;
import pl.nikitko.dao.CarDAO;
import pl.nikitko.dao.OwnerDAO;
import pl.nikitko.model.Brand;
import pl.nikitko.model.Car;
import pl.nikitko.model.Owner;
import pl.nikitko.model.api.WheelDrive;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        //   BrandDAO brandDAO = new BrandDAO(sessionFactory);
        CarDAO carDAO = new CarDAO(sessionFactory);
        OwnerDAO ownerDAO = new OwnerDAO(sessionFactory);
        BrandDAO brandDAO = new BrandDAO(sessionFactory);

        generateData(sessionFactory);

//      Generate test entities, uncomment for use
//        for (int i = 0; i < 10; i++) {
//            Brand brand = new Brand();
//            brand.setName("Brand"+i);
//            brand.setCountry("Country"+i);
//            brandDAO.create(brand);
//        }

//        System.out.println("*************** readSqlQuery **************");
//
//        String sqlQuery = "SELECT * FROM application.car_brand";
//        List<Brand> brands = brandDAO.readSqlQuery(sqlQuery);
//        for (Brand brand : brands) {
//            System.out.println(brand);
//        }

//        System.out.println("*************** criteriaExample **************");
//
//        List<Brand> ownerList = brandDAO.criteriaExample();
//        for (Brand currentBrand : ownerList) {
//            System.out.println(currentBrand);
//        }
//
//
//        System.out.println("*************** criteriaPredicatesArrayExample **************");
//
//        List<Brand> brandList = brandDAO.criteriaPredicatesArrayExample();
//        for (Brand currentBrand : brandList) {
//            System.out.println(currentBrand);
//        }
//
//
//        System.out.println("*************** readSqlWithParameter **************");
//
//        Brand brand = brandDAO.readSqlWithParameter(2L);
//        System.out.println(brand);


//        List brands = brandDAO.readHQL();
//        for (Object brand : brands) {
//            System.out.println(brand);
//        }

        //   brandDAO.readHQ1();
        //   brandDAO.readHQ2();

        //      carDAO.readHQ1();
        //   carDAO.readHQL();


//        List<Car> carList = carDAO.criteriaExample();
//        for (Car car : carList) {
//            System.out.println(car);
//        }


//        System.out.println("*************** criteriaExample **************");
//
//        List<Owner> ownerList = ownerDAO.criteriaExample();
//        for (Owner owner : ownerList) {
//            System.out.println(owner);
//        }
        System.out.println("*****************************");

        Owner owner = ownerDAO.read(1L);

        System.out.println("************* entriesByVin ****************");

        Set<Map.Entry<Integer, Car>> entriesByVin = owner.getCarsByVin().entrySet();
        for (Map.Entry<Integer, Car> entry : entriesByVin) {
            System.out.println(entry.getKey()+" || "+ entry.getValue());
        }

        System.out.println("*********** entriesByBrand ******************");

        Map<Brand, Car> carMap = owner.getCarMap();
        Set<Map.Entry<Brand, Car>> entries = carMap.entrySet();
        for (Map.Entry<Brand, Car> entry : entries) {
            System.out.println(entry.getKey() + " || " + entry.getValue());

        }

        System.out.println("************ entriesByWD *****************");

        Set<Map.Entry<WheelDrive, Car>> entriesWD = owner.getCarsByWD().entrySet();
        if (!entriesWD.isEmpty()){
            System.out.println(entriesWD.size());
        }
        for (Map.Entry<WheelDrive, Car> wheelDriveCarEntry : entriesWD) {
            System.out.println(wheelDriveCarEntry.getKey()+ " || "+wheelDriveCarEntry.getValue());
        }

        System.out.println("************ CarByBrand *****************");

        Brand brand = brandDAO.read(2L);
        List<Car> carList = brand.getCarList();
        for (Car car : carList) {
            System.out.println(car.getCarModel());
        }

    }

    private static void generateData(SessionFactory sessionFactory) {
        DataGenerator dataGenerator = new DataGenerator(sessionFactory);
        dataGenerator.generateBrands();
        dataGenerator.generateEngines();
        dataGenerator.generateOwners();
        dataGenerator.generateCars();
    }
}
