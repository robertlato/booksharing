package pl.edu.pg.booksharing.Booksharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class BooksharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksharingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	// add "// https://mvnrepository.com/artifact/org.modelmapper.extensions/modelmapper-spring
	//	compile group: 'org.modelmapper.extensions', name: 'modelmapper-spring', version: '2.3.3"
	// to build.gradle

}
