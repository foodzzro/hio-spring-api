package hio.controller.auth;

import hio.dto.response.AuthResponseDTO;
import io.swagger.annotations.*;
import hio.model.User;
import hio.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${api}/${auth.url}")
@Api(tags = "auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/${auth.login}")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public AuthResponseDTO login(//
                        @ApiParam("Email") @RequestParam String email, //
                        @ApiParam("Password") @RequestParam String password) {

        User user = userService.findByEmail(email);
        AuthResponseDTO response = new AuthResponseDTO();

        response.setToken(userService.signin(email, password));
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles());

        return response;
    }
}
