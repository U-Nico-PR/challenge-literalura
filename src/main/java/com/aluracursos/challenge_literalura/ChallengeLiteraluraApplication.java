package com.aluracursos.challenge_literalura;

import com.aluracursos.challenge_literalura.principal.Principal;
import com.aluracursos.challenge_literalura.repository.IRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

    @Autowired
    private IRepositorio repositorio;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.mostrarMenu();
    }
}
