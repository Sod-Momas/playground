package cc.momas.reactor.core;

import reactor.core.publisher.Mono;

/**
 * @author Sod-Momas
 * @since 2022/1/13
 */
public class ReactorCoreMain {
    public static void main(String[] args) {
        Mono.just("hello reactor mono!").subscribe(System.out::println);
    }
}
