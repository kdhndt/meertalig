package be.vdab.meertalig.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
//nodig voor registratie van interceptors
public class WebConfig implements WebMvcConfigurer {
    private static final int ZEVEN_DAGEN = 604_000;

    @Bean
    LocaleResolver localeResolver() {
//        return new FixedLocaleResolver(new Locale("en", "US"));
//        return new SessionLocaleResolver();
        var resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(ZEVEN_DAGEN);
        return resolver;
    }

    //@Override/Implement optie ipv zelf te typen
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //(locale) parameter in je URL expression zorgt voor interceptor (oproep v/d class) tussen browser request en Controller
        //interceptor roept de method setLocale van je SessionLocaleResolver bean op, die bean onthoudt de taal en het land v/d gebruiken als HTTP session variabele
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}