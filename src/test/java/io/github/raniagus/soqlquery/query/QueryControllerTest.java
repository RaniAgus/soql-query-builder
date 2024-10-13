package io.github.raniagus.soqlquery.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.raniagus.soqlquery.query.dto.QueryObjectsRequest;
import io.github.raniagus.soqlquery.query.model.ConditionExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QueryController.class)
class QueryControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testQueryObjectsBy() throws Exception {
        var requestBody = new QueryObjectsRequest(
                ConditionExpression.and(
                        ConditionExpression.not(ConditionExpression.eq("Name", "Test")),
                        ConditionExpression.gt("CreatedDate", "2021-01-01T00:00:00Z"),
                        ConditionExpression.in("Type", "Customer", "Partner")
                )
        );

        mockMvc.perform(post("/objects/Account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string("SELECT Id,Name,CreatedDate,LastModifiedDate FROM Account WHERE ((NOT (Name = 'Test')) AND (CreatedDate > '2021-01-01T00:00:00Z') AND (Type IN ('Customer','Partner')))"));
    }
}