package softuni.wshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.wshop.model.binding.ExerciseBindingAddModel;
import softuni.wshop.model.service.ExerciseServiceModel;
import softuni.wshop.service.ExerciseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("exerciseBindingAddModel") ExerciseBindingAddModel exerciseBindingAddModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseBindingAddModel", exerciseBindingAddModel);
            return "redirect:/exercises/add";
        } else {
            this.exerciseService.addExercise(this.modelMapper.map(exerciseBindingAddModel, ExerciseServiceModel.class));
            return "redirect:/";
        }
    }
}
