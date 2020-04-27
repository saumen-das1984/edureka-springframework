package com.edureka.ms.training.springsecurity.securitysimple;

import com.edureka.ms.training.securitysimple.model.UserAccount;
import com.edureka.ms.training.securitysimple.repository.UserAccountRepository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages={"com.edureka.ms.training.securitysimple"})
@EntityScan("com.edureka.ms.training.securitysimple.model")
@EnableJpaRepositories("com.edureka.ms.training.securitysimple.repository")
public class SecuritySimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySimpleApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(UserAccountRepository userAccountRepository){
		return args -> Stream.of("sanj,sanj","edureka,edureka")
				.map(s -> s.split(","))
				.forEach(string -> userAccountRepository.save(new UserAccount(string[0], string[1], true)));
		
		
//		{
//			userAccountRepository.save(new UserAccount("sanj", "sanj", true));
//			userAccountRepository.save(new UserAccount("edureka", "edureka", true));
//		};
	}

	@Bean
	public static NoOpPasswordEncoder noOpPasswordEncoder(){
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}


	@Service
	class MyUserDetailsService implements UserDetailsService{

		@Autowired
		UserAccountRepository userAccountRepository;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			System.out.println("UserName : "+username);
			Optional<UserAccount> byUsernameOptional = userAccountRepository.findByUsername(username);
			System.out.println("byUsernameOptional.isPresent() : "+byUsernameOptional.isPresent());
			if(!byUsernameOptional.isPresent()){
				throw new UsernameNotFoundException("User name -> " + username +" does not exit");
			}
			UserAccount userAccount = byUsernameOptional.get();
			System.out.println("Password : "+userAccount.getPassword());
			System.out.println("isActive : "+userAccount.isActive());
			
			return new User(userAccount.getUsername(),
					userAccount.getPassword(),
					userAccount.isActive(),
					userAccount.isActive(),
					userAccount.isActive(),
					userAccount.isActive(),
					AuthorityUtils.createAuthorityList("ADMIN", "USER"));
			 
//			 return "User Authenticated";

		}
	}

}