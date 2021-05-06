package dataproviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class InvalidCreditCardProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new CreditCard("5512270941707436", "03/30", "737"), "Your card number is invalid."),
                Arguments.of(new CreditCard("551227094170743", "03/30", "737"), "Your card number is incomplete."),
                Arguments.of(new CreditCard("44443333222211114444", "03/30", "737"), "Your card number is incorrect."),
                Arguments.of(new CreditCard("4444333322221111", "03/18", "737"), "Your card's expiration year is in the past."),
                Arguments.of(new CreditCard("4444333322221111", "03/30", "73"), "Your card's security code is incomplete.")
        );
    }
}

