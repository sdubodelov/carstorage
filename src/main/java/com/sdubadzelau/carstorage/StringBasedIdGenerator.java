package com.sdubadzelau.carstorage;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Properties;

public class StringBasedIdGenerator implements IdentifierGenerator, Configurable {

    private String propertyName;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {

        if(isBlankString(propertyName)){
            throw new IdentifierGenerationException("propertyName configuration is absent");
        }

        if (obj != null) {

            Class  aClass = obj.getClass();
            Field field;
            try {
                field = aClass.getDeclaredField(propertyName);
                if (!field.getType().isAssignableFrom(String.class)) {
                    throw new IdentifierGenerationException(String.format("propertyName: %s \r Field type should String", propertyName));
                }
                field.setAccessible(true);
                String value = (String) field.get(obj);
                return Optional.of(value).orElse("").replaceAll("\\s+", "-");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                throw new IdentifierGenerationException(String.format("propertyName: %s \r There is no such field in class", propertyName));
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
                throw new IdentifierGenerationException(String.format("propertyName: %s \r Wrong field accessibility", propertyName));
            }
        }
        return "";
    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        propertyName = properties.getProperty("propertyName");
    }

    boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
