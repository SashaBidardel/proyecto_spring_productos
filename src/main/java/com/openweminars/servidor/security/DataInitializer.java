package com.openweminars.servidor.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.openweminars.servidor.model.Categoria;
import com.openweminars.servidor.model.Producto;
import com.openweminars.servidor.model.Usuario;
import com.openweminars.servidor.repository.CategoriaRepository;
import com.openweminars.servidor.repository.ProductoRepository;
import com.openweminars.servidor.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    public DataInitializer(CategoriaRepository categoriaRepository,
                           UsuarioRepository usuarioRepository, ProductoRepository productoRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear categoría GENERAL si no existe
    @Bean
    CommandLineRunner initCategorias() {
        return args -> {

            if (categoriaRepository.findByNombre("General") == null) {

                Categoria general = new Categoria();
                general.setNombre("General");

                categoriaRepository.save(general);

                log.info("Categoría 'General' creada");
            }

        };
    }

    // Crear usuarios por defecto si no existen
    @Bean
    CommandLineRunner initUsuarios() {
        return args -> {

            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = Usuario.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234"))
                        .role("ADMIN")
                        .build();

                usuarioRepository.save(admin);

                log.info("Usuario admin creado");
            }

            if (usuarioRepository.findByUsername("usuario").isEmpty()) {

                Usuario user = Usuario.builder()
                        .username("usuario")
                        .password(passwordEncoder.encode("1234"))
                        .role("USER")
                        .build();

                usuarioRepository.save(user);
                log.info("Usuario usuario creado");

            }

        };
    }
    @Bean
    CommandLineRunner initProductos() {
        return args -> {
            // Crear productos por defecto si no existen
            if (productoRepository.findByNombre("Producto A").isEmpty()) {
                Producto p1 = new Producto();
                Categoria general = categoriaRepository.findByNombre("General");
                p1.setCategoria(general);
                p1.setNombre("Producto A");
                p1.setPrecio(12);
                productoRepository.save(p1);
            }

            if (productoRepository.findByNombre("Producto B").isEmpty()) {
                Producto p2 = new Producto();
                Categoria general = categoriaRepository.findByNombre("General");
                p2.setCategoria(general);
                p2.setNombre("Producto B");
                p2.setPrecio(29);
                productoRepository.save(p2);
            }

            if (productoRepository.findByNombre("Producto C").isEmpty()) {
                Producto p3 = new Producto();
                Categoria general = categoriaRepository.findByNombre("General");
                p3.setCategoria(general);
                p3.setNombre("Producto C");
                p3.setPrecio(5);
                productoRepository.save(p3);
            }

            log.info("Productos por defecto inicializados");
        };
    }
}