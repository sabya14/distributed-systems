package principles.consistenthashing;

import org.junit.jupiter.api.Test;

import java.security.DigestException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MD5HashingTest {

    @Test
    void shouldReturnALongHashLessThanNoOfNodes() throws DigestException {
        MD5Hashing hashing = new MD5Hashing(1000);
        long hash1 = hashing.hash("10.12.12.30");
        assertTrue(hash1 < 1000);
    }

    @Test
    void shouldReturnSameHashForBothKeys() throws DigestException {
        MD5Hashing hashing = new MD5Hashing(1000);
        long hash1 = hashing.hash("10.12.12.30");
        long hash2 = hashing.hash("10.12.12.30");
        assertEquals(hash1, hash2);
    }

}