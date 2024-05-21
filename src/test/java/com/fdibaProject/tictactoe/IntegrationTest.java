package com.fdibaProject.tictactoe;

import org.springframework.boot.test.context.SpringBootTest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {
        TicTacToeApplication.class,
})
public @interface IntegrationTest {
}