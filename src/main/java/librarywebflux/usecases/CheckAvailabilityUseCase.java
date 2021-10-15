package librarywebflux.usecases;

import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class CheckAvailabilityUseCase implements Function<String, Mono<String>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;
    private final FindResourceUseCase findResourceUseCase;

    public CheckAvailabilityUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper, FindResourceUseCase findResourceUseCase) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
        this.findResourceUseCase = findResourceUseCase;
    }


    @Override
    public Mono<String> apply(String id) {
        return resourceRepository.findById(id)
                .map(resourceDTO -> resourceDTO.getQuantityAvailable()>0?
                        "Hay "+resourceDTO.getQuantityAvailable()+" recursos disponibles"
                        :"No hay unidades disponibles, la ultima se prestó en " + resourceDTO.getLastResourceLoanDate())
                .defaultIfEmpty("Este recurso no es comercializado por el momento");
    }
}
