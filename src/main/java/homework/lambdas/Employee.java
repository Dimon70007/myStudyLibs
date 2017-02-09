package homework.lambdas;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by OTBA}|{HbIu` on 30.12.16.
 * Предоставьте интерфейс Measurable с методом
 * double getMeasure(), измеряющим объект
 * определенным образом. Создайте класс Employee,
 * реализующий интерфейс Measurable. Предоставьте
 * метод double  average (Measurable[ ] objects),
 * вычисляющий среднюю меру. Воспользуйтесь
 * им для расчета средней зарплаты в массиве работников
 */
public class Employee implements Measurable<Employee> {

    private final String name;
    private double salary;

    public Employee(String name) {
        this(name,0);
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMeasure(Employee employee) {
        return salary;
    }


}
