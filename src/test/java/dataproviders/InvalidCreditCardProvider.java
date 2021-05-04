package dataproviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class InvalidCreditCardProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of("5711270041703456", "03/30", "737", "Your card number is invalid."),
                Arguments.of("571127004170345", "03/30", "737", "Your card number is incomplete."),
                Arguments.of("44443333222211114444", "03/30", "737", "Your card number is incorrect."),
                Arguments.of("4444333322221111", "03/18", "737", "Your card's expiration year is in the past."),
                Arguments.of("4444333322221111", "03/30", "73", "Your card's security code is incomplete.")

        );
    }
}
