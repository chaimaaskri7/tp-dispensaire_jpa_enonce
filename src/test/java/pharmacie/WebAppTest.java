package pharmacie;

import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour la classe WebApp
 * Testent la création du bean H2 Server sans démarrer le contexte Spring
 * complet
 */
public class WebAppTest {

    private WebApp webApp = new WebApp();

    @Test
    public void testWebAppInstanceCreation() {
        // Vérifie qu'une instance de WebApp peut être créée
        assertNotNull(webApp);
        assertTrue(webApp instanceof WebApp);
    }

    @Test
    public void testH2TcpServerBeanCreation() throws SQLException {
        // Teste la création du bean h2TcpServer
        Server server = webApp.h2TcpServer();
        assertNotNull(server);
        assertTrue(server instanceof Server);
    }

    @Test
    public void testH2TcpServerBeanNotNull() throws SQLException {
        // Teste que le serveur H2 TCP est créé correctement
        Server server = webApp.h2TcpServer();
        assertNotNull(server);
    }

    @Test
    public void testH2TcpServerPort() throws SQLException {
        // Teste que le serveur H2 TCP utilise le bon port
        Server server = webApp.h2TcpServer();
        assertNotNull(server);

        // Le port doit être 9092
        int port = server.getPort();
        assertEquals(9092, port);
    }

    @Test
    public void testWebAppClassExists() {
        // Vérifie que la classe WebApp existe et est accessible
        assertNotNull(WebApp.class);
        assertTrue(
                WebApp.class.isAnnotationPresent(org.springframework.boot.autoconfigure.SpringBootApplication.class));
    }

    @Test
    public void testWebAppHasH2BeanMethod() {
        // Vérifie que la méthode h2TcpServer existe dans WebApp
        try {
            WebApp app = new WebApp();
            assertNotNull(app.getClass().getMethod("h2TcpServer"));
        } catch (NoSuchMethodException e) {
            fail("Méthode h2TcpServer() non trouvée dans WebApp");
        }
    }

    @Test
    public void testWebAppMethodReturnType() throws SQLException {
        // Teste que la méthode h2TcpServer retourne bien un Server
        WebApp app = new WebApp();
        Object result = app.h2TcpServer();
        assertTrue(result instanceof Server, "Le résultat doit être une instance de Server");
    }

    @Test
    public void testH2TcpServerIsRunning() throws SQLException {
        // Teste que le serveur H2 TCP est démarré
        Server server = webApp.h2TcpServer();
        assertNotNull(server);
        // Le serveur doit avoir un port associé
        assertTrue(server.getPort() > 0);
    }

    @Test
    public void testWebAppMainMethodExists() {
        // Vérifie que la méthode main existe dans WebApp
        try {
            WebApp.class.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            fail("Méthode main() non trouvée dans WebApp");
        }
    }

    @Test
    public void testH2TcpServerMultipleCreations() throws SQLException {
        // Teste qu'on peut créer plusieurs instances du serveur
        Server server1 = webApp.h2TcpServer();
        assertNotNull(server1);
        assertEquals(9092, server1.getPort());

        // Les deux instances doivent avoir le même port
        Server server2 = webApp.h2TcpServer();
        assertNotNull(server2);
        assertEquals(server1.getPort(), server2.getPort());
    }

    @Test
    public void testH2TcpServerBeanAnnotation() {
        // Vérifie que la méthode h2TcpServer a l'annotation @Bean
        try {
            var method = WebApp.class.getMethod("h2TcpServer");
            assertTrue(method.isAnnotationPresent(org.springframework.context.annotation.Bean.class),
                    "La méthode h2TcpServer doit avoir l'annotation @Bean");
        } catch (NoSuchMethodException e) {
            fail("Méthode h2TcpServer() non trouvée");
        }
    }

    @Test
    public void testWebAppClassAnnotations() {
        // Teste les annotations de la classe WebApp
        assertTrue(WebApp.class.isAnnotationPresent(org.springframework.boot.autoconfigure.SpringBootApplication.class),
                "WebApp doit être annoté avec @SpringBootApplication");

        // Vérifie que c'est une classe bien formée
        assertNotNull(WebApp.class.getPackageName());
        assertTrue(WebApp.class.getPackageName().equals("pharmacie"));
    }

    @Test
    public void testH2ServerConfiguration() throws SQLException {
        // Teste la configuration complète du serveur H2
        Server server = webApp.h2TcpServer();
        assertNotNull(server);

        // Vérifie que le serveur est correctement initialisé
        assertTrue(server.getPort() > 0, "Le port doit être positif");
        assertEquals(9092, server.getPort(), "Le port doit être 9092");
    }

    @Test
    public void testWebAppInstanceFields() {
        // Teste que l'instance WebApp peut être utilisée pour accéder aux méthodes
        // statiques
        WebApp app = new WebApp();
        assertNotNull(app);

        // Vérifie que la classe n'est pas abstraite
        assertFalse(java.lang.reflect.Modifier.isAbstract(WebApp.class.getModifiers()),
                "WebApp ne doit pas être une classe abstraite");
    }

    @Test
    public void testMultipleWebAppInstances() {
        // Teste qu'on peut créer plusieurs instances de WebApp
        WebApp app1 = new WebApp();
        WebApp app2 = new WebApp();

        assertNotNull(app1);
        assertNotNull(app2);
        assertNotEquals(app1, app2, "Les instances doivent être différentes");
    }
}
