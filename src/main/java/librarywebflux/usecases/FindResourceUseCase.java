package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class FindResourceUseCase implements Function<String, Flux<ResourceDTO>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;

    public FindResourceUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
    }


    @Override
    public Flux<ResourceDTO> apply(String title) {
        Objects.requireNonNull(title);
        return resourceRepository.findAll().filter(resource -> resource.getTitle().equals(title)).map(resource -> resourceMappper.convertToResourceDTO(resource));

    }

}
