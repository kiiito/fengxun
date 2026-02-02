import com.hc.beanFactory.Gun;
import com.hc.beanFactory.Person;
import com.hc.beanFactory.Star;
import com.hc.beanFactory.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactorySpringTest {
    @Test
    public void testFactoryBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-Factory.xml");
        Student date = applicationContext.getBean("date", Student.class);
        System.out.println(date);
    }
    @Test
    public void testFactory4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-Factory.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }
    @Test
    public void testFactory3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-Factory.xml");
        Gun gun = applicationContext.getBean("gun", Gun.class);
        System.out.println(gun);
    }
    @Test
    public void testFactory2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-Factory.xml");
        Star star = applicationContext.getBean("star", Star.class);
        System.out.println(star);
    }
}
