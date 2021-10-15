package librarywebflux.usecases;

import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.function.Function;

@Service
@Validated
public class ReturnResourceUseCase implements Function<String, Mono<Object>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;
    private final CheckAvailabilityUseCase checkAvailabilityUseCase;

    public ReturnResourceUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper, CheckAvailabilityUseCase checkAvailabilityUseCase) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
        this.checkAvailabilityUseCase = checkAvailabilityUseCase;
    }

    @Override
    public Mono<Object> apply(String id) {

        return resourceRepository.findById(id).flatMap(resource -> {
                resource.setQuantityAvailable(resource.getQuantityAvailable()+1);
                return resourceRepository.save(resource).thenReturn("Se devolvió el recurso de " + resource.getTitle());

        });
    }
}
