package bibtexpro.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import static java.util.Collections.singletonList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("bibtexpro.repository")
public class SpringMongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Override
    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        if (System.getenv("DATABASE_NAME") != null) {
            System.out.println("Using Heroku variables");
            String database = System.getenv("DATABASE_NAME");
            String host = System.getenv("MONGOHOST");
            String pass = System.getenv("MONGOPASS");
            String port = System.getenv("MONGOPORT");
            String user = System.getenv("MONGOUSER");

            return new MongoClient(singletonList(new ServerAddress(host + ":" + port)),
                    singletonList(MongoCredential.createCredential(user, database, pass.toCharArray())));
        }

        System.out.println("Using Fongo as the DB backend");
        return new Fongo(getDatabaseName()).getMongo();
    }

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }
}
