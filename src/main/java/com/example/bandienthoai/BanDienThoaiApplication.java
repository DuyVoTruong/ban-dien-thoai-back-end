package com.example.bandienthoai;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BanDienThoaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanDienThoaiApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary(){
		Cloudinary c = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dkckat0q7",
				"api_key", "845453132759233",
				"api_secret", "UmOm9763zgzUHUA68epXVWce1as",
				"secure",true
		));
		return c;
	}

}
