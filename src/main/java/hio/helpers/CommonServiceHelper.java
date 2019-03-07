package hio.helpers;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonServiceHelper {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }
}
