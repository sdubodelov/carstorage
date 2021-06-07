package com.sdubadzelau.carstorage;

import com.sdubadzelau.carstorage.model.Car;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

public class CarIdGenerator implements IdentifierGenerator, Configurable {

    private String prefix;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {

        if (obj instanceof Car) {
            String carId = getCarId((Car) obj);
            if (!isBlankString(carId)) {
                prefix = carId;
            }
        }

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query).stream();

        Long max = ids.peek(id -> System.out.println("id: "+id))
                .filter(id -> id.startsWith(prefix + "-"))
                .map(o -> o.replace(prefix + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefix + "-" + (max + 1);
    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }

    boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }

    String getCarId(Car currentCar) {
        return (isBlankString(currentCar.getMake()) ? "" : currentCar.getMake().substring(0, Math.min(currentCar.getMake().length(), 3)))
                + (isBlankString(currentCar.getModel()) ? "" : currentCar.getModel().substring(0, Math.min(currentCar.getModel().length(), 3)));
    }
}
