package br.com.gaia;


import br.com.gaia.service.ServerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@ComponentScan("br.com.gaia")
@SpringBootApplication
public class App {
	@Autowired
	public static ServerLogin serverLogin;
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(App.class, args);
		serverLogin.server();

	}

}
