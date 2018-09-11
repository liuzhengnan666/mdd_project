package com.example.administrator.llc_p.activity;

import android.content.Intent;
import android.os.Bundle;
import org.apache.shiro.codec.Base64;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.llc_p.R;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

public class LoginActivity extends BaseActivity {

    private TextView tv_register,tv_forget_password;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_register = findViewById(R.id.tv_register);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.bt_login:
                //startActivity(new Intent(this,MainActivity.class));
                String str = "backUrl=rl=https://www.31mdd.com/home/testasd/test";
                String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJoBuQkksPmff9TJkjw+EpAoLlgqog7dn1R3vQGroJoeaHfIalfbGUmqcwSeB3EnZvFOGrtvF9pUemu71+uEL/Nd2uirHM2/rWH/67rrjjyYhwumIy1UNcTU50BhICLycYAUVceqAd8EaijHVUStAEFDjMK2PneqMkONecrLbrOxAgMBAAECgYBlbMcIz4JCE5ojcFD/nb2p9Nx4sjMiaTCad3UhmX9eoLIaWQI4NBJWYov3OYAbNGDmgJK4aDSkVEh/lSlhcSAGNv0wwU/fvbGAz1LkhV9QfFX39ILSJebgF1uC/cDM3Fy4qfLZwbVA/sChhEJBEFz9hM4kC1whFb7hiIwxBpJ18QJBANzv6JzbTun6QYkAjjcSaXqCvRJ0stQZpybwTusNjg25nbe7ZaIf58PtjdPlPCmVLIVhv6QMHOf+I4P6TJoE7S0CQQCycpvNXSibvmSs3xBaF4oX6SzwEAU8Fvq/tRqa3ZT8xCTfAtWj1vcOXqCCWmrx+h7YU7UYcn4lNvVpkY/pQZsVAkAMzOWIOPiQe8xSOLMEo89ypqJ3sEUwHrBdhCy+V5G0X92waG4R/5E4+8lszmo26oqiy7ADvCPsmP/LxLOtdL2dAkEAquWv6KGLPny8lgKa+m0Ix5V8f/wXx1CKnAapkB8djGWcmidwyxQYTx64wZ4uRcewuqSQDvW3cZSMu6m4FT/LcQJAMDSdMuTU44dGWmNWwD2Hea8c6MYBQm4u0OR+tLd84k7ecqXL7dauws7zNsnIWHQoNwT0IdBvikjRB5gsWcxEtA==";
                byte[] sb = str.getBytes();
                StringBuilder s1 = new StringBuilder();
                try {
                    byte[] bytes = encryptByPrivateKey(sb, key);
                    for (int i=0;i<bytes.length;i++){
                        int aByte = bytes[i];
                        s1.append(aByte);
                    }
                    String s = new String(bytes);
//                    for( byte b : encryptByPrivateKey(str.getBytes(), key))
//                    {
//
//                    }
                    Log.d("KEY", "KEY: "+s1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {

        byte[] keyBytes = Base64.decode(privateKey.getBytes());

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
}
