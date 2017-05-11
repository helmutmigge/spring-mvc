package br.hm.example;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by helmut.guimaraes on 08/05/2017.
 */
public class Initializer extends AbstractHttpSessionApplicationInitializer {

   public Initializer(){
       super(PepHttpSessionConfiguration.class);
   }
}
