package com.springstruct.cli;

import com.springstruct.model.ProjectStructure;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringStructCommandLineRunner implements CommandLineRunner {

    private final ProjectGenerator projectGenerator;

    public SpringStructCommandLineRunner(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            showHelp();
            return;
        }

        String basePackage = args[0];

        if (!isValidPackageName(basePackage)) {
            System.err.println("❌ Erro: Nome de pacote inválido: " + basePackage);
            showHelp();
            return;
        }

        if (!isInSpringProject()) {
            System.out.println("⚠️  Aviso: Execute no diretório src/main/java do projeto Spring");
            return;
        }

        ProjectStructure structure = new ProjectStructure(basePackage);
        projectGenerator.generateStructure(structure);
    }

    private boolean isValidPackageName(String packageName) {
        return packageName != null &&
                packageName.matches("^([a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)$");
    }

    private boolean isInSpringProject() {
        return java.nio.file.Files.exists(java.nio.file.Paths.get("src/main/java"));
    }

    private void showHelp() {
        System.out.println("""
            
            🏗️  Spring Struct - O Arquiteto de Pacotes
            ========================================
            
            USO: java -jar spring-struct.jar <pacote-base>
            
            EXEMPLO:
              java -jar spring-struct.jar com.br.meuprojeto
            
            RESULTADO:
              src/main/java/com/br/meuprojeto/
                ├── controller/
                ├── service/
                ├── repository/
                ├── model/
                ├── dto/
                ├── config/
                └── exception/
            
            """);
    }
}