package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberReposiroty;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource datasource;

    @Autowired
    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberReposiroty());
    }

    @Bean
    public MemberReposiroty memberReposiroty(){
//        return new MemoryMemberRepository()
//        return new JdbcMemberRepository(datasource);
        return new JdbcTemplateMemberRepository(datasource);
    }

}
