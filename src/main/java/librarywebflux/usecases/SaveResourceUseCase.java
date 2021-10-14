package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class SaveResourceUseCase implements SaveResourceInterface{

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;

    public SaveResourceUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
    }


    @Override
    public Mono<String> apply(ResourceDTO resourceDTO) {
        return resourceRepository.save(resourceMappper.convertToModel(resourceDTO));
    }
}
