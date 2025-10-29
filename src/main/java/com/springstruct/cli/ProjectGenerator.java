package com.springstruct.cli;

import com.springstruct.model.ProjectStructure;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ProjectGenerator {

    public void generateStructure(ProjectStructure structure) {
        try {
            System.out.println("🏗️  Criando estrutura do projeto Spring Boot...");
            System.out.println("📦 Pacote base: " + structure.getBasePackage());

            String basePath = "src/main/java/" + structure.getBasePackagePath();

            Files.createDirectories(Paths.get(basePath));

            for (String pkg : structure.getPackages()) {
                String packagePath = basePath + "/" + pkg;
                Files.createDirectories(Paths.get(packagePath));
                System.out.println("✅ Criado pacote: " + pkg);
            }

            createExampleFiles(structure, basePath);

            System.out.println("\n🎉 Estrutura criada com sucesso!");
            showStructure(structure);

        } catch (Exception e) {
            System.err.println("❌ Erro ao criar estrutura: " + e.getMessage());
        }
    }

    private void createExampleFiles(ProjectStructure structure, String basePath) {
        try {
            String entityContent = """
                package %s.model;
                
                import jakarta.persistence.*;
                import java.time.LocalDateTime;
                
                @Entity
                @Table(name = "users")
                public class User {
                    @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private Long id;
                    
                    @Column(nullable = false)
                    private String name;
                    
                    @Column(nullable = false, unique = true)
                    private String email;
                    
                    private LocalDateTime createdAt;
                    
                    public User() {}
                    
                    public User(String name, String email) {
                        this.name = name;
                        this.email = email;
                        this.createdAt = LocalDateTime.now();
                    }
                    
                    // Getters e Setters
                    public Long getId() { return id; }
                    public void setId(Long id) { this.id = id; }
                    
                    public String getName() { return name; }
                    public void setName(String name) { this.name = name; }
                    
                    public String getEmail() { return email; }
                    public void setEmail(String email) { this.email = email; }
                    
                    public LocalDateTime getCreatedAt() { return createdAt; }
                    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
                }
                """.formatted(structure.getBasePackage());

            Files.writeString(Paths.get(basePath, "model/User.java"), entityContent);
            System.out.println("📄 Criado: User.java (exemplo)");

            String controllerContent = """
                package %s.controller;
                
                import org.springframework.web.bind.annotation.*;
                import java.util.List;
                
                @RestController
                @RequestMapping("/api/users")
                public class UserController {
                    
                    @GetMapping
                    public List<String> getAllUsers() {
                        return List.of("User1", "User2");
                    }
                    
                    @PostMapping
                    public String createUser(@RequestBody String user) {
                        return "User created: " + user;
                    }
                }
                """.formatted(structure.getBasePackage());

            Files.writeString(Paths.get(basePath, "controller/UserController.java"), controllerContent);
            System.out.println("📄 Criado: UserController.java (exemplo)");

        } catch (Exception e) {
            System.out.println("⚠️  Não foi possível criar arquivos de exemplo");
        }
    }

    private void showStructure(ProjectStructure structure) {
        System.out.println("\n📁 Estrutura criada:");
        System.out.println("src/main/java/" + structure.getBasePackagePath() + "/");

        for (String pkg : structure.getPackages()) {
            System.out.println("  ├── " + pkg + "/");
        }
    }
}
