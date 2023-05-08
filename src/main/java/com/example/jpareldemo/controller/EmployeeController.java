package com.example.jpareldemo.controller;

import com.example.jpareldemo.dto.EmployeeDto;
import com.example.jpareldemo.dto.GuildDto;
import com.example.jpareldemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PostMapping("/{employeeId}/guild")
    public ResponseEntity<EmployeeDto> addGuildToEmployee(@PathVariable Long employeeId, @RequestBody GuildDto guildDto) {
        return ResponseEntity.ok(employeeService.addGuildToEmployee(employeeId, guildDto));
    }

    @DeleteMapping("/{employeeId}/guild/{guildId}")
    public ResponseEntity<Void> removeGuildFromEmployee(@PathVariable Long employeeId, @PathVariable Long guildId) {
        employeeService.removeGuildFromEmployee(employeeId, guildId);
        return ResponseEntity.ok().build();
    }
}
