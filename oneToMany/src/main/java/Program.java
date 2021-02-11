import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.service.RoleDaoImp;
import ru.sapteh.service.UserDaoImp;

public class Program {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        DAO<Role,Integer> roleDaoImp = new RoleDaoImp(factory);
        DAO<User,Integer> userDaoImp = new UserDaoImp(factory);

        Role read = roleDaoImp.read(1);

        System.out.println(read);

        factory.close();

    }
}
