package com.apiintegration.bripley.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apiintegration.bripley.service.ScanScaService;

@Controller
public class IndexController {
	Properties properties = new Properties();

	@Autowired
	ScanScaService scaService;

	@RequestMapping("/sca_scan")
	@ResponseBody
	public String Index(HttpServletRequest request, @RequestHeader(value = "appname") String appname,
			@RequestHeader(value = "rutascan") String rutascan, @RequestHeader(value = "proyecto") String proyecto) {

		String result = "";
		try {
			properties.load(new FileInputStream(new File("C:\\Automation_SCA\\dimensions.properties")));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		String[] cmd = properties.getProperty("SCA_SCAN").split(" ");
		System.out.println(Arrays.toString(cmd));
		cmd[3] = appname;
		cmd[5] = rutascan;
		result = scaService.sca_scan(cmd);
		cmd = properties.getProperty("SCA_UPLOAD").split(" ");
		cmd[6] = proyecto;
		cmd[8] = appname;
		result = scaService.sca_upload_result(cmd);
		return result;
	}

	@GetMapping("/heartbeat")
	@ResponseBody
	public String index() {
		return "OK";
	}
}