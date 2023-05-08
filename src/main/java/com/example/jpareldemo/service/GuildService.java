package com.example.jpareldemo.service;

import com.example.jpareldemo.dto.EmployeeDto;
import com.example.jpareldemo.dto.GuildDto;
import com.example.jpareldemo.entity.Employee;
import com.example.jpareldemo.entity.Guild;
import com.example.jpareldemo.repository.EmployeeRepository;
import com.example.jpareldemo.repository.GuildRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GuildService {

    private final GuildRepository guildRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public void createGuild(GuildDto guildDto) {
        guildRepository.save(modelMapper.map(guildDto, Guild.class));
    }

    public GuildDto findById(Long guildId) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));
        return modelMapper.map(guild, GuildDto.class);
    }

    public GuildDto addEmployeeToGuild(Long guildId, EmployeeDto employeeDto) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        guild.addEmployee(employee);

        return modelMapper.map(guild, GuildDto.class);
    }

        /*
    Якщо продуктивність є критичним фактором, то краще використовувати перший метод, оскільки він не виконує додаткового
    запиту до бази даних для отримання екземпляру `Employee`. В цьому випадку ми використовуємо метод `getEmployees()` з
    `Guild` для отримання списку працівників гільдії та потім фільтруємо його за ідентифікатором працівника.

    Однак, якщо продуктивність не є критичним фактором, то можна використовувати другий метод, оскільки він є більш
    простим та зрозумілим. В цьому випадку ми використовуємо метод `findById()` з `EmployeeRepository` для отримання
    екземпляру `Employee` за ідентифікатором.



    Ці запити відбуваються в процесі виконання методу `removeEmployeeFromGuild()` в класі `GuildService`.


     */

    //    1)
    /*
    1. Перший запит вибирає гільдію з бази даних за ідентифікатором `guildId`.
    2. Другий запит вибирає всіх працівників, які належать до гільдії з ідентифікатором `guildId`.
    3. Третій запит вибирає всі гільдії, до яких належить працівник з ідентифікатором `employeeId`.
    4. Четвертий запит видаляє всі записи про зв'язок між працівником та гільдією з таблиці `employees_guilds`, де `employee_id` дорівнює `employeeId`.
    5. П'ятий запит додає запис про зв'язок між працівником та гільдією з таблиці `employees_guilds` для кожної гільдії зі списку `guildIds`.
    6. Шостий запит додає запис про зв'язок між працівником та гільдією з таблиці `employees_guilds` для гільдії з ідентифікатором `defaultGuildId`.
     */
    public void removeEmployeeFromGuild(Long guildId, Long employeeId) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));

        Employee employee = guild.getEmployees().stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        guild.removeEmployee(employee);
    }

    //    2)
    /*
    1. Перший запит вибирає гільдію з бази даних за ідентифікатором `guildId`.
    2. Другий запит вибирає працівника з бази даних за ідентифікатором `employeeId`.
    3. Третій запит вибирає всі гільдії, до яких належить працівник з ідентифікатором `employeeId`.
    4. Четвертий запит вибирає всіх працівників, які належать до гільдії з ідентифікатором `guildId`.
    5. П'ятий запит видаляє запис про зв'язок між працівником та гільдією з таблиці `employees_guilds`.
    6. Шостий запит додає запис про зв'язок між працівником та гільдією з таблиці `employees_guilds`.
    7. Сьомий запит додає запис про зв'язок між працівником та гільдією з таблиці `employees_guilds`.
     */
//    public void removeEmployeeFromGuild(Long guildId, Long employeeId) {
//        Guild guild = guildRepository.findById(guildId)
//                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));
//
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
//
//        guild.removeEmployee(employee);
//    }
}
