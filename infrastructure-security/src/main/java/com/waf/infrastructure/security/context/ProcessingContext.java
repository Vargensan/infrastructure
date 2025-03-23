package com.waf.infrastructure.security.context;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessingContext {

    ThreadLocal<Invoker> invoker = new ThreadLocal<>();

    public void setInvoker(Invoker invoker) {
        if (invoker != null) {
            throw new IllegalStateException("Invoker already set");
        }
        this.invoker.set(invoker);
    }

    public Invoker getInvoker() {
        return invoker.get();
    }

    public void clearInvoker() {
        invoker.remove();
    }

}
