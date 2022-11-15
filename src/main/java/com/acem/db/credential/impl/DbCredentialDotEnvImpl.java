package com.acem.db.credential.impl;

import com.acem.db.credential.DbCredential;
import io.github.cdimascio.dotenv.Dotenv;

public class DbCredentialDotEnvImpl implements DbCredential {
    private Dotenv dotenv;
    public DbCredentialDotEnvImpl() {
         dotenv = Dotenv.load();
    }

    @Override
    public String getIpAddress() {
        return dotenv.get("IP_ADDRESS");
    }

    @Override
    public String getPort() {
        return dotenv.get("PORT");
    }

    @Override
    public String getName() {
        return dotenv.get("NAME");
    }

    @Override
    public String getUsername() {
        return dotenv.get("USERNAME");
    }

    @Override
    public String getPassword() {
        return dotenv.get("PASSWORD");
    }

    @Override
    public String getConfigValue(String key) {
        return dotenv.get(key);
    }
}
