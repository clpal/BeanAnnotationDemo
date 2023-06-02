package com.bean.BeanAnnotationDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class Student {
   Address address;
	public Student(Address address){
		this.address=address;
	}

	public  void  print(){
		System.out.println("Student class method called");
		address.print();
	}
	public void init(){
		System.out.println("Initialization logic");
	}
	public  void destroy(){
		System.out.println("Destruction logic");
	}
}
class Address {
	public  void  print(){
		System.out.println("Address class method called");

	}
}
@Configuration
 class AppConfig {

	@Bean(name = "addressBean")
	public Address address(){
		return new Address();

	}
	//@Bean(name = {"studentBean","studentDemo","studentComponent"},initMethod = "init",destroyMethod = "destroy")
	@Bean(name = "studentBean",initMethod = "init",destroyMethod = "destroy")
	public Student student(){
		return new Student(address());

	}
}

@SpringBootApplication
public class BeanAnnotationDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BeanAnnotationDemoApplication.class, args);
		 ApplicationContext applicationContext= new AnnotationConfigApplicationContext(AppConfig.class);
		Student student= (Student) applicationContext.getBean("studentBean");
		String [] beanNames= applicationContext.getBeanDefinitionNames();
		/*for (String bean:beanNames){
			System.out.println(bean);
		}*/
		student.print();
	}

}
