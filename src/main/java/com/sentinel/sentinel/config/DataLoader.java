package com.sentinel.sentinel.config;
import com.sentinel.sentinel.model.Vehicle;
import com.sentinel.sentinel.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
@Configuration
    public class DataLoader {
        @Bean
        CommandLineRunner loadData(VehicleRepository repository){
            return args -> {
                repository.save(new Vehicle(
                        "TN01AB1234",
                        "Arun Kumar",
                        "TN",
                        false,
                        false
                ));

                repository.save(new Vehicle(
                        "TN22CD5678",
                        "Suresh",
                        "TN",
                        true,
                        false
                ));

                repository.save(new Vehicle(
                        "PY05EF9999",
                        "Ravi",
                        "PY",
                        false,
                        true
                ));
            };

}

    }

