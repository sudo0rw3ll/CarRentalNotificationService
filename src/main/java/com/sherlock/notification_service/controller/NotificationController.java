package com.sherlock.notification_service.controller;

import com.sherlock.notification_service.dto.NotificationCreateDto;
import com.sherlock.notification_service.dto.NotificationDto;
import com.sherlock.notification_service.dto.NotificationTypeDto;
import com.sherlock.notification_service.dto.NotificationUpdateDto;
import com.sherlock.notification_service.security.CheckSecurity;
import com.sherlock.notification_service.service.NotificationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @ApiOperation(value = "Get all notifications")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="size", value="Number of items to return", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name="sort",allowMultiple = true, dataType = "string", paramType = "query",
                    value="Sorting criteria in the format: property(, asc|desc). "+
                            "Default sort order is ascending. "+
                            "Multiple sort criteria are supported.")})
    @GetMapping("/")
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(Pageable pageable){
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Find by type")
    @GetMapping("/findByType/{id}")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<List<NotificationDto>> findByType(@RequestHeader("Authorization") String authorization, Long id){
        return new ResponseEntity<>(notificationService.findByType(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find by email")
    @GetMapping("/findByEmail/{email}")
    @CheckSecurity(userTypes = {"Admin","Manager","Client"})
    public ResponseEntity<List<NotificationDto>> findByEmail(@RequestHeader("Authorization") String authorization, String email){
        return new ResponseEntity<>(notificationService.findByEmail(email), HttpStatus.OK);
    }

    @ApiOperation(value = "Create notification")
    @PostMapping("/createNotification/")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationDto> createNotification(@RequestHeader("Authorization") String authorization,
                                                              @RequestBody @Valid NotificationCreateDto notificationCreateDto){
        return new ResponseEntity<>(notificationService.createNotification(notificationCreateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Update notification")
    @PutMapping("/updateNotification/{id}")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationDto> updateNotification(@RequestHeader("Authorization") String authorization,
                                                              @RequestBody @Valid NotificationUpdateDto notificationUpdateDto,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(notificationService.updateNotification(id, notificationUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete notification")
    @DeleteMapping("/deleteNotification/{id}")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationDto> deleteNotification(@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(notificationService.deleteNotification(id), HttpStatus.OK);
    }
}
