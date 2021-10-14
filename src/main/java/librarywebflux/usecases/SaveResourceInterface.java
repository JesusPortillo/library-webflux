package librarywebflux.usecases;

import librarywebflux.model.ResourceDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveResourceInterface {
    Mono<String> apply(@Valid ResourceDTO resourceDTO);
}
