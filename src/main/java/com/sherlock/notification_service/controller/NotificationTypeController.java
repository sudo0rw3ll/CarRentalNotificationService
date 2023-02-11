package com.sherlock.notification_service.controller;

import com.sherlock.notification_service.dto.*;
import com.sherlock.notification_service.mapper.NotificationTypeMapper;
import com.sherlock.notification_service.security.CheckSecurity;
import com.sherlock.notification_service.service.NotificationService;
import com.sherlock.notification_service.service.NotificationTypeService;
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
@RequestMapping("/notificationType")
public class NotificationTypeController {

    private NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService){
        this.notificationTypeService = notificationTypeService;
    }

    @ApiOperation(value = "Get all notification types")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="size", value="Number of items to return", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name="sort",allowMultiple = true, dataType = "string", paramType = "query",
                    value="Sorting criteria in the format: property(, asc|desc). "+
                            "Default sort order is ascending. "+
                            "Multiple sort criteria are supported.")})
    @GetMapping("/notificationTypes/")
    @CheckSecurity(userTypes={"Admin","Manager","Client"})
    public ResponseEntity<Page<NotificationTypeDto>> getAllUsers(@RequestHeader("Authorization") String authorization, Pageable pageable){
        return new ResponseEntity<>(notificationTypeService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Create notification")
    @PostMapping("/createNotificationType/")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationTypeDto> createNotificationType(@RequestHeader("Authorization") String authorization,
                                                              @RequestBody @Valid NotificationTypeCreateDto notificationTypeCreateDto){
        return new ResponseEntity<>(notificationTypeService.createNotification(notificationTypeCreateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Update notification type")
    @PutMapping("/updateNotificationType/{id}")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationTypeDto> updateNotificationType(@RequestHeader("Authorization") String authorization,
                                                              @RequestBody @Valid NotificationTypeUpdateDto notificationUpdateDto,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(notificationTypeService.updateNotification(id, notificationUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete notification type")
    @DeleteMapping("/deleteNotificationType/{id}")
    @CheckSecurity(userTypes = {"Admin"})
    public ResponseEntity<NotificationTypeDto> deleteNotificationType(@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(notificationTypeService.deleteNotification(id), HttpStatus.OK);
    }
}
