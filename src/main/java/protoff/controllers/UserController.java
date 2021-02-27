package protoff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import protoff.models.User;
import protoff.services.UserService;

@Controller
public class UserController {

    private User updateUser;
    private String updateTitle;

    private final UserService userService;

    public UserController(UserService userService) {
        resetForm();
        this.userService = userService;
    }

    private void resetForm() {
        updateTitle = "Create new user";
        updateUser = new User();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("createUpdateUser", new User());
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("updateTitle", updateTitle);
        return "index";
    }

    @PostMapping
    public String createUser(@ModelAttribute("createUpdateUser") User user) {
        if (user.getId() == 0 ) {
            // **************** created ******************
            userService.createUser(user);
        } else {
            // ************** Updated ***********************
            resetForm();
            User userForUpdate = userService.getUserById(user.getId());
            userForUpdate.setName(user.getName());
            userForUpdate.setSurname(user.getSurname());
            userForUpdate.setCity(user.getCity());
            userService.updateUser(userForUpdate);
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id) {
        updateUser = userService.getUserById(id);
        updateTitle = "Update user";
        return "redirect:/";
    }

}
