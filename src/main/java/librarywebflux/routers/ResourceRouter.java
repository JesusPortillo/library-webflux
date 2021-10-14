package librarywebflux.routers;

import librarywebflux.model.ResourceDTO;
import librarywebflux.usecases.FindResourceUseCase;
import librarywebflux.usecases.GetAllUseCase;
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
        return route(GET("/findResource/{title}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findResourceUseCase.apply(
                                request.pathVariable("title")), ResourceDTO.class
                                ))
                );
    }
}
