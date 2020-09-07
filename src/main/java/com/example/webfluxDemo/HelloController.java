package com.example.webfluxDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: ZhangCong
 * @Date: 2020/9/7 22:12
 */
@Slf4j
@RestController
@RequestMapping("/api/")
public class HelloController {

    @GetMapping("mono1")
    public Mono<String> mono1() {
        return Mono.just("hello webflux");
    }

    @GetMapping("mono2")
    public Mono<Object> mono2() {
        return Mono.create(monoSink -> {
            log.info("创建 Mono");
            monoSink.success("hello webflux");
        })

                .doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                    log.info("{}", subscription);
                })
                .doOnNext(o -> { //当订阅者收到数据时，改方法会调用
                    log.info("{}", o);
                });
    }


    @GetMapping("flux1")
    public Flux<String> flux1() {
        return Flux.just("hello","webflux","spring","boot");
    }
}