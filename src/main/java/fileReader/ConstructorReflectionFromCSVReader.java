package fileReader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import foodDelivery.Sayable;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConstructorReflectionFromCSVReader implements Sayable {
    private static ConstructorReflectionFromCSVReader INSTANCE;

    private ConstructorReflectionFromCSVReader() {}

    static{
        try{
            INSTANCE = new ConstructorReflectionFromCSVReader();
        } catch (Exception e) {
            // SOON-TO-BE LOGGER
            e.printStackTrace();
        }
    }

    public static ConstructorReflectionFromCSVReader getInstance() {
        return INSTANCE;
    }

    public ArrayList<?> readFromCSVFile(Path filePath, String className, Class<?>[] type) throws Exception{
        ArrayList<Object> o = new ArrayList<>();
        try(CSVReader csvReader = new CSVReaderBuilder(
                new FileReader(filePath.toFile())
        ).build()) {
            Class<?> clazz = Class.forName(className);
            Constructor<?> ctor = clazz.getConstructor(type);

            List<String[]> l = new ArrayList<>(csvReader.readAll());
            l.forEach(data -> {
                try {
                    o.add(ctor.newInstance((Object[]) data));
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            LOGGER.error("ERROR while creating " + className);
        }
        return o;
    }
}
