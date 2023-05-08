package com.example.jpareldemo.controller;

import com.example.jpareldemo.dto.EmployeeDto;
import com.example.jpareldemo.dto.GuildDto;
import com.example.jpareldemo.service.GuildService;
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
@RequestMapping("/guilds")
@RequiredArgsConstructor
public class GuildController {

    private final GuildService guildService;

    @PostMapping
    public ResponseEntity<Void> createGuild(@RequestBody GuildDto guildDto) {
        guildService.createGuild(guildDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{guildId}")
    public ResponseEntity<GuildDto> findById(@PathVariable Long guildId) {
        return ResponseEntity.ok(guildService.findById(guildId));
    }

    @PostMapping("/{guildId}/employee")
    public ResponseEntity<GuildDto> addEmployeeToGuild(@PathVariable Long guildId, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(guildService.addEmployeeToGuild(guildId, employeeDto));
    }

    @DeleteMapping("/{guildId}/employee/{employeeId}")
    public ResponseEntity<Void> removeEmployeeFromGuild(@PathVariable Long guildId, @PathVariable Long employeeId) {
        guildService.removeEmployeeFromGuild(guildId, employeeId);
        return ResponseEntity.ok().build();
    }
}
