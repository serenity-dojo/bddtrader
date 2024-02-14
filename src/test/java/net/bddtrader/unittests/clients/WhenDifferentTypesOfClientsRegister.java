package net.bddtrader.unittests.clients;

import net.bddtrader.clients.Client;
import net.bddtrader.clients.ClientController;
import net.bddtrader.clients.ClientDirectory;
import net.bddtrader.config.TradingDataSource;
import net.bddtrader.portfolios.*;
import net.bddtrader.tradingdata.TradingData;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;




import java.util.stream.Stream;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenDifferentTypesOfClientsRegister {

    ClientDirectory clientDirectory = new ClientDirectory();
    PortfolioDirectory portfolioDirectory = new PortfolioDirectory();
    PortfolioController portfolioController = new PortfolioController(TradingDataSource.DEV, portfolioDirectory);
    ClientController controller = new ClientController(clientDirectory, portfolioController);

    static Stream<Arguments> namesProvider() {
        return Stream.of(arguments("Sarah-Jane",     "Smith"),
                         arguments("Bill",  "Oddie"),
                         arguments("Tim", "Brooke-Taylor"));
    }

    @BeforeEach
    public void resetTestData() {
        TradingData.instanceFor(DEV).reset();
    }

    @ParameterizedTest(name = "for {0} with {1}")
    @MethodSource("namesProvider")
    public void aClientRegisters(String firstName,String lastName) {

        // WHEN
        Client registeredClient = controller.register(Client.withFirstName(firstName).andLastName(lastName).andEmail("sarah-jane@smith.com"));

        // THEN
        assertThat(registeredClient).isEqualToComparingFieldByField(registeredClient);
    }

}
