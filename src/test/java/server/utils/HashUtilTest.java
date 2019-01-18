package server.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stubs.RepositoryStub;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashUtilTest {
    private static RepositoryStub repositoryStub;
    private static HashUtil hashUtil;

    @BeforeAll
    static void Prep(){
        repositoryStub=new RepositoryStub();
        hashUtil=new HashUtil();
    }

    @Test
    void Sha256HashTest(){
        String hashed=hashUtil.Sha256Hash(repositoryStub.getUnhashedPassword());
        assertEquals(hashed,repositoryStub.getAccount().getPassword());
    }

}
