package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecommendByKindAndSubjectInterface {
    Flux<ResourceDTO> apply(@Valid String kindOfResource, @Valid String subject);
}
