package com.joelnetodev.pizzaria;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//Para que a segurança funcione, precisa ter uma classe que herde de SecurityWebInitializer, assim como a WebAppInitializer
public class StartSecurityApplication extends AbstractSecurityWebApplicationInitializer {
}