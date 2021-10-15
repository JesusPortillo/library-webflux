package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveResourceInterface {
    Mono<ResourceDTO> apply(@Valid ResourceDTO resourceDTO);
}
