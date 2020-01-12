package worldofclients;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import worldofclients.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.id=?1")
    Client getClientById(long id);
}
