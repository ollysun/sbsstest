package com.sbss.test;

import com.sbss.test.domain.Profile;
import com.sbss.test.repository.ProfileRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SbsstestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileRepository mockRepository;

    @Before
    public void init() {
        Profile profile = new Profile(1L, "Bodhisattva","female","12.3cm");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(profile));
    }

    @WithMockUser("ADMIN")
    @Test
    public void find_login_ok() throws Exception {
        mockMvc.perform(get("/profiles/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bodhisattva")))
                .andExpect(jsonPath("$.height", is("12.3cm")))
                .andExpect(jsonPath("$.gender", is("female")));
    }

    @WithMockUser("USER")
    @Test
    public void find_nologin_401() throws Exception {
        mockMvc.perform(get("/profiles/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
