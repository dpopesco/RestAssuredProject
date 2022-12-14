package api.github;

import Users.AnotherUser;
import Users.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

public class ObjectMapping {
    public static final String BASE_URI = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingTestOne() {
        User user = RestAssured.get(BASE_URI)
                .as(User.class);

        Assert.assertEquals(user.getLogin(), "rest-assured");
        Assert.assertEquals(user.getId(), 19369327);
        Assert.assertEquals(user.getPublicRepos(), 2);
    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {

        io.restassured.mapper.ObjectMapper mapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });
        AnotherUser user = RestAssured.get(BASE_URI)
                .as(AnotherUser.class, mapper);

        Assert.assertEquals(user.login, "rest-assured");
        Assert.assertEquals(user.id, 19369327);
        Assert.assertEquals(user.public_repos, 2);
    }
}
