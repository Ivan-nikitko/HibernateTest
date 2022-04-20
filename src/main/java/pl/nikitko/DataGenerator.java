package pl.nikitko;

import org.hibernate.SessionFactory;
import pl.nikitko.dao.BrandDAO;
import pl.nikitko.dao.CarDAO;
import pl.nikitko.dao.EngineDAO;
import pl.nikitko.dao.OwnerDAO;
import pl.nikitko.model.*;
import pl.nikitko.model.api.BrandId;
import pl.nikitko.model.api.EngineId;
import pl.nikitko.model.api.WheelDrive;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
            brand.setFirstIdPart((long)i);
            brand.setSecondIdPart(i+10L);
            brand.setName("Brand_" + i);
            brand.setCountry("Country_" + i);
            brandDAO.create(brand);
        }
    }

    public void generateEngines() {
        for (int i = 0; i < 10; i++) {
            DieselEngine dieselEngine = new DieselEngine();
            dieselEngine.setId(new EngineId((long)i,i+10L));
            dieselEngine.setEngineModel("E_Model_" + i);
            dieselEngine.setPower(i*3+50);
            engineDAO.create(dieselEngine);
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

    public void generateCars() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            Car car = new Car();
            Brand read = brandDAO.read(new BrandId((long) i-1, i + 9L));
            car.setBrand(read);
            car.setEngine(engineDAO.read(new EngineId((long) i-1, i + 9L)));
            car.setCarModel("Model_" + i);
            car.setVin(ThreadLocalRandom.current().nextInt(1000000, 9999999));
            car.setOwner(ownerDAO.read((long) ThreadLocalRandom.current().nextInt(1, 3)));
            car.setCreatedOn(new Date());
            TimeUnit.MILLISECONDS.sleep(10L);
            if (ThreadLocalRandom.current().nextInt(1, 3) == 1) {
                car.setWheelDrive(WheelDrive.AWD);
            } else {
                car.setWheelDrive(WheelDrive.FWD);
            }
            carDAO.create(car);
        }
    }


}
