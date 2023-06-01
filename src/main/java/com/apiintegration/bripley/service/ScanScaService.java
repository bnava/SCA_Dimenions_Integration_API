package com.apiintegration.bripley.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class ScanScaService {

    public String sca_scan(String[] cmd) {
        String result = "OK";
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
            result = "Error";
        }
        return result;
    }

    public String sca_upload_result(String[] cmd) {
        String result = "OK";
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
            result = "Error";
        }
        return result;
    }
}
