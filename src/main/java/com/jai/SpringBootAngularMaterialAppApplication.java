package com.jai;

import com.jai.daos.UserDao;
import com.jai.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootAngularMaterialAppApplication{

	/*@Autowired
	private UserDao userDao;*/

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularMaterialAppApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		List<String> roles = new ArrayList<>();
		String pkkoshta = passwordEncoder.encode("pkkoshta");
		roles.add("admin");
		roles.add("manager");
		User user =
				new User(1,"prashant", "Prashant",pkkoshta,
						"prashant@gmail.com","Male", roles ,true);
		User save = userDao.save(user);
		System.out.println(save);

	}*/

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
