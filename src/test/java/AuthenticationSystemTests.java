import org.example.SecureAuthentication.entity.Users;
import org.example.SecureAuthentication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticationSystemTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        // Clear the user repository before each test
        userRepository.deleteAll();
    }

    @Test
    public void testSuccessfulAuthentication() throws Exception {
        // Create a test user
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        userRepository.save(user);

        // Perform the login request
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"testpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isString());
    }

    @Test
    public void testInvalidUsername() throws Exception {
        // Perform the login request with an invalid username
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"invaliduser\", \"password\": \"testpassword\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Invalid username"));
    }

    @Test
    public void testInvalidPassword() throws Exception {
        // Create a test user
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        userRepository.save(user);

        // Perform the login request with an invalid password
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"invalidpassword\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Invalid password"));
    }
}