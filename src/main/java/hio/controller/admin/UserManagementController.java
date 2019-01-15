package hio.controller.admin;

import hio.dto.response.GeneralResponseDTO;
import hio.dto.response.UserResponseDTO;
import hio.model.User;
import hio.service.implementation.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${api}/${admin.prefix-url}/${admin.users}")
public class UserManagementController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponseDTO> list() {
        List<UserResponseDTO> userList = new ArrayList<UserResponseDTO>();

        for (User user : userService.list() ) {
            userList.add(modelMapper.map(user, UserResponseDTO.class));
        }
        return userList;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO create(
            @ApiParam("Email") @RequestParam String email, //
            @ApiParam("Password") @RequestParam String password,
            @ApiParam("Username") @RequestParam String username,
            @ApiParam("Role") @RequestParam String role,
            @ApiParam("firstName") @RequestParam String firstName,
            @ApiParam("lastName") @RequestParam String lastName,
            @ApiParam("phone") @RequestParam String phone,
            @ApiParam("active") @RequestParam Boolean active
    ) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setActive(active);
        user.setRoles(userService.getRole(role));

        userService.signup(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
    })
    public UserResponseDTO update(
            @ApiParam("Email") @RequestParam String email, //
            @ApiParam("Password") @RequestParam String password,
            @ApiParam("Username") @RequestParam String username,
            @ApiParam("Role") @RequestParam String role,
            @ApiParam("firstName") @RequestParam String firstName,
            @ApiParam("lastName") @RequestParam String lastName,
            @ApiParam("phone") @RequestParam String phone,
            @ApiParam("active") @RequestParam Boolean active,
            @ApiParam("id") @PathVariable Integer id
    ) {

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setActive(active);
        user.setRoles(userService.getRole(role));

        userService.update(user);

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @GetMapping("/change/status/{id}/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GeneralResponseDTO changeStatus(
            @ApiParam("id") @PathVariable Integer id,
            @ApiParam("status") @PathVariable Boolean status) {
        User user = userService.findById(id);
        user.setActive(status);
        this.userService.update(user);
        return new GeneralResponseDTO(true, "admin.users.status_changed");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
    })
    public Boolean delete(@ApiParam("id") @PathVariable Integer id) {
        this.userService.deleteById(id);
        return true;
    }



}
