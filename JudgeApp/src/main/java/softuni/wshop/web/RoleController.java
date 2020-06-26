package softuni.wshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.wshop.model.binding.RoleAddBindingModel;
import softuni.wshop.service.RoleService;
import softuni.wshop.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RoleController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.addObject("usernamesAttr", this.userService.getAllUsernames());

        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@ModelAttribute("roleAddBindingModel")
                                     RoleAddBindingModel roleAddBindingModel) {

        //to do @Valid and bindingResult

        this.userService.addRole(roleAddBindingModel.getUsername(),
                roleAddBindingModel.getRole());


        return "redirect:/";
    }
}
