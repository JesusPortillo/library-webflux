package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class RecommendByKindOfResourceUseCase implements Function<String,Flux<ResourceDTO>> {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;


    public RecommendByKindOfResourceUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
    }

    @Override
    public Flux<ResourceDTO> apply(String kindOfResource) {
        return resourceRepository.findAll().filter(resource -> resource.getKindOfResource().equals(kindOfResource))
                .map(resource -> resourceMappper.convertToResourceDTO(resource));
    }
}
