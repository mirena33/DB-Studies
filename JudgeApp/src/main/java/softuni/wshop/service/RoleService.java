package softuni.wshop.service;

import softuni.wshop.model.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByName(String name);
}
