package hio.controller.admin;

import hio.commons.ObjectMapperUtils;
import hio.dto.request.MenuCategoryRequestDTO;
import hio.model.menu.Category;
import hio.service.implementation.RestaurantMenuServiceImpl;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${api}/${admin.prefix-url}/${admin.restaurants}/${admin.menu}")
public class MenuManagementController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantMenuServiceImpl restaurantMenuService;

    @GetMapping("/list/{restaurantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MenuCategoryRequestDTO> list(
            @ApiParam("'/list/{restaurantId}'") @PathVariable Integer restaurantId
    ) {
        List<Category> categoryList = restaurantMenuService.list(restaurantId);
        return ObjectMapperUtils.mapAll(categoryList, MenuCategoryRequestDTO.class);
    }

    @PostMapping("/save/{restaurantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MenuCategoryRequestDTO save(
            @ApiParam("restaurantId") @PathVariable Integer restaurantId,
            @RequestBody Map<String, Object> payload
    ) {
        MenuCategoryRequestDTO menuCategoryRequestDTO = modelMapper.map(payload, MenuCategoryRequestDTO.class);
        Category category = modelMapper.map(menuCategoryRequestDTO, Category.class);
        return modelMapper.map(restaurantMenuService.save(category, restaurantId), MenuCategoryRequestDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(
            @ApiParam("id") @PathVariable Integer id
    ) {
        restaurantMenuService.delete(id);
    }
}
