package com.olivereivak.starter.guice;

import com.google.inject.Injector;

public class GuiceInjector {

    private static Injector injector;

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector inj) {
        injector = inj;
    }

}
