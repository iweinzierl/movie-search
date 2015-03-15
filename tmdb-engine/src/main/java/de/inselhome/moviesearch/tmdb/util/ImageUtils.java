package de.inselhome.moviesearch.tmdb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class ImageUtils {

    public static void main(final String[] args) {
        File in = new File("/home/iweinzierl/private/hg.amdb/tmdb-sdk/requests/test.png");

        try {
            String base64 = convertToBase64(in);
            System.out.println("Coverted length: " + base64.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String covertToBase64(final URL url) throws IOException {
        InputStream in = url.openStream();
        byte[] data = IOUtils.toByteArray(in);

        byte[] encoded = Base64.encodeBase64(data);
        return new String(encoded);
    }

    public static String convertToBase64(final File source) throws IOException {
        InputStream in = new FileInputStream(source);
        byte[] data = IOUtils.toByteArray(in);

        byte[] encoded = Base64.encodeBase64(data);
        return new String(encoded);
    }

    public static void convertToBase64(final File source, final File destination) throws IOException {
        String encoded = convertToBase64(source);
        IOUtils.write(encoded.getBytes(), new FileOutputStream(destination));
    }
}
