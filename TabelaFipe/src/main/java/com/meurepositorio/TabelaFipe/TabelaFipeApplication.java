package com.meurepositorio.TabelaFipe;

import com.meurepositorio.TabelaFipe.main.MainClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainClass mainClass = new MainClass();
		mainClass.menu();


	}
}
