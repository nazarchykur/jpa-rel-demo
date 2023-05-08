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
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GuildRepository guildRepository;
    private final ModelMapper modelMapper;

    public void createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
    }

    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto addGuildToEmployee(Long employeeId, GuildDto guildDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Guild guild = modelMapper.map(guildDto, Guild.class);
        employee.addGuild(guild);

//        employeeRepository.flush();

        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public void removeGuildFromEmployee(Long employeeId, Long guildId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));

        employee.removeGuild(guild);
    }
}
