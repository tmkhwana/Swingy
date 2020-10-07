package org.tmkhwana.Controller;

import org.tmkhwana.Model.Player;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PlayerValidator {
    static Validator validator;

    public PlayerValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean validate(Player player) {
        boolean ok;
        ok = false;
        Set<ConstraintViolation<Player>> validationErrors = validator.validate(player);
        if (!validationErrors.isEmpty()) {
            ok = true;
            for (ConstraintViolation<Player> error : validationErrors) {
                System.out.println(String.format("%s - %s [%s]", error.getPropertyPath().toString(), error.getMessage(), player.getName()));
            }
            validationErrors.clear();
        }
        return (ok);
    }
}
