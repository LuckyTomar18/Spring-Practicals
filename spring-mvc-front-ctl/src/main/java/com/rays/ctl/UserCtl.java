package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;
import com.rays.util.DataUtility;

@Controller
@RequestMapping(value = "/ctl/User")
public class UserCtl {

	@Autowired
	UserServiceInt userService;

	@GetMapping
	public String display(@ModelAttribute("form") UserRegistrationForm form, @RequestParam(required = false) Long id) {

		if (id != null && id > 0) {
			UserDTO dto = userService.findByPk(id);
			form.setId(dto.getId());
			form.setFirstName(dto.getFirstName());
			form.setLastName(dto.getLastName());
			form.setLogin(dto.getLogin());
			form.setPassword(dto.getPassword());
			form.setDob(DataUtility.dateToString(dto.getDob()));
			form.setAddress(dto.getAddress());
		}
		return "UserView";
	}

	@PostMapping
	public String save(@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "UserView";
		}
		UserDTO dto = new UserDTO();

		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(DataUtility.stringToDate(form.getDob()));
		dto.setAddress(form.getAddress());

		try {
			if (dto.getId() > 0) {

				userService.update(dto);
				model.addAttribute("msg", "user updated successfully");
			} else {
				long pk = userService.add(dto);
				model.addAttribute("msg", "user Added successfully");
			}
		} catch (Exception e) {
			model.addAttribute("emsg", e.getMessage());
		}

		return "UserView";
	}

	@GetMapping("/Search")
	public String Search(@ModelAttribute("form") UserForm form, Model model) {

		int pageNo = 1;
		int pageSize = 5;

		List<UserDTO> list = userService.search(null, pageNo, pageSize);

		form.setPageNo(pageNo);

		model.addAttribute("list", list);
		return "UserListView";
	}

	@PostMapping("/Search")
	public String Search(@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult,
			@RequestParam(required = false) String operation, Model model) {

		if (bindingResult != null) {
			return "UserListView";
		}
		UserDTO dto = null;

		int pageNo = 1;
		int pageSize = 5;

		if (operation.equals("next")) {
			pageNo = form.getPageNo();
			pageNo++;
		}

		if (operation.equals("previous")) {
			pageNo = form.getPageNo();
			pageNo--;
		}

		if (operation.equals("search")) {
			dto = new UserDTO();
			dto.setFirstName(form.getFirstName());
		}

		if (operation.equals("delete")) {
			if (form.getIds() != null && form.getIds().length > 0) {
				for (long id : form.getIds()) {
					userService.delete(id);
				}
			}
		}

		form.setPageNo(pageNo);

		List list = userService.search(dto, pageNo, pageSize);

		model.addAttribute("list", list);

		return "UserListView";
	}

}
