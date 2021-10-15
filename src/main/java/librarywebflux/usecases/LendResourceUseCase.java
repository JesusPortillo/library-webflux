package librarywebflux.usecases;

import librarywebflux.collections.Resource;
import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.function.Function;

@Service
@Validated
public class LendResourceUseCase implements Function<String, Mono<Object>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;
    private final CheckAvailabilityUseCase checkAvailabilityUseCase;

    public LendResourceUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper, CheckAvailabilityUseCase checkAvailabilityUseCase) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
        this.checkAvailabilityUseCase = checkAvailabilityUseCase;
    }

    @Override
    public Mono<Object> apply(String id) {

        return resourceRepository.findById(id).flatMap(resource -> {
                    LocalDate date = LocalDate.now();
                    if (resource.getQuantityAvailable()>0){
                        resource.setQuantityAvailable(resource.getQuantityAvailable()-1);
                        resource.setLastResourceLoanDate(date.toString());
                        return resourceRepository.save(resource).thenReturn("Se prestó el recurso");
                    }
                    return Mono.just("No se pudo prestar el recurso, ya se encuentra prestado");
                });
    }

}
