package com.softuni.demo.services.impls;

import com.softuni.demo.domain.dtos.GameAddDto;
import com.softuni.demo.domain.entities.Game;
import com.softuni.demo.repositories.GameRepository;
import com.softuni.demo.services.GameService;
import com.softuni.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        try {
            if (!this.userService.isLoggedUserAdmin()) {
                System.out.println("Logged user is not ADMIN.");
            }
        } catch (NullPointerException e) {
            System.out.println("Logged user is not ADMIN.");
            return;
        }

        Game game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);
    }
}
