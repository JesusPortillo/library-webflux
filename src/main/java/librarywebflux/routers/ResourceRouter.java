package librarywebflux.routers;

import librarywebflux.model.ResourceDTO;
import librarywebflux.usecases.*;
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

    @Bean
    public RouterFunction<ServerResponse> recommendByKindOfResource(RecommendByKindOfResourceUseCase recommendByKindOfResourceUseCase){
        return route(GET("/recommendByKindOfResource/{kindOfResource}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendByKindOfResourceUseCase.apply(
                                request.pathVariable("kindOfResource")),ResourceDTO.class))
                );
    }

    @Bean
    public RouterFunction<ServerResponse> recommendBySubject(RecommendBySubjectUseCase recommendBySubject){
        return route(GET("/recommendBySubject/{subject}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendBySubject.apply(
                                request.pathVariable("subject")),ResourceDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> recommendByKindOfResourceAndSubject(RecommendByKindOfResourceAndSubjectUseCase recommendByKindOfResourceAndSubjectUseCase){
        return route(GET("/recommendByKindOfResourceAndSubject/{kindOfResource}/{subject}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendByKindOfResourceAndSubjectUseCase.apply(
                                request.pathVariable("kindOfResource"), request.pathVariable("subject")),ResourceDTO.class))
        );
    }
}
