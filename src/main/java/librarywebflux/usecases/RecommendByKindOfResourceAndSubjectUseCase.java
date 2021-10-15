package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import librarywebflux.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Service
@Validated
public class RecommendByKindOfResourceAndSubjectUseCase implements RecommendByKindAndSubjectInterface {

    private final ResourceRepository resourceRepository;
    private final ResourceMappper resourceMappper;


    public RecommendByKindOfResourceAndSubjectUseCase(ResourceRepository resourceRepository, ResourceMappper resourceMappper) {
        this.resourceRepository = resourceRepository;
        this.resourceMappper = resourceMappper;
    }


    @Override
    public Flux<ResourceDTO> apply(String kindOfResource, String subject) {
        return resourceRepository.findAll().filter(resource -> resource.getKindOfResource().equals(kindOfResource) &&
                        resource.getSubject().equals(subject))
                .map(resource -> resourceMappper.convertToResourceDTO(resource));
    }
}
