package com.ttn.bootcamp.controller.day2;

import com.ttn.bootcamp.dto.day2.ChangePasswordRequest;
import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.serivce.day2.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/day-2/user")
@Tag(name = "Day 2 - User APIs", description = " CRUD API for user with support for xml and swagger description")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Add user",
            description = "Add a user using xml and json.<br>Change headers to switch response and request type")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get all users",
            description = "Fetches all users in xml and json format.<br>Change headers to switch response and request type")
    public List<User> getAllUsersFullDetails() {
        return userService.getAllUser();
    }



    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user",
            description = "Delete user by given username")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User '" + username + "' deleted successfully");
    }


    @GetMapping(value = "/all/filter")
    @Operation(summary = "Get all user details with dynamic filter",
            description = "Fetches all users' fields which are passed in the parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Filtered User List",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    )
            })
    public MappingJacksonValue getUsersCustomFields(@RequestParam List<String> fields) {
        return userService.getUserWithDynamicFilter(fields);
    }


    @PatchMapping("/{username}/change-password")
    @Operation(
            summary = "Change user password",
            description = "Updates the user's password"
    )
    public ResponseEntity<String> changePassword(
            @PathVariable String username,
            @RequestBody ChangePasswordRequest request) {
        userService.changeUserPassword(username, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully!");
    }


    /*
    * URL versioning
    * */

    // Old implementation to get user
    @GetMapping("/v1/{username}")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public User getUserByUsernameOldUrlVersion(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    // new implementation to get user with hateoas
    @GetMapping("/v2/{username}")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public EntityModel<User> getUserByUsernameUrlVersion(@PathVariable String username) {
        User user= userService.getUserByUsername(username);
       return getEntityWithLinks(user,username);
    }

    /*
     * Request Parameter Versioning
     * */

    @GetMapping("/{username}")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public Object getUserByUsernameReqParamsVersion(@PathVariable String username, @RequestParam(name = "version") int version) {
        User user = userService.getUserByUsername(username);

        if (version == 1) {
            return user;
        } else if (version == 2) {
            return getEntityWithLinks(user, username);
        } else {
            throw new IllegalArgumentException("Invalid version");
        }
    }

    /*
     * MimeType Versioning
     * */

    // Old implementation to get user using mimetype versioning
    @GetMapping(value="/{username}/mime-type",produces = "application/vnd.company.app-v1+json")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public User getUserByUsernameOldMimeTypeVersion(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    // new implementation to get user with hateoas using mimetype versioning
    @GetMapping(value="/{username}/mime-type",produces = "application/vnd.company.app-v2+json")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public EntityModel<User> getUserByUsernameMimeTypeVersion(@PathVariable String username) {
        User user= userService.getUserByUsername(username);
        return getEntityWithLinks(user,username);
    }

    /*
     * Custom Header Versioning
     * */

    @GetMapping("/{username}/header")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.<br>" +
                    "Contain hateoas links for get all user and change user password")
    public Object getUserByUsernameHeaderVersion(@PathVariable String username, @RequestHeader("X-API-VERSION") int version) {
        User user = userService.getUserByUsername(username);

        if (version == 1) {
            return user;
        } else if (version == 2) {
            return getEntityWithLinks(user, username);
        } else {
            throw new IllegalArgumentException("Invalid version");
        }
    }

    // function to get links for hateoas
    private EntityModel<User> getEntityWithLinks(User user, String username){
        EntityModel<User> entityModel= EntityModel.of(user);

        if(username!=null) {
            WebMvcLinkBuilder changePasswordLink= linkTo(methodOn(this.getClass())
                    .changePassword(username,new ChangePasswordRequest()));
            entityModel.add(changePasswordLink.withRel("user-change-password"));
        }
        WebMvcLinkBuilder allUsersLink = linkTo(methodOn(this.getClass()).getAllUsersFullDetails());
        entityModel.add(allUsersLink.withRel("user-all"));

        return entityModel;
    }


}
