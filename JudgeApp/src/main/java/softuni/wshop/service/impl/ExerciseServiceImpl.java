package softuni.wshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.wshop.model.entity.Exercise;
import softuni.wshop.model.service.ExerciseServiceModel;
import softuni.wshop.repository.ExerciseRepository;
import softuni.wshop.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel exerciseServiceModel) {
        this.exerciseRepository.saveAndFlush(this.modelMapper.map(exerciseServiceModel, Exercise.class));
    }
}
