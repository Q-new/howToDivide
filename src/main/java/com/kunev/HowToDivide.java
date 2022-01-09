package com.kunev;

import com.kunev.models.Person;
import com.kunev.services.DivideService;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class HowToDivide {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        final Class<? extends PrintStream> stdOutClass = System.out.getClass();
        final Field charOutField = stdOutClass.getDeclaredField("charOut");
        charOutField.setAccessible(true);
        OutputStreamWriter o = (OutputStreamWriter) charOutField.get(System.out);

        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }

        System.out.println("Для того что бы восользоваться программой\n" +
                "вводите по очереди сначала имя человека, который учавствует\n" +
                "в рассчётах, потом сумму , которую он потратил\n" +
                "Например:\n" +
                "Имя: Дима\n" +
                "Сумма: 150,34\n" +
                "Для получения рассчётов, вместо имени введите пустое значение или нажмите Enter\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, o.getEncoding()));
        try {
            while (true) {
                System.out.print("Имя: ");
                String name = reader.readLine();
                if ("".equalsIgnoreCase(name.trim())) {
                    break;
                }
                System.out.print("Сумма: ");
                double spending = Double.parseDouble(reader.readLine().replace(",","."));
                DivideService.addPerson(new Person(name, BigDecimal.valueOf(spending)));
            }

            System.out.println("\n-------------------------------------------------------------------------------------\n");
            DivideService.divide().forEach(System.out::println);
            System.out.printf("\nСредние затраты на человека составили %.2f", DivideService
                    .getAverage().doubleValue());
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Для выхода нажмите Enter");
        String s = reader.readLine();
    }
}
