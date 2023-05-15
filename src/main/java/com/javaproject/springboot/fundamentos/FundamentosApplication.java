package com.javaproject.springboot.fundamentos;

import com.javaproject.springboot.fundamentos.bean.MyBean;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithDependency;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithProperties;
import com.javaproject.springboot.fundamentos.component.ComponentDependency;
import com.javaproject.springboot.fundamentos.entity.User;
import com.javaproject.springboot.fundamentos.pojo.UserPojo;
import com.javaproject.springboot.fundamentos.repository.UserRepository;
import com.javaproject.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency compDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDep;
	@Autowired
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	/*
	Inyeccion de dependecias
	 */
	public FundamentosApplication(@Qualifier("componentDependencyImpTwo") ComponentDependency comDep,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency
			/*, MyBeanWithProperties myBeanWithProperties*/,
								  UserPojo userPojo,
								  UserRepository userRepository,
								  UserService userService) {
		this.compDependency = comDep;
		this.myBean = myBean;
		this.myBeanWithDep = myBeanWithDependency;
		//this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	public void ejemplosAnteriores() {
		compDependency.saludar();
		myBean.imprimir();
		myBeanWithDep.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try {
			//error
			int value = 10 / 0;
			LOGGER.info("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividor por cero " + e.getMessage() + e.getStackTrace());
		}
	}

	private void saveUsersInDataBase() {
		User userOne = new User("John", "john@domail.com", LocalDate.of(2000, 1, 1));
		User userTwo = new User("Julie", "julie@domail.com", LocalDate.of(2000, 2, 2));
		User userThree = new User("Daniela", "daniela@domail.com", LocalDate.of(2000, 3, 3));
		User userFour = new User("Louis", "louis@domail.com", LocalDate.of(2000, 4, 4));
		User userFive = new User("Sam", "sam@domail.com", LocalDate.of(2000, 5, 5));
		User userSix = new User("Fabrizio", "fabrizio@domail.com", LocalDate.of(2000, 6, 6));
		User userSeven = new User("Martin", "user7@domail.com", LocalDate.of(2000, 7, 7));
		User userEight = new User("Javier", "user8@domail.com", LocalDate.of(2000, 8, 8));
		User userNine = new User("Eleonor", "user9@domail.com", LocalDate.of(2000, 9, 9));
		User userTen = new User("user10", "luci@domail.com", LocalDate.of(2000, 10, 10));
		User userEleven = new User("user11", "gabriela@domail.com", LocalDate.of(2000, 11, 11));
		User userTwelve = new User("user12", "eliot@domail.com", LocalDate.of(2000, 12, 12));

		List<User> list = Arrays.asList(userOne, userTwo, userThree, userFour, userFive, userSix, userSeven, userEight, userNine, userTen, userEleven, userTwelve);
		list.stream().forEach(userRepository::save);
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario con el metodo findByUserEmail: " +
				userRepository.findByUserEmail("sam@domail.com")
						.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort" + user));

		userRepository.findByName("John")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method findByEmailAndName " + userRepository.findByEmailAndName("daniela@domail.com", "Daniela")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

		userRepository.findByNameOrEmail("Fabrizio", null)
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));

		userRepository.findByBirthDateBetween(LocalDate.of(2000, 4, 1), LocalDate.of(2000, 6, 30))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));

		userRepository.findByNameContainingOrderByIdAsc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con containing y ordenado " + user));

		userRepository.findByEmailLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));

		userRepository.findByEmailContainingOrderByIdAsc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con containing y ordenado " + user));

		LOGGER.info("El usuario a partir del named parameter es: " + userRepository
				.getAllByBirthDateAndEmail(LocalDate.of(2000, 3, 3), "daniela@domail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	private void saveWithErrorTransactional() {
		User testTransact1 = new User("test1", "test1@domain.com", LocalDate.of(2000,1,2));
		User testTransact2 = new User("test2", "test2@domain.com", LocalDate.of(2000,2,4));
		User testTransact3 = new User("test3", "test3@domain.com", LocalDate.of(2000,3,6));
		User testTransact4 = new User("test4", "test4@domain.com", LocalDate.of(2000,4,8));
		User testTransact5 = new User("test5", "test5@domain.com", LocalDate.of(2000,5,10));
		User testTransact6 = new User("test6", "test6@domain.com", LocalDate.of(2000,6,12));
		User testTransact7 = new User("test7", "test1@domain.com", LocalDate.of(2000,7,14));

		List<User> userss = Arrays.asList(testTransact1,testTransact2,testTransact3,testTransact4,testTransact5,testTransact6,testTransact7);

		try {
			userService.saveTransactional(userss);
		} catch (Exception e) {
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional " + e);
		}

		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional " + user));
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
}
