package tech.buildrun.picpay.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
// @RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // @NonNull
    // private final WalletTypeRepository walletTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Arrays.stream(WalletType.Value.values()).forEach(value -> {
        // walletTypeRepository.save(value.get());
        // });
    }

}
