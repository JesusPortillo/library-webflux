package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import librarywebflux.repository.ResourceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllUseCase implements Supplier<Flux<ResourceDTO>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;


    public GetAllUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
    }

    @Override
    public Flux<ResourceDTO> get() {
        return resourceRepository.findAll().map(resource -> resourceMappper.convertToResourceDTO(resource));
    }
}
