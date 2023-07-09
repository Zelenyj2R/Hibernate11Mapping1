import Servise.ClientCrudService;
import Servise.HibernateUtil;
import Servise.PlanetCrudService;
import Servise.TicketCrudService;
import entity.Client;

import entity.Ticket;
import org.flywaydb.core.Flyway;




public class Main {


    public static void main(String[] args) {
        final String url = "jdbc:h2:./mode11iMyMapping;TRACE_LEVEL_FILE=3;TRACE_MAX_FILE_SIZE=10";
        final String user = "";
        final String password = "";

        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();

        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();

        System.out.println(clientCrudService.get(2L));
        Client client = clientCrudService.get(4L).orElseThrow();
        client.setName("Update Name");
        clientCrudService.update(client);
        System.out.println(clientCrudService.get(4L));
        Client newClient = Client.builder().name("New client").build();
        clientCrudService.create(newClient);
        System.out.println(clientCrudService.get(11L));
        clientCrudService.delete(6L);
        System.out.println("Client id=3 has next tickets: " + clientCrudService.get(3L).orElseThrow().getTickets());

        System.out.println(planetCrudService.get("CAL"));
        System.out.println("Planet id=CAL has next tickets to itself: " + planetCrudService.get("CAL").orElseThrow().getToPlanets());
        System.out.println("Planet id=CAL has next tickets from itself: " + planetCrudService.get("CAL").orElseThrow().getFromPlanets());

        System.out.println(ticketCrudService.get(1L));
        Ticket ticket = ticketCrudService.get(1L).orElseThrow();
        ticket.setClient(clientCrudService.get(7L).orElseThrow());
        ticketCrudService.update(ticket);
        System.out.println(ticketCrudService.get(1L));

        HibernateUtil.getInstance().close();

    }
}

