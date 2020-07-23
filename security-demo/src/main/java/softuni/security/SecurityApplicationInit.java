package softuni.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.security.model.AuthorityEntity;
import softuni.security.model.UserEntity;
import softuni.security.repository.UserRepository;

import java.util.List;

@Component
public class SecurityApplicationInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityApplicationInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity();
        user.setName("user");
        user.setPassword(this.passwordEncoder.encode("user"));

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setName("ROLE_USER");
        authorityEntity.setUserEntity(user);

        user.setAuthorities(List.of(authorityEntity));
        this.userRepository.save(user);


        UserEntity admin = new UserEntity();
        admin.setName("admin");
        admin.setPassword(this.passwordEncoder.encode("admin"));
        admin.setEnabled(true);

        AuthorityEntity adminUserAuthorityEntity = new AuthorityEntity();
        adminUserAuthorityEntity.setName("ROLE_USER");
        adminUserAuthorityEntity.setUserEntity(admin);

        AuthorityEntity adminAdminAuthorityEntity = new AuthorityEntity();
        adminAdminAuthorityEntity.setName("ROLE_ADMIN");
        adminAdminAuthorityEntity.setUserEntity(admin);

        admin.setAuthorities(List.of(adminUserAuthorityEntity, adminAdminAuthorityEntity));
        userRepository.save(admin);

    }
}
