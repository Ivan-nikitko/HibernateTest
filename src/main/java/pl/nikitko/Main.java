package pl.nikitko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.nikitko.dao.BrandDAO;
import pl.nikitko.dao.CarDAO;
import pl.nikitko.dao.OwnerDAO;
import pl.nikitko.model.Brand;
import pl.nikitko.model.Car;
import pl.nikitko.model.Owner;
import pl.nikitko.model.api.WheelDrive;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        OwnerDAO ownerDAO = new OwnerDAO(sessionFactory);
        BrandDAO brandDAO = new BrandDAO(sessionFactory);

        generateData(sessionFactory);


        System.out.println("*****************************");

        Owner owner = ownerDAO.read(1L);


        System.out.println("*********** carsByBrand ******************");

        Map<Brand, Car> carMap = owner.getCarsByBrand();
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


        System.out.println("************ CarModelByVin *****************");
        System.out.println(owner.getCarsModelByVin());


        System.out.println("************* CarsByVin ****************");
        Set<Map.Entry<Integer, Car>> entriesByVin = owner.getCarsByVin().entrySet();
        entriesByVin.stream().map(entry -> entry.getKey() + " || " + entry.getValue()).forEach(System.out::println);


        System.out.println("************ CarByDate *****************");
        for (Map.Entry<Date, Car> dateCarEntry : owner.getCarsByDate().entrySet()) {
            System.out.println(dateCarEntry.getKey() + "||" + dateCarEntry.getValue());
        }

        Query select_e_from_engine_e;
        try (Session session = sessionFactory.openSession()) {

            List select_en_from_engine_en = session.createQuery("select en from Engine en").list();

        }


    }



    private static void generateData(SessionFactory sessionFactory) {
        DataGenerator dataGenerator = new DataGenerator(sessionFactory);
        dataGenerator.generateBrands();
        dataGenerator.generateEngines();
        dataGenerator.generateOwners();
        try {
            dataGenerator.generateCars();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
