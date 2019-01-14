package server.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashUtil {
    public String Sha256Hash(String string) {
        return Hashing.sha256()
                .hashString(string, StandardCharsets.UTF_8)
                .toString();
    }
}
