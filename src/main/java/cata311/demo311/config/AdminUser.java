package cata311.demo311.config;

import cata311.demo311.model.Role;
import cata311.demo311.model.User;
import cata311.demo311.service.RoleService;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminUser implements InitializingBean {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User Admin = new User();
        Admin.setName("admin");
        Admin.setLastName("admin");
        Admin.setAge(12);
        Admin.setPassword("admin");
        Admin.setEmail("admin@admin.com");
        Admin.setRole(roleService.findRoleById(Long.valueOf(1)));
        Admin.setRole(roleService.findRoleById(Long.valueOf(2)));


        User User = new User();
        User.setName("user");
        User.setLastName("user");
        User.setAge(12);
        User.setPassword("user");
        User.setEmail("user@user.com");
        User.setRole(roleService.findRoleById(Long.valueOf(2)));

        userService.add(Admin);
        userService.add(User);

    }
}
