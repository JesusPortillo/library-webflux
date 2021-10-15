package librarywebflux.routers;

import librarywebflux.collections.Resource;
import librarywebflux.model.ResourceDTO;
import librarywebflux.usecases.CheckAvailabilityUseCase;
import librarywebflux.usecases.FindResourceUseCase;
import librarywebflux.usecases.GetAllUseCase;
import librarywebflux.usecases.LendResourceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ResourceRouter {

    @Bean
    public RouterFunction<ServerResponse> getAll(GetAllUseCase getAllUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllUseCase.get(), ResourceDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> findResource(FindResourceUseCase findResourceUseCase){
        return route(GET("/findResource/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findResourceUseCase.apply(
                                request.pathVariable("id")), ResourceDTO.class
                                ))
                );
    }

    @Bean
    public RouterFunction<ServerResponse> lendResource(LendResourceUseCase lendResourceUseCase){
        return route(GET("/lendResource/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(lendResourceUseCase.apply(
                                request.pathVariable("id")), Object.class))
                );
    }

    @Bean
    public RouterFunction<ServerResponse> checkAvailability(CheckAvailabilityUseCase checkAvailabilityUseCase){
        return route(GET("/checkAvailability/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(checkAvailabilityUseCase.apply(
                                request.pathVariable("id")),String.class))
        );
    }
}
