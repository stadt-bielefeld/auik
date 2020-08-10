package de.bielefeld.umweltamt.aui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

/**
 * Custom connection provider enabling spatialite for every connection it provides
 */
public class SqliteConnectionProvider implements ConnectionProvider, Configurable, ServiceRegistryAwareService {

    private static final long serialVersionUID = 1L;

    private ServiceRegistryImplementor serviceRegistry;

    private ConnectionProvider connectionProvider;

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void configure(Map configurationValues) {
        @SuppressWarnings("unchecked")
        Map<String, Object> settings = new HashMap<>( configurationValues );
        settings.remove(AvailableSettings.CONNECTION_PROVIDER);
        connectionProvider = ConnectionProviderInitiator.INSTANCE.initiateService(
                settings,
                serviceRegistry
        );
        if (connectionProvider instanceof Configurable) {
            Configurable configurableConnectionProvider = (Configurable) connectionProvider;
            configurableConnectionProvider.configure(settings);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection c = connectionProvider.getConnection();
        //c.createStatement().executeQuery("SELECT load_extension('mod_spatialite');");
        return c;
    }

    @Override
    public void closeConnection(Connection con) throws SQLException {
        connectionProvider.closeConnection(con);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return connectionProvider.supportsAggressiveRelease();
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return connectionProvider.isUnwrappableAs( unwrapType );
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return connectionProvider.unwrap( unwrapType );
    }
}