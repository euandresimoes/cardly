package br.com.euandresimoes.pin_service.application.useCases.create;

import br.com.euandresimoes.pin_service.application.dtos.requests.CardStyle;
import br.com.euandresimoes.pin_service.application.dtos.requests.PinCreateRequest;
import br.com.euandresimoes.pin_service.domain.entity.PinEntity;
import br.com.euandresimoes.pin_service.domain.entity.enums.PinType;
import br.com.euandresimoes.pin_service.domain.repository.PinRepository;
import br.com.euandresimoes.pin_service.infrastructure.services.TokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PinCreateImplTest {

    private PinRepository pinRepo;
    private PinCreateImpl pinCreate;
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        pinRepo = Mockito.mock(PinRepository.class);
        tokenService = Mockito.mock(TokenService.class);
        pinCreate = new PinCreateImpl(pinRepo, tokenService);
    }

    @Test
    void shouldCreateNewPin() {
        var cardStyleMock = new CardStyle(
                "#000000",
                "#000001",
                "#000002",
                "#000003",
                "#000004",
                "#000005",
                "#000006"
        );

        var mockData = new PinCreateRequest(
                PinType.PRODUCT,
                "some title",
                "https://example.com",
                true,
                cardStyleMock
        );

        String fakeToken = "i2andnja2ljkdljnk2anljd";
        String authHeader = "Bearer " + fakeToken;

        // Mockando os Claims
        var idClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);
        var displayNameClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);

        Mockito.when(idClaim.asLong()).thenReturn(15L);
        Mockito.when(displayNameClaim.asString()).thenReturn("Test User");

        // Mockando o JWT
        DecodedJWT mockDecoded = Mockito.mock(DecodedJWT.class);
        Mockito.when(mockDecoded.getClaim("id")).thenReturn(idClaim);
        Mockito.when(mockDecoded.getClaim("displayName")).thenReturn(displayNameClaim);
        Mockito.when(mockDecoded.getSubject()).thenReturn("user123456789");

        // Mockando o tokenService
        Mockito.when(tokenService.extract(authHeader)).thenReturn(fakeToken);
        Mockito.when(tokenService.decode(fakeToken)).thenReturn(mockDecoded);

        pinCreate.execute(mockData, authHeader);

        // Verificando se o pin foi salvo
        Mockito.verify(pinRepo, Mockito.times(1)).save(Mockito.any(PinEntity.class));
    }
}