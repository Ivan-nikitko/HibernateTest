package pl.nikitko;

import org.hibernate.SessionFactory;
import pl.nikitko.dao.BrandDAO;
import pl.nikitko.dao.CarDAO;
import pl.nikitko.dao.EngineDAO;
import pl.nikitko.dao.OwnerDAO;
import pl.nikitko.model.*;
import pl.nikitko.model.api.WheelDrive;

import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    private CarDAO carDAO;
    private BrandDAO brandDAO;
    private EngineDAO engineDAO;
    private OwnerDAO ownerDAO;

    public DataGenerator(SessionFactory sessionFactory) {
        this.carDAO = new CarDAO(sessionFactory);
        this.brandDAO = new BrandDAO(sessionFactory);
        this.engineDAO = new EngineDAO(sessionFactory);
        this.ownerDAO = new OwnerDAO(sessionFactory);

    }

    public void generateBrands() {
        for (int i = 0; i < 10; i++) {
            Brand brand = new Brand();
            brand.setName("Brand_" + i);
            brand.setCountry("Country_" + i);
            brandDAO.create(brand);
        }
    }

    public void generateEngines() {
        for (int i = 0; i < 10; i++) {
            Engine engine = new Engine();
            engine.setEngineModel("E_Model_" + i);
            engineDAO.create(engine);
        }
    }

    public void generateOwners() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Ivan");
        ownerDAO.create(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Alex");
        ownerDAO.create(owner2);

    }

    public void generateCars() {
        for (int i = 1; i <= 10; i++) {
            Car car = new Car();
            car.setBrand(brandDAO.read((long) i));
            car.setEngine(engineDAO.read((long) i));
            car.setCarModel("Model_" + i);
            car.setVin(ThreadLocalRandom.current().nextInt(1000000, 9999999));
            car.setOwner(ownerDAO.read((long) ThreadLocalRandom.current().nextInt(1, 3)));
            if (ThreadLocalRandom.current().nextInt(1, 3) == 1) {
                car.setWheelDrive(WheelDrive.AWD);
            } else {
                car.setWheelDrive(WheelDrive.FWD);
            }
            carDAO.create(car);
        }
    }


}
